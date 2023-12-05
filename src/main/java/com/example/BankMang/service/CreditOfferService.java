package com.example.BankMang.service;

import com.example.BankMang.model.Credit;
import com.example.BankMang.model.CreditOffer;
import com.example.BankMang.model.Payment;
import com.example.BankMang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditOfferService {

    private final List<CreditOffer> creditOffers = new ArrayList<>();
    private final UserService userService;
    private final CreditService creditService;

    @Autowired
    public CreditOfferService(UserService userService, CreditService creditService) {
        this.userService = userService;
        this.creditService = creditService;
    }

    public List<CreditOffer> getAllCreditOffers() {
        return creditOffers;
    }

    public Optional<CreditOffer> getCreditOfferById(long creditOfferId) {
        return creditOffers.stream()
                .filter(creditOffer -> creditOffer.getId() == creditOfferId)
                .findFirst();
    }

    public CreditOffer createCreditOffer(CreditOffer creditOffer) {
        creditOffer.setId(System.currentTimeMillis());

        User user = userService.getUserById(creditOffer.getUser().getId());

        Credit credit = creditService.getCreditById(creditOffer.getCredit().getId())
                .orElseThrow(() -> new IllegalArgumentException("Credit not found"));

        creditOffer.setUser(user);
        creditOffer.setCredit(credit);

        // Вызовите расчеты и обновление объекта CreditOffer
        calculatePaymentSchedule(creditOffer);
        creditOffer.setTotalInterestAmount(calculateTotalInterestAmount(creditOffer));
        creditOffer.setMonthlyPayment(calculateMonthlyPayment(
                creditOffer.getSum(),
                creditOffer.getCredit().getInterestRate() / 100.0 / 12.0,
                creditOffer.getPaymentSchedule()));

        creditOffers.add(creditOffer);
        return creditOffer;
    }

    public Optional<CreditOffer> updateCreditOffer(long creditOfferId, CreditOffer updatedCreditOffer) {
        Optional<CreditOffer> existingCreditOffer = getCreditOfferById(creditOfferId);
        existingCreditOffer.ifPresent(creditOffer -> {
            creditOffer.setUser(updatedCreditOffer.getUser());
            creditOffer.setCredit(updatedCreditOffer.getCredit());
            creditOffer.setSum(updatedCreditOffer.getSum());
            creditOffer.setPaymentSchedule(updatedCreditOffer.getPaymentSchedule());
            creditOffer.setPaymentData(updatedCreditOffer.getPaymentData());
            creditOffer.setPaymentSum(updatedCreditOffer.getPaymentSum());
            creditOffer.setLoanRepaymentAmount(updatedCreditOffer.getLoanRepaymentAmount());
            creditOffer.setInterestRepaymentAmount(updatedCreditOffer.getInterestRepaymentAmount());
        });
        return existingCreditOffer;
    }

    public boolean deleteCreditOffer(long creditOfferId) {
        Optional<CreditOffer> creditOfferToRemove = getCreditOfferById(creditOfferId);
        if (creditOfferToRemove.isPresent()) {
            creditOffers.remove(creditOfferToRemove.get());
            return true;
        }
        return false;
    }

    private void calculatePaymentSchedule(CreditOffer creditOffer) {
        double monthlyInterestRate = creditOffer.getCredit().getInterestRate() / 100.0 / 12.0;
        double loanAmount = creditOffer.getSum();
        int paymentPeriods = creditOffer.getPaymentSchedule();

        double monthlyPayment = calculateMonthlyPayment(loanAmount, monthlyInterestRate, paymentPeriods);
        double remainingLoanAmount = loanAmount;

        List<Payment> paymentSchedule = new ArrayList<>();

        for (int i = 1; i <= paymentPeriods; i++) {
            double interestPayment = remainingLoanAmount * monthlyInterestRate;
            double principalPayment = monthlyPayment - interestPayment;

            Payment payment = new Payment();
            payment.setPaymentNumber(i);
            payment.setDueDate(calculateDueDate(creditOffer.getPaymentData(), i));
            payment.setPaymentAmount(monthlyPayment);
            payment.setPrincipalAmount(principalPayment);
            payment.setInterestAmount(interestPayment);

            paymentSchedule.add(payment);

            remainingLoanAmount -= principalPayment;
        }

        creditOffer.setPaymentScheduleList(paymentSchedule); // Используем paymentScheduleList
    }

    // Метод для автоматического расчета итоговой суммы процентов по кредиту
    private double calculateTotalInterestAmount(CreditOffer creditOffer) {
        double loanAmount = creditOffer.getSum();
        double totalInterestRate = creditOffer.getCredit().getInterestRate();
        int paymentPeriods = creditOffer.getPaymentSchedule();

        return loanAmount * totalInterestRate / 100.0 * paymentPeriods / 12.0;
    }

    // Метод для автоматического расчета суммы ежемесячного платежа с учетом процентной ставки
    private double calculateMonthlyPayment(double loanAmount, double monthlyInterestRate, int paymentPeriods) {
        return loanAmount * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, paymentPeriods))
                / (Math.pow(1 + monthlyInterestRate, paymentPeriods) - 1);
    }

    // Метод для расчета даты платежа
    private LocalDate calculateDueDate(int paymentData, int paymentNumber) {
        LocalDate currentDate = LocalDate.now();
        return currentDate.plusMonths(paymentData + paymentNumber - 1);
    }
}

package com.example.BankMang.service;
import model.CreditOffer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditOfferService {

    private final List<CreditOffer> creditOffers = new ArrayList<>();

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
}

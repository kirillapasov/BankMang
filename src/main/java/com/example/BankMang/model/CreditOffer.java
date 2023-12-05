package com.example.BankMang.model;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class CreditOffer {
    private Long id;
    private User user;
    private Credit credit;
    private long sum;
    private int paymentSchedule;
    private short paymentData;
    private long paymentSum;
    private long loanRepaymentAmount;
    private long interestRepaymentAmount;
    private double totalInterestAmount;
    private double MonthlyPayment;
    private List<Payment> paymentScheduleList;

}

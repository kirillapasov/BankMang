package com.example.BankMang.model;

import com.example.BankMang.service.CreditOfferService;
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
    private List<CreditOfferService.Payment> paymentScheduleList; // Добавлено поле для графика платежей

}

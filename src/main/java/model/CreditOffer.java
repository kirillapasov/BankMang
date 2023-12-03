package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreditOffer {
    private User user;
    private Credit credit;
    private long sum;
    private int paymentSchedule; //График платежей
    private short paymentData;
    private long paymentSum;
    private long loanRepaymentAmount; //Сумма гашения тела кредита
    private long InterestRepaymentAmount;//Сумма гашения процентов

}

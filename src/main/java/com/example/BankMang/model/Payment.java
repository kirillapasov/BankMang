package com.example.BankMang.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Payment {
    private int paymentNumber;
    private LocalDate dueDate;
    private double paymentAmount;
    private double principalAmount;
    private double interestAmount;

}
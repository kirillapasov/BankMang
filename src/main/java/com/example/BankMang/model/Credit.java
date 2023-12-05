package com.example.BankMang.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Credit {
    private long creditLimit;
    private short interestRate;
    private Long id;
}

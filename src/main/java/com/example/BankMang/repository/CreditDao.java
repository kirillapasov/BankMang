package com.example.BankMang.repository;

import com.example.BankMang.model.Credit;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CreditDao {
    private final List<Credit> credits = new ArrayList<>();

    public List<Credit> getAllCredits() {
        return new ArrayList<>(credits);
    }

    public Optional<Credit> getCreditById(long creditId) {
        return credits.stream()
                .filter(credit -> credit.getId() == creditId)
                .findFirst();
    }

    public Credit createCredit(Credit credit) {
        credit.setId(System.currentTimeMillis());
        credits.add(credit);
        return credit;
    }

    public Optional<Credit> updateCredit(long creditId, Credit updatedCredit) {
        Optional<Credit> existingCredit = getCreditById(creditId);
        existingCredit.ifPresent(credit -> {
            credit.setCreditLimit(updatedCredit.getCreditLimit());
            credit.setInterestRate(updatedCredit.getInterestRate());
        });
        return existingCredit;
    }

    public boolean deleteCredit(long creditId) {
        Optional<Credit> creditToRemove = getCreditById(creditId);
        if (creditToRemove.isPresent()) {
            credits.remove(creditToRemove.get());
            return true;
        }
        return false;
    }
}

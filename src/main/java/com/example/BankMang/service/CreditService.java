package com.example.BankMang.service;

import model.Credit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditService {

    private final List<Credit> credits = new ArrayList<>();

    public List<Credit> getAllCredits() {
        return credits;
    }

    public Optional<Credit> getCreditById(long creditId) {
        return credits.stream()
                .filter(credit -> credit.getId() == creditId)
                .findFirst();
    }

    public Credit createCredit(Credit credit) {
        // Генерация уникального идентификатора (в реальном приложении лучше использовать базу данных)
        credit.setId(System.currentTimeMillis());
        // Дополнительная бизнес-логика, если необходимо
        credits.add(credit);
        return credit;
    }

    public Optional<Credit> updateCredit(long creditId, Credit updatedCredit) {
        // Дополнительная бизнес-логика, если необходимо
        Optional<Credit> existingCredit = getCreditById(creditId);
        existingCredit.ifPresent(credit -> {
            credit.setCreditLimit(updatedCredit.getCreditLimit());
            credit.setInterestRate(updatedCredit.getInterestRate());
            // Другие поля...
        });
        return existingCredit;
    }

    public boolean deleteCredit(long creditId) {
        // Дополнительная бизнес-логика, если необходимо
        Optional<Credit> creditToRemove = getCreditById(creditId);
        if (creditToRemove.isPresent()) {
            credits.remove(creditToRemove.get());
            return true;
        }
        return false;
    }
}
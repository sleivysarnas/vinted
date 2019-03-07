package rules;

import module.Transaction;

import java.util.List;

public interface DiscountRule {
    void applyDiscounts(List<Transaction> transactionList);
}

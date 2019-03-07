package module;

import rules.DiscountRule;
import rules.FreeXShipmentRule;
import rules.SmallPackageRule;
import utilities.ModuleHelper;

import java.util.ArrayList;
import java.util.List;

public class DiscountModule {
    private final List<DiscountRule> rules = new ArrayList<>();
    private final ModuleHelper moduleHelper;
    private final List<Transaction> finalTransactions;

    public DiscountModule(ModuleHelper moduleHelper) {
        rules.add(new SmallPackageRule(moduleHelper));
        rules.add(new FreeXShipmentRule(moduleHelper));
        this.moduleHelper = moduleHelper;
        this.finalTransactions = calculateDiscounts();
    }

    private List<Transaction> calculateDiscounts() {
        for (DiscountRule rule : rules)
            rule.applyDiscounts(moduleHelper.getTransactions());
        for (Transaction transaction : moduleHelper.getTransactions()) {
            if (transaction.getPrice() == null) {
                transaction.setPrice(moduleHelper.getProviderPrice(transaction.getName(), transaction.getSize()));
                transaction.setDiscount("-");
            }
        }
        return moduleHelper.getTransactions();
    }

    public List<Transaction> getFinalTransactions() {
        return finalTransactions;
    }
}

package module;

import rules.DiscountRule;
import rules.FreeXShipmentRule;
import rules.SmallPackageRule;
import utilities.ModuleHelper;

import java.util.ArrayList;
import java.util.List;

public class DiscountModule {
    private final List<DiscountRule> rules = new ArrayList<>();

    public DiscountModule(ModuleHelper moduleHelper) {
        rules.add(new SmallPackageRule(moduleHelper));
        rules.add(new FreeXShipmentRule(moduleHelper));
        calculateDiscounts(moduleHelper);
    }

    private List<Transaction> calculateDiscounts(ModuleHelper moduleHelper) {
        for (DiscountRule rule : rules)
            rule.applyDiscounts();
        for (Transaction transaction : moduleHelper.getTransactions()) {
            if (transaction.getPrice() == null) {
                transaction.setPrice(moduleHelper.getProviderPrice(transaction.getName(), transaction.getSize()));
                transaction.setDiscount("-");
            }
        }
        return moduleHelper.getTransactions();
    }
}

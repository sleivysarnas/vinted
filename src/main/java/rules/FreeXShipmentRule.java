package rules;

import module.ShippingInterface;
import module.Transaction;
import utilities.ModuleHelper;

public class FreeXShipmentRule implements DiscountRule {

    private static final String CARRIER = "LP";
    private static final int AMOUNT_FOR_FREE_SHIPMENT = 3;
    private static final Transaction.PackageSize PACKAGE_SIZE = ShippingInterface.PackageSize.L;

    private final ModuleHelper moduleHelper;

    public FreeXShipmentRule(ModuleHelper moduleHelper) {
        this.moduleHelper = moduleHelper;
    }

    public void applyDiscounts() {
        int shipmentCount = 0;
        for (Transaction transaction : moduleHelper.getTransactions()) {
            if (transaction.getName().equals(CARRIER) && transaction.getSize().equals(PACKAGE_SIZE)) {
                shipmentCount++;
                if (shipmentCount == AMOUNT_FOR_FREE_SHIPMENT) {
                    transaction.setDiscount(moduleHelper.getProviderPrice(transaction.getName(), transaction.getSize()));
                    transaction.setPrice("0");
                    shipmentCount = 0;
                }
            }
        }
    }
}

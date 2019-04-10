package rules;

import module.Provider;
import module.ShippingInterface;
import module.Transaction;
import utilities.ModuleHelper;

import java.util.List;

public class SmallPackageRule implements DiscountRule {

    private final String SMALL_PACKAGE_NAME = "S";
    private final ModuleHelper moduleHelper;

    public SmallPackageRule(ModuleHelper moduleHelper) {
        this.moduleHelper = moduleHelper;
    }

    public void applyDiscounts() {
        float lowestPrice = getLowestPrice(moduleHelper.getProviders());
        float providerPrice;
        String discount;
        for (Transaction transaction : moduleHelper.getTransactions()) {
            if (transaction.getSize().equals(ShippingInterface.PackageSize.valueOf(SMALL_PACKAGE_NAME))) {
                providerPrice = Float.parseFloat
                        (moduleHelper.getProviderPrice(transaction.getName(), transaction.getSize()));
                transaction.setPrice(String.valueOf(lowestPrice));
                discount = String.valueOf(providerPrice - lowestPrice);
                if(discount.equals("0.0")) {
                    transaction.setDiscount("-");
                } else {
                    transaction.setDiscount(String.valueOf(providerPrice - lowestPrice));
                }
            }
        }
    }

    private float getLowestPrice(List<Provider> providers) {
        float lowestPrice = Float.MAX_VALUE;
        for (Provider provider : providers) {
            if (provider.getSize().equals(Provider.PackageSize.valueOf(SMALL_PACKAGE_NAME))
                    && Float.parseFloat(provider.getPrice()) < lowestPrice) {
                lowestPrice = Float.parseFloat(provider.getPrice());
            }
        }
        return lowestPrice;
    }
}

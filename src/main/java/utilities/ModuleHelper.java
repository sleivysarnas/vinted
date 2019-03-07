package utilities;

import module.Provider;
import module.ShippingInterface;
import module.Transaction;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ModuleHelper {

    private final List<Provider> providers = new ArrayList<>();
    private final List<Transaction> transactions = new ArrayList<>();
    private final TreeMap<Integer, String> ignoredTransactions = new TreeMap<>();

    public ModuleHelper(List<String> inputTransactions, List<String> inputProviders) {
        generateProviders(inputProviders);
        generateTransactions(inputTransactions);
    }

    private void generateProviders(List<String> inputProviders) {
        for (String inputProvider : inputProviders) {
            String[] providerParts = inputProvider.split(" ");
            providers.add(new Provider(providerParts[0], providerParts[1], providerParts[2]));
        }
    }

    private void generateTransactions(List<String> inputTransactions) {
        int index = 0;
        for (String inputTransaction : inputTransactions) {
            if (isIgnoredTransaction(inputTransaction)) {
                ignoredTransactions.put(index, inputTransaction);
            } else {
                String[] transactionParts = inputTransaction.split(" ");
                transactions.add(new Transaction(Date.valueOf(transactionParts[0]), ShippingInterface.PackageSize.valueOf(transactionParts[1]), transactionParts[2]));
            }
            index++;
        }
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public TreeMap<Integer, String> getIgnoredTransactions() {
        return ignoredTransactions;
    }

    public String getProviderPrice(String providerName, ShippingInterface.PackageSize packageSize) {
        for (Provider provider : providers) {
            if (provider.getName().equals(providerName) && provider.getSize().equals(packageSize)) {
                return provider.getPrice();
            }
        }
        return null;
    }

    private boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException parseException) {
            return false;
        }
        return true;
    }

    private boolean isValidPackageSize(String packageSize) {
        try {
            ShippingInterface.PackageSize.valueOf(packageSize);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private boolean isValidCarrier(String carrier) {
        for (Provider provider : getProviders()) {
            if (provider.getName().equals(carrier))
                return true;
        }
        return false;
    }

    private boolean isIgnoredTransaction(String transaction) {
        String[] transactionParts = transaction.split(" ");
        if (transactionParts.length != 3) {
            return true;
        }
        String transactionDate = transactionParts[0];
        String packageSize = transactionParts[1];
        String carrierName = transactionParts[2];

        return !isValidDate(transactionDate) || !isValidPackageSize(packageSize) || !isValidCarrier(carrierName);
    }
}

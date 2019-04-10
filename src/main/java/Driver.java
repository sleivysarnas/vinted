import module.DiscountModule;
import utilities.FileWorker;
import utilities.ModuleHelper;

public class Driver {

    public static void main(String[] args) {
        try {
            //Parse Transactions and Providers file to corresponding String lists (line by line)
            FileWorker fileWorker = new FileWorker();
            //Check those lists for data validity and restructurise to corresponding class Object lists
            ModuleHelper moduleHelper = new ModuleHelper(fileWorker.getInputTransactions(), fileWorker.getInputProviders());
            //Apply discount rules one by one and get final Transactions list (with applied discounts and final prices)
            new DiscountModule(moduleHelper);
            //Form an output file
            fileWorker.writeFile(moduleHelper.getTransactions(), moduleHelper.getIgnoredTransactions());
        } catch (Exception e) {
            System.err.println("Error!");
            e.printStackTrace();
        }
    }

}

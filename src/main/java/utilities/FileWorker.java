package utilities;

import module.Transaction;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileWorker {

    private static final String TRANSACTIONS_FILE = "input.txt";
    private static final String PROVIDERS_FILE = "providers.txt";

    private List<String> inputTransactions = new ArrayList<>();
    private List<String> inputProviders = new ArrayList<>();

    public FileWorker() {
        readFile(TRANSACTIONS_FILE);
        readFile(PROVIDERS_FILE);
    }


    private void readFile(String file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Scanner scanner = new Scanner(inputStream, "UTF-8");
            if (file.equals(TRANSACTIONS_FILE)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    inputTransactions.add(line);
                }
            } else {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    inputProviders.add(line);
                }
            }
            inputStream.close();
            scanner.close();
        } catch (IOException exception) {
            System.err.print("Error!");
            exception.printStackTrace();
        }
    }

    public void writeFile(List<Transaction> transactions, TreeMap<Integer, String> ignoredTransactions) throws IOException {
        List<String> lines = new ArrayList<>();
        int index;
        String ignoredTransaction;
        //Write all processed correct format transactions
        for (Transaction transaction : transactions) {
            lines.add(transaction.toString());
        }
        //Insert all ignored transactions to the results
        for (Map.Entry<Integer, String> entry : ignoredTransactions.entrySet()) {
            index = entry.getKey();
            ignoredTransaction = entry.getValue() + " Ignored";
            lines.add(index, ignoredTransaction);
        }
        Path file = Paths.get("outputTransactions.txt");
        Files.write(file, lines, Charset.forName("UTF-8"));
    }

    public List<String> getInputTransactions() {
        return inputTransactions;
    }

    public List<String> getInputProviders() {
        return inputProviders;
    }
}

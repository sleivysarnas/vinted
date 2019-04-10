package module;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction implements ShippingInterface {

    private final Date date;
    private final PackageSize size;
    private final String name;
    private String price;
    private String discount;

    public Transaction(Date date, PackageSize size, String name) {
        this.date = date;
        this.size = size;
        this.name = name;
        this.price = null;
        this.discount = null;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public PackageSize getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            return String.format("%s %s %s %s %s", dateFormat.format(date), size, name,
                    String.format("%.2f", Double.parseDouble(price)),
                    discount.equals("-")  ? "-" : String.format("%.2f", Double.parseDouble(discount)));
        } catch (Exception exception) {
            System.err.print("Error!");
            exception.printStackTrace();
        }
        return null;
    }
}
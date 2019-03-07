package module;

public class Provider implements ShippingInterface {
    private final String name;
    private final PackageSize size;
    private final String price;

    public Provider(String name, String size, String price) {
        this.name = name;
        this.size = PackageSize.valueOf(size);
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public PackageSize getSize() {
        return size;
    }

    public String getPrice() {
        return price;
    }

}

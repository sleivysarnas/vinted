package module;

public interface ShippingInterface {

    enum PackageSize {
        S, M, L;
    }

    PackageSize getSize();

    String getName();
}

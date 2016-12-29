package in.clayfish.printful.enums;

/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
public enum ProductType {
    POSTER, FRAMED_POSTER, CANVAS, T_SHIRT, MUG, EMBROIDERY, CUT_SEW, SUBLIMATION, PHONE_CASE;


    /**
     * Convenience method to convert string to ProductType using loose matching
     *
     * @param searchTerm Term to search
     * @return Found ProductType object
     */
    public static ProductType find(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return null;
        }

        searchTerm = searchTerm.replace("-", "").trim().toUpperCase();

        for (ProductType productType : ProductType.values()) {
            if (productType.name().replace("_", "").equalsIgnoreCase(searchTerm)) {
                return productType;
            }
        }

        for (ProductType productType : ProductType.values()) {
            if (productType.name().replace("_", "").contains(searchTerm)) {
                return productType;
            }
        }

        for (ProductType productType : ProductType.values()) {
            if (searchTerm.contains(productType.name().replace("_", ""))) {
                return productType;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return this.name().replace("_", "-");
    }
}

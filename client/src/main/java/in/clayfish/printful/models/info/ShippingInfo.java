package in.clayfish.printful.models.info;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class ShippingInfo {

    /**
     * Shipping method ID
     */
    private String id;

    /**
     * Shipping method name
     */
    private String name;

    /**
     * Shipping rate
     */
    private String rate;

    /**
     * Currency code in which the rate is returned (USD unless conversion was requested)
     */
    private String currency;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

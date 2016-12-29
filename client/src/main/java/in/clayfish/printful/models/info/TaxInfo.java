package in.clayfish.printful.models.info;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class TaxInfo {
    /**
     * Whether sales tax is required for the given address
     */
    private Boolean required;

    /**
     * Tax rate
     */
    private float rate;

    /**
     * This attribute is undocumented
     */
    private Boolean shippingTaxable;

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Boolean getShippingTaxable() {
        return shippingTaxable;
    }

    public void setShippingTaxable(Boolean shippingTaxable) {
        this.shippingTaxable = shippingTaxable;
    }
}

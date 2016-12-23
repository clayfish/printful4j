package in.clayfish.printful.models.info;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class CardInfo {
    /**
     * Payment card type
     */
    private String type;

    /**
     * Masked card number with only last 4 digits visible
     */
    private String numberMask;

    /**
     * Card expiry date
     */
    private String expires;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumberMask() {
        return numberMask;
    }

    public void setNumberMask(String numberMask) {
        this.numberMask = numberMask;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }
}

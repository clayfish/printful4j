package in.clayfish.printful.models.info;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class AddressInfo {

    /**
     * Address line 1
     */
    private String address1;


    private String city;
    private String countryCode;

    /**
     * optional
     */
    private String stateCode;

    /**
     * ZIP or postal code (optional, required for some countries to calculate expedited shipping rates)
     */
    private String zip;

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}

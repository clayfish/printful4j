package in.clayfish.printful.models.info;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class TaxAddressInfo {
    private String countryCode;
    private String stateCode;
    private String city;
    private String zip;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}

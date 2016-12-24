package in.clayfish.printful.models.includable;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class Address {

    /**
     * Full name
     */
    private String name;

    /**
     * Company name
     */
    private String company;

    /**
     * Address line 1
     */
    private String address1;

    /**
     * Address line 2
     */
    private String address2;
    private String city;
    private State state;
    private Country country;
    private String zip;
    private String phone;
    private String email;


    public Address() {
    }

    public Address(JSONObject json) throws JSONException {
        if (json.has("name")) {
            this.name = json.getString("name");
        }

        if (json.has("company")) {
            this.company = json.getString("company");
        }

        if (json.has("address1")) {
            this.address1 = json.getString("address1");
        }

        if (json.has("address2")) {
            this.address2 = json.getString("address2");
        }

        if (json.has("city")) {
            this.city = json.getString("city");
        }

        if (json.has("zip")) {
            this.zip = json.getString("zip");
        }

        if (json.has("phone")) {
            this.phone = json.getString("phone");
        }

        if (json.has("email")) {
            this.email = json.getString("email");
        }

        if (json.has("state_code") && json.has("state_name")) {
            this.state = new State(json.getString("state_code"), json.getString("state_name"));
        }

        if (json.has("country_code") && json.has("country_name")) {
            this.country = new Country(json.getString("country_code"), json.getString("country_name"));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        JSONStringer jsonStringer = new JSONStringer();

        try {
            jsonStringer.object();

            if (name != null) {
                jsonStringer.key("name");
                jsonStringer.value(name);
            }

            if (company != null) {
                jsonStringer.key("company");
                jsonStringer.value(company);
            }

            if (email != null) {
                jsonStringer.key("email");
                jsonStringer.value(email);
            }

            if (address1 != null) {
                jsonStringer.key("address1");
                jsonStringer.value(address1);
            }

            if (address2 != null) {
                jsonStringer.key("address2");
                jsonStringer.value(address2);
            }

            if (city != null) {
                jsonStringer.key("city");
                jsonStringer.value(city);
            }

            if (state != null) {
                jsonStringer.key("state_code");
                jsonStringer.value(state.getCode());

                jsonStringer.key("state_name");
                jsonStringer.value(state.getName());
            }

            if (country != null) {
                jsonStringer.key("country_code");
                jsonStringer.value(country.getCode());

                jsonStringer.key("country_name");
                jsonStringer.value(country.getName());
            }

            if (zip != null) {
                jsonStringer.key("zip");
                jsonStringer.value(zip);
            }

            if (phone != null) {
                jsonStringer.key("phone");
                jsonStringer.value(phone);
            }

            jsonStringer.endObject();

            return jsonStringer.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
}

package in.clayfish.printful.models.includable;

import in.clayfish.printful.exceptions.ValidationFailedException;

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

    /**
     * @author shuklaalok7
     * @since 2/01/2017
     */
    public static class Builder implements in.clayfish.printful.models.Builder<Address> {
        private Address address;
        private boolean checkValidity;

        public Builder() {
            this.address = new Address();
        }

        @Override
        public Builder check() {
            this.checkValidity = true;
            return this;
        }

        public Builder name(String name) {
            this.address.name = name;
            return this;
        }

        public Builder address1(String address1) {
            this.address.address1 = address1;
            return this;
        }

        public Builder address2(String address2) {
            this.address.address2 = address2;
            return this;
        }

        public Builder city(String city) {
            this.address.city = city;
            return this;
        }

        public Builder state(State state) {
            this.address.state = state;
            return this;
        }

        public Builder country(Country country) {
            this.address.country = country;
            return this;
        }

        public Builder zip(String zip) {
            this.address.zip = zip;
            return this;
        }

        public Builder email(String email) {
            this.address.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.address.phone = phone;
            return this;
        }

        /**
         * @param company
         * @return
         */
        public Builder company(String company) {
            this.address.company = company;
            return this;
        }

        @Override
        public Address build() {
            if (checkValidity) {
                checkValidity();
            }
            return this.address;
        }

        /**
         *
         */
        private void checkValidity() {
            if (this.address.country == null || this.address.country.getCode() == null || this.address.country.getCode().isEmpty()
                    || this.address.state == null || this.address.state.getCode() == null || this.address.state.getCode().isEmpty()
                    || this.address.name == null || this.address.name.isEmpty()
                    || this.address.city == null || this.address.city.isEmpty()
                    || this.address.address1 == null || this.address.address1.isEmpty()
                    || this.address.zip == null || this.address.zip.isEmpty()) {
                throw new ValidationFailedException();
            }
        }


    }

}

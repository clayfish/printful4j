package in.clayfish.printful.models;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class PackingSlip {

    /**
     * Customer service email
     */
    private String email;

    /**
     * Customer service phone (111-222-3333)
     */
    private String phone;

    /**
     * Custom packing slip message
     */
    private String message;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


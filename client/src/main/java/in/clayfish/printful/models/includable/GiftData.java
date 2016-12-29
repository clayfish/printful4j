package in.clayfish.printful.models.includable;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class GiftData {

    /**
     * Gift message title
     */
    private String subject;

    /**
     * Gift message text
     */
    private String message;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

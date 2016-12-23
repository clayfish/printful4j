package in.clayfish.printful.models.info;

import java.util.List;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class WebhookInfo {


    /**
     * Webhook URL that will receive store's event notifications
     */
    private String url;
    /**
     * Array of enabled webhook event types
     */
    private List<String> types;
//    params	array [ ]	Array of parameters for enabled webhook event types

}

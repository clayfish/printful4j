package in.clayfish.printful.models.includable;

import java.io.Serializable;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class State implements Serializable {
    private String code;
    private String name;

    public State(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}

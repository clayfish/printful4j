package in.clayfish.printful.models.includable;

import java.io.Serializable;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class State implements Serializable {
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

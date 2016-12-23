package in.clayfish.printful.models.includable;

import java.io.Serializable;
import java.util.List;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class Country implements Serializable {

    /**
     * Country code
     */
    private String code;

    /**
     * Country name
     */
    private String name;

    /**
     * List of states
     */
    private List<State> states;

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

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }
}


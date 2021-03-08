package helpers.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SS {

    @JsonProperty("S1")
    private String s1;

    @JsonProperty("S2")
    private String s2;

    public String getS1() {
        return s1;
    }

    public String getS2() {
        return s2;
    }
}
package helpers.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Value {

    @JsonProperty("S1")
    private int s1;

    @JsonProperty("S2")
    private int s2;

    public int getS1() {
        return s1;
    }

    public int getS2() {
        return s2;
    }
}
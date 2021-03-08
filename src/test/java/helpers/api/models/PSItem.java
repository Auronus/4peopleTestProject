package helpers.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PSItem {

    @JsonProperty("Value")
    private Value value;

    @JsonProperty("Key")
    private int key;

    public Value getValue() {
        return value;
    }

    public int getKey() {
        return key;
    }
}
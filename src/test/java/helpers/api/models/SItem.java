package helpers.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SItem {

    @JsonProperty("Value")
    private String value;

    @JsonProperty("Key")
    private String key;

    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }
}
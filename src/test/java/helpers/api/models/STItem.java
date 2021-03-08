package helpers.api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class STItem {

    @JsonProperty("Value")
    private List<ValueItem> value;

    @JsonProperty("Key")
    private int key;

    public List<ValueItem> getValue() {
        return value;
    }

    public int getKey() {
        return key;
    }
}
package helpers.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MISItem {

    @JsonProperty("V")
    private String V;

    @JsonProperty("K")
    private int K;

    public String getV() {
        return V;
    }

    public int getK() {
        return K;
    }
}
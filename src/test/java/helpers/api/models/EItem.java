package helpers.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EItem {

    @JsonProperty("P")
    private double P;

    @JsonProperty("C")
    private double C;

    @JsonProperty("T")
    private int T;

    @JsonProperty("G")
    private int G;
}
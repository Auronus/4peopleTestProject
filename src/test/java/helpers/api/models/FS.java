package helpers.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FS {

    @JsonProperty("S1")
    private int s1;

    @JsonProperty("S2")
    private int s2;
}
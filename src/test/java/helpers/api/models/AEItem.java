package helpers.api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AEItem {

    @JsonProperty("G")
    private int G;

    @JsonProperty("ME")
    private List<MEItem> mE;
}
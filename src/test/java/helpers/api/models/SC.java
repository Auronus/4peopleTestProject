package helpers.api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SC {

    @JsonProperty("TD")
    private int tD;

    @JsonProperty("PS")
    private List<Object> pS;

    @JsonProperty("S")
    private List<Object> S;

    @JsonProperty("I")
    private String I;

    @JsonProperty("FS")
    private FS fS;

    @JsonProperty("TS")
    private int tS;

    @JsonProperty("CPS")
    private String cPS;

    @JsonProperty("CP")
    private int cP;

    @JsonProperty("TR")
    private int tR;
}
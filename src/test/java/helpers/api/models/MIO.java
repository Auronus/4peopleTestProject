package helpers.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MIO {

    @JsonProperty("Loc")
    private String loc;

    @JsonProperty("TSt")
    private String tSt;

    @JsonProperty("MaF")
    private String maF;

    public String getLoc() {
        return loc;
    }

    public String getTSt() {
        return tSt;
    }

    public String getMaF() {
        return maF;
    }
}
package helpers.api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValueItem {

    @JsonProperty("O1C")
    private int o1C;

    @JsonProperty("O1")
    private String o1;

    @JsonProperty("O1E")
    private String o1E;

    @JsonProperty("O2")
    private String o2;

    @JsonProperty("DI")
    private String dI;

    @JsonProperty("E")
    private List<EItem> E;

    @JsonProperty("LR")
    private String lR;

    @JsonProperty("O1I")
    private int o1I;

    @JsonProperty("O1IS")
    private List<Integer> o1IS;

    @JsonProperty("I")
    private int I;

    @JsonProperty("O2IS")
    private List<Integer> o2IS;

    @JsonProperty("L")
    private String L;

    @JsonProperty("GNS")
    private boolean gNS;

    @JsonProperty("N")
    private int N;

    @JsonProperty("CHIMG")
    private String cHIMG;

    @JsonProperty("SC")
    private SC sC;

    @JsonProperty("O1IMG")
    private List<String> o1IMG;


    @JsonProperty("O1R")
    private String o1R;

    @JsonProperty("SE")
    private String sE;

    @JsonProperty("R")
    private int R;

    @JsonProperty("S")
    private int S;

    @JsonProperty("SI")
    private int sI;

    @JsonProperty("SIMG")
    private String sIMG;

    @JsonProperty("SN")
    private String sN;

    @JsonProperty("COI")
    private int cOI;

    @JsonProperty("EC")
    private int eC;

    @JsonProperty("KI")
    private int kI;

    @JsonProperty("CID")
    private int cID;

    @JsonProperty("CI")
    private int cI;

    @JsonProperty("SSI")
    private int sSI;

    @JsonProperty("SR")
    private String sR;

    @JsonProperty("O2C")
    private int o2C;

    @JsonProperty("HMH")
    private int hMH;

    @JsonProperty("O2E")
    private String o2E;

    @JsonProperty("AE")
    private List<Object> aE;

    @JsonProperty("MS")
    private List<Integer> mS;

    @JsonProperty("O2I")
    private int o2I;

    @JsonProperty("CN")
    private String cN;

    @JsonProperty("CO")
    private int cO;

    @JsonProperty("O2R")
    private String o2R;

    @JsonProperty("O2IMG")
    private List<String> o2IMG;

    @JsonProperty("LE")
    private String lE;

    @JsonProperty("TN")
    private String tN;

    @JsonProperty("LI")
    private int lI;

    @JsonProperty("ICY")
    private boolean iCY;

    @JsonProperty("VA")
    private int vA;

    @JsonProperty("VI")
    private String vI;

    @Override
    public boolean equals(Object obj) {
        return (lI == ((ValueItem) obj).getLI());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + lI;
        return result;
    }
}
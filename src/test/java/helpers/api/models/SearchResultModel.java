package helpers.api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResultModel {

    @JsonProperty("Value")
    private List<ValueItem> value;

    @JsonProperty("Error")
    private String error;

    @JsonProperty("ErrorCode")
    private int errorCode;

    @JsonProperty("Guid")
    private String guid;

    @JsonProperty("Id")
    private int id;

    @JsonProperty("Success")
    private boolean success;

    public List<ValueItem> getValue() {
        return value;
    }

    public String getError() {
        return error;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getGuid() {
        return guid;
    }

    public int getId() {
        return id;
    }

    public boolean isSuccess() {
        return success;
    }
}
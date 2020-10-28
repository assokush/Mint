
package com.mint.net.dto.hit_info;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "545451",
    "234233",
    "111111"
})
public class Payload {

    @JsonProperty("545451")
    private String _545451;
    @JsonProperty("234233")
    private String _234233;
    @JsonProperty("111111")
    private String _111111;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("545451")
    public String get545451() {
        return _545451;
    }

    @JsonProperty("545451")
    public void set545451(String _545451) {
        this._545451 = _545451;
    }

    @JsonProperty("234233")
    public String get234233() {
        return _234233;
    }

    @JsonProperty("234233")
    public void set234233(String _234233) {
        this._234233 = _234233;
    }

    @JsonProperty("111111")
    public String get111111() {
        return _111111;
    }

    @JsonProperty("111111")
    public void set111111(String _111111) {
        this._111111 = _111111;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

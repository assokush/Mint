
package com.mint.net.dto.card_details;

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
    "length",
    "luhn"
})
public class Number {

    @JsonProperty("length")
    private Integer length;
    @JsonProperty("luhn")
    private Boolean luhn;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("length")
    public Integer getLength() {
        return length;
    }

    @JsonProperty("length")
    public void setLength(Integer length) {
        this.length = length;
    }

    @JsonProperty("luhn")
    public Boolean getLuhn() {
        return luhn;
    }

    @JsonProperty("luhn")
    public void setLuhn(Boolean luhn) {
        this.luhn = luhn;
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

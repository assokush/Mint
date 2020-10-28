
package com.mint.net.model;

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
        "bin",
        "success",
        "payload"
})
public class CardDetailsModel {

    

	@JsonProperty("bin")
    private String bin;

    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("payload")
    private Payload payload;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    @JsonProperty("bin")
    public String getBin() {
        return bin;
    }

    @JsonProperty("bin")
    public void setBin(String bin) {
        this.bin = bin;
    }

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("payload")
    public Payload getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    @Override
	public String toString() {
		return "CardDetailsModel [bin=" + bin + ", success=" + success + ", payload=" + payload
				+ ", additionalProperties=" + additionalProperties + "]";
	}
}

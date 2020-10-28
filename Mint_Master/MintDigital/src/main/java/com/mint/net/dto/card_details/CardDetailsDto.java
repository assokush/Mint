
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
    "number",
    "scheme",
    "type",
    "brand",
    "prepaid",
    "country",
    "bank"
})
public class CardDetailsDto {

    @JsonProperty("number")
    private Number number;
    @JsonProperty("scheme")
    private String scheme;
    @JsonProperty("type")
    private String type;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("prepaid")
    private Boolean prepaid;
    @JsonProperty("country")
    private Country country;
    @JsonProperty("bank")
    private Bank bank;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("number")
    public Number getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(Number number) {
        this.number = number;
    }

    @JsonProperty("scheme")
    public String getScheme() {
        return scheme;
    }

    @JsonProperty("scheme")
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("brand")
    public String getBrand() {
        return brand;
    }

    @JsonProperty("brand")
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @JsonProperty("prepaid")
    public Boolean getPrepaid() {
        return prepaid;
    }

    @JsonProperty("prepaid")
    public void setPrepaid(Boolean prepaid) {
        this.prepaid = prepaid;
    }

    @JsonProperty("country")
    public Country getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(Country country) {
        this.country = country;
    }

    @JsonProperty("bank")
    public Bank getBank() {
        return bank;
    }

    @JsonProperty("bank")
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public CardDetailsDto() {
    }
}

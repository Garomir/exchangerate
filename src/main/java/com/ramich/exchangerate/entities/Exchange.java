package com.ramich.exchangerate.entities;

import com.fasterxml.jackson.databind.JsonNode;

public class Exchange {
    private String disclaimer;
    private String license;
    private String timestamp;
    private String base;
    private JsonNode rates;

    public Exchange() {
    }

    public Exchange(String disclaimer, String license, String timestamp, String base, JsonNode rates) {
        this.disclaimer = disclaimer;
        this.license = license;
        this.timestamp = timestamp;
        this.base = base;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public JsonNode getRates() {
        return rates;
    }

    public void setRates(JsonNode rates) {
        this.rates = rates;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

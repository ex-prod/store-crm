package ru.exprod.crm.controllers.model;


public class FilterRequest {
    String name;
    String code;
    String external_code;

    public FilterRequest(String name, String code, String external_code) {
        this.name = name;
        this.code = code;
        this.external_code = external_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExternal_code() {
        return external_code;
    }

    public void setExternal_code(String external_code) {
        this.external_code = external_code;
    }
}

package com.leverx.dealerstat.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    ADMIN("admin"), TRADER("trader");

    private String valueOfRole;

    Role(String valueOfRole) {
        this.valueOfRole = valueOfRole;
    }

    @JsonValue
    public String getValueOfRole() {
        return valueOfRole;
    }
}

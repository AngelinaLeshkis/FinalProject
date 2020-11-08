package com.leverx.dealerstat.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    ROLE_ADMIN("ADMIN"), ROLE_USER("USER");

    private String valueOfRole;

    Role(String valueOfRole) {
        this.valueOfRole = valueOfRole;
    }

    @JsonValue
    public String getValueOfRole() {
        return valueOfRole;
    }

}

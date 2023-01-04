package com.example.isa.configuration;

import com.example.isa.model.enums.UserTypes;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Principal {

    private final Long userId;
    private final UserTypes role;

    public Principal(Long userId, UserTypes role) {
        this.userId = userId;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public UserTypes getRole() {
        return role;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("userId", userId)
                .append("role", role)
                .toString();
    }
}

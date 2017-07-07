package com.brodma.web.security.domain;

import java.util.Arrays;

public enum RoleDesc {

    SUPER_USER("super"),
    ADMIN("admin"),
    UNKNOWN("unknown");

    private String desc;

    RoleDesc(String desc) {
        this.desc = desc;
    }

    public static RoleDesc toRole(final String desc) {
        if (desc == null || desc.isEmpty()) {
            return UNKNOWN;
        }
        return Arrays.stream(values())
                .filter(r -> r.getDesc().equalsIgnoreCase(desc)).findFirst().orElse(RoleDesc.UNKNOWN);
    }

    public static String toDesc(RoleDesc role) {
        return role.getDesc();
    }

    public String getDesc() {
        return desc;
    }
}

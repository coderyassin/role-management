package org.yascode.role_management.util;

public enum UserFieldType {
    STRING("string"),
    INTEGER("Integer"),
    DOUBLE("Double");
    private final String type;

    UserFieldType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}

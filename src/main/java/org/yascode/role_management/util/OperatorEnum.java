package org.yascode.role_management.util;

public enum OperatorEnum {
    EQUAL("equal"),
    NOT_EQUAL("not_equal"),
    LESS("less"),
    LESS_OR_EQUAL("less_or_equal"),
    GREATER("greater"),
    GREATER_OR_EQUAL("greater_or_equal"),
    IS_NULL("is_null"),
    IS_NOT_NULL("is_not_null"),
    CONTAINS("contains"),
    NOT_CONTAINS("not_contains"),
    BEGINS_WITH("begins_with"),
    NOT_BEGINS_WITH("not_begins_with"),
    ENDS_WITH("ends_with"),
    NOT_ENDS_WITH("not_ends_with"),
    IS_EMPTY("is_empty"),
    IS_NOT_EMPTY("is_not_empty"),
    BETWEEN("between"),
    IN("in"),
    NOT_IN("not_in");

    private final String value;

    OperatorEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static OperatorEnum fromValue(String value) {
        for (OperatorEnum operatorEnum : OperatorEnum.values()) {
            if (operatorEnum.value.equalsIgnoreCase(value)) {
                return operatorEnum;
            }
        }
        throw new IllegalArgumentException("Unknown operator: " + value);
    }
}

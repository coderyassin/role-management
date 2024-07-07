package org.yascode.role_management.role;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RoleEvaluator {
    public boolean evaluate(RoleRule roleRule, Map<String, Object> userAttributes) {
        for (Condition condition : roleRule.getConditions()) {
            String field = condition.getField();
            String operator = condition.getOperator();
            Object value = condition.getValue();
            Object userValue = userAttributes.get(field);

            if (!evaluateCondition(userValue, operator, value)) {
                return false;
            }
        }
        return true;
    }

    private boolean evaluateCondition(Object userValue, String operator, Object value) {
        switch (operator) {
            case "equals":
                return userValue.equals(value);
            case "greater_than":
                return ((Comparable) userValue).compareTo(value) > 0;
            case "less_than":
                return ((Comparable) userValue).compareTo(value) < 0;
            case "greater_than_or_equals":
                return ((Comparable) userValue).compareTo(value) >= 0;
            case "less_than_or_equals":
                return ((Comparable) userValue).compareTo(value) <= 0;
            case "contains":
                return ((String) userValue).contains((String) value);
            default:
                return false;
        }
    }
}

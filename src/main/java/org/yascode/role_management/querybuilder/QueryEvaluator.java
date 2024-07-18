package org.yascode.role_management.querybuilder;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QueryEvaluator {
    public Object evaluate(Query query, Map<String, Object> userMap) {
        List<String> errors = new ArrayList<>();
        boolean result = evaluateRules(query.getRules(), query.getCondition(), userMap, errors);
        return errors.isEmpty() ? result : errors;
    }

    private boolean evaluateRules(List<Rule> rules, String condition, Map<String, Object> userMap, List<String> errors) {
        boolean result = "AND".equalsIgnoreCase(condition);
        for (Rule rule : rules) {
            boolean ruleResult;

            if (Objects.nonNull(rule.getRules()) && !rule.getRules().isEmpty()) {
                ruleResult = evaluateRules(rule.getRules(), rule.getCondition(), userMap, errors);
            } else {
                ruleResult = evaluateRule(rule, userMap, errors);
            }

            if ("AND".equalsIgnoreCase(condition)) {
                result = result && ruleResult;
                if (!result) return false;
            } else if ("OR".equalsIgnoreCase(condition)) {
                result = result || ruleResult;
                if (result) return true;
            }
        }

        return result;
    }

    private boolean evaluateRule(Rule rule, Map<String, Object> userMap, List<String> errors) {
        Object value = rule.getValue();
        String field = rule.getField();
        String operator = rule.getOperator();
        Object fieldValue = userMap.get(field);

        if (fieldValue == null) {
            errors.add("Unknown field: " + field);
            return false;
        }

        if (value instanceof List) {
            if (fieldValue instanceof Integer) {
                return evaluateCondition((Integer) fieldValue, (List<Integer>) value, operator, errors, field);
            } else if (fieldValue instanceof Double) {
                return evaluateCondition((Double) fieldValue, (List<Double>) value, operator, errors, field);
            } else if (fieldValue instanceof String) {
                return evaluateCondition((String) fieldValue, (List<String>) value, operator, errors, field);
            }
        } else if (fieldValue instanceof String) {
            return evaluateCondition((String) fieldValue, (String) value, operator, errors, field);
        } else if (fieldValue instanceof Integer) {
            return evaluateCondition((Integer) fieldValue, (Integer) value, operator, errors, field);
        } else if (fieldValue instanceof Double) {
            return evaluateCondition((Double) fieldValue, (Double) value, operator, errors, field);
        } else if (fieldValue instanceof Boolean) {
            return evaluateCondition((Boolean) fieldValue, (Boolean) value, operator, errors, field);
        } else {
            errors.add("Unsupported field type: " + fieldValue.getClass().getName() + " for field: " + field);
            return false;
        }
        errors.add("Unsupported field type: " + fieldValue.getClass().getName() + " for field: " + field);
        return false;
    }

    private boolean evaluateCondition(double fieldValue, double ruleValue, String operator, List<String> errors, String field) {
        boolean result;
        switch (operator) {
            case "equal":
                result = fieldValue == ruleValue;
                if (!result) errors.add(field + " does not equal " + ruleValue);
                return result;
            case "not_equal":
                result = fieldValue != ruleValue;
                if (!result) errors.add(field + " equals " + ruleValue);
                return result;
            case "less":
                result = fieldValue < ruleValue;
                if (!result) errors.add(field + " is not less than " + ruleValue);
                return result;
            case "less_or_equal":
                result = fieldValue <= ruleValue;
                if (!result) errors.add(field + " is not less than or equal to " + ruleValue);
                return result;
            case "greater":
                result = fieldValue > ruleValue;
                if (!result) errors.add(field + " is not greater than " + ruleValue);
                return result;
            case "greater_or_equal":
                result = fieldValue >= ruleValue;
                if (!result) errors.add(field + " is not greater than or equal to " + ruleValue);
                return result;
            case "is_null":
                result = Objects.isNull(fieldValue);
                if (!result) errors.add(field + " is not null");
                return result;
            case "is_not_null":
                result = Objects.nonNull(fieldValue);
                if (!result) errors.add(field + " is null");
                return result;
            default:
                errors.add("Unknown operator: " + operator + " for field: " + field);
                return false;
        }
    }

    private boolean evaluateCondition(int fieldValue, int ruleValue, String operator, List<String> errors, String field) {
        boolean result;
        switch (operator) {
            case "equal":
                result = fieldValue == ruleValue;
                if (!result) errors.add(field + " does not equal " + ruleValue);
                return result;
            case "not_equal":
                result = fieldValue != ruleValue;
                if (!result) errors.add(field + " equals " + ruleValue);
                return result;
            case "less":
                result = fieldValue < ruleValue;
                if (!result) errors.add(field + " is not less than " + ruleValue);
                return result;
            case "less_or_equal":
                result = fieldValue <= ruleValue;
                if (!result) errors.add(field + " is not less than or equal to " + ruleValue);
                return result;
            case "greater":
                result = fieldValue > ruleValue;
                if (!result) errors.add(field + " is not greater than " + ruleValue);
                return result;
            case "greater_or_equal":
                result = fieldValue >= ruleValue;
                if (!result) errors.add(field + " is not greater than or equal to " + ruleValue);
                return result;
            case "is_null":
                result = Objects.isNull(fieldValue);
                if (!result) errors.add(field + " is not null");
                return result;
            case "is_not_null":
                result = Objects.nonNull(fieldValue);
                if (!result) errors.add(field + " is null");
                return result;
            default:
                errors.add("Unknown operator: " + operator + " for field: " + field);
                return false;
        }
    }

    private boolean evaluateCondition(String fieldValue, String ruleValue, String operator, List<String> errors, String field) {
        boolean result;
        switch (operator) {
            case "equal":
                result = fieldValue.equals(ruleValue);
                if (!result) errors.add(field + " does not equal " + ruleValue);
                return result;
            case "not_equal":
                result = !fieldValue.equals(ruleValue);
                if (!result) errors.add(field + " equals " + ruleValue);
                return result;
            case "contains":
                result = fieldValue.contains(ruleValue);
                if (!result) errors.add(field + " does not contain " + ruleValue);
                return result;
            case "not_contains":
                result = !fieldValue.contains(ruleValue);
                if (!result) errors.add(field + " contains " + ruleValue);
                return result;
            case "begins_with":
                result = fieldValue.startsWith(ruleValue);
                if (!result) errors.add(field + " does not begin with " + ruleValue);
                return result;
            case "not_begins_with":
                result = !fieldValue.startsWith(ruleValue);
                if (!result) errors.add(field + " begins with " + ruleValue);
                return result;
            case "ends_with":
                result = fieldValue.endsWith(ruleValue);
                if (!result) errors.add(field + " does not end with " + ruleValue);
                return result;
            case "not_ends_with":
                result = !fieldValue.endsWith(ruleValue);
                if (!result) errors.add(field + " ends with " + ruleValue);
                return result;
            case "is_empty":
                result = ruleValue.isEmpty();
                if (!result) errors.add(field + " is not empty");
                return result;
            case "is_not_empty":
                result = !ruleValue.isEmpty();
                if (!result) errors.add(field + " is empty");
                return result;
            case "is_null":
                result = Objects.isNull(fieldValue);
                if (!result) errors.add(field + " is not null");
                return result;
            case "is_not_null":
                result = Objects.nonNull(fieldValue);
                if (!result) errors.add(field + " is null");
                return result;
            default:
                errors.add("Unknown operator: " + operator + " for field: " + field);
                return false;
        }
    }

    private boolean evaluateCondition(double fieldValue, List<Double> ruleValue, String operator, List<String> errors, String field) {
        boolean result;
        switch (operator) {
            case "between":
                result = fieldValue >= ruleValue.get(0) && fieldValue <= ruleValue.get(1);
                if (!result) errors.add(field + " is not between " + ruleValue.get(0) + " and " + ruleValue.get(1));
                return result;
            case "in":
                result = ruleValue.stream().anyMatch(value -> value.equals(fieldValue));
                if (!result) errors.add(field + " is not in the list of values");
                return result;
            case "not_in":
                result = ruleValue.stream().noneMatch(value -> value.equals(fieldValue));
                if (!result) errors.add(field + " is in the list of values");
                return result;
            default:
                errors.add("Unknown operator: " + operator + " for field: " + field);
                return false;
        }
    }

    private boolean evaluateCondition(int fieldValue, List<Integer> ruleValue, String operator, List<String> errors, String field) {
        boolean result;
        switch (operator) {
            case "between":
                result = fieldValue >= ruleValue.get(0) && fieldValue <= ruleValue.get(1);
                if (!result) errors.add(field + " is not between " + ruleValue.get(0) + " and " + ruleValue.get(1));
                return result;
            case "in":
                result = ruleValue.stream().anyMatch(value -> value.equals(fieldValue));
                if (!result) errors.add(field + " is not in the list of values");
                return result;
            case "not_in":
                result = ruleValue.stream().noneMatch(value -> value.equals(fieldValue));
                if (!result) errors.add(field + " is in the list of values");
                return result;
            default:
                errors.add("Unknown operator: " + operator + " for field: " + field);
                return false;
        }
    }

    private boolean evaluateCondition(String fieldValue, List<String> ruleValue, String operator, List<String> errors, String field) {
        boolean result;
        switch (operator) {
            case "in":
                result = ruleValue.stream().anyMatch(value -> value.equals(fieldValue));
                if (!result) errors.add(field + " is not in the list of values");
                return result;
            case "not_in":
                result = ruleValue.stream().noneMatch(value -> value.equals(fieldValue));
                if (!result) errors.add(field + " is in the list of values");
                return result;
            default:
                errors.add("Unknown operator: " + operator + " for field: " + field);
                return false;
        }
    }

    private boolean evaluateCondition(boolean fieldValue, boolean ruleValue, String operator, List<String> errors, String field) {
        boolean result;
        switch (operator) {
            case "equal":
                result = Objects.equals(fieldValue, ruleValue);
                if (!result) errors.add(field + " does not equal " + ruleValue);
                return result;
            case "not_equal":
                result = !Objects.equals(fieldValue, ruleValue);
                if (!result) errors.add(field + " equals " + ruleValue);
                return result;
            default:
                errors.add("Unknown operator: " + operator + " for field: " + field);
                return false;
        }
    }
}

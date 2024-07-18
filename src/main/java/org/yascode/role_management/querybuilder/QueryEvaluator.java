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
            errors.add( "Unknown field: " + field);
            return false;
        }

        if(value instanceof List) {
            if (fieldValue instanceof Integer) {
                return evaluateCondition((Integer) fieldValue, (List<Integer>) value, operator, errors);
            } else if(fieldValue instanceof Double) {
                return evaluateCondition((Integer) fieldValue, (List<Double>) value, operator, errors);
            } else if(fieldValue instanceof String) {
                return evaluateCondition((String) fieldValue, (List<String>) value, operator, errors);
            }
        } else if (fieldValue instanceof String) {
            return evaluateCondition((String) fieldValue, (String) value, operator, errors);
        } else if (fieldValue instanceof Integer) {
            return evaluateCondition((Integer) fieldValue, (Integer) value, operator, errors);
        } else if (fieldValue instanceof Double) {
            return evaluateCondition((Double) fieldValue, (Double) value, operator, errors);
        } else if (fieldValue instanceof Boolean) {
            return evaluateCondition((Boolean) fieldValue, (Boolean) value, operator, errors);
        } else if(true) {
            return true;
        } else {
            errors.add( "Unsupported field type: " + fieldValue.getClass().getName());
            return false;
        }
        errors.add( "Unsupported field type: " + fieldValue.getClass().getName());
        return false;
    }

    private boolean evaluateCondition(double fieldValue, double ruleValue, String operator, List<String> errors) {
        switch (operator) {
            case "equal":
                return fieldValue == ruleValue;
            case "not_equal":
                return fieldValue != ruleValue;
            case "less":
                return fieldValue < ruleValue;
            case "less_or_equal":
                return fieldValue <= ruleValue;
            case "greater":
                return fieldValue > ruleValue;
            case "greater_or_equal":
                return fieldValue >= ruleValue;
            case "is_null":
                return Objects.isNull(fieldValue);
            case "is_not_null":
                return Objects.nonNull(fieldValue);
            default:
                errors.add( "Unknown operator: " + operator);
                return false;
        }
    }

    private boolean evaluateCondition(int fieldValue, int ruleValue, String operator, List<String> errors) {
        switch (operator) {
            case "equal":
                return fieldValue == ruleValue;
            case "not_equal":
                return fieldValue != ruleValue;
            case "less":
                return fieldValue < ruleValue;
            case "less_or_equal":
                return fieldValue <= ruleValue;
            case "greater":
                return fieldValue > ruleValue;
            case "greater_or_equal":
                return fieldValue >= ruleValue;
            case "is_null":
                return Objects.isNull(fieldValue);
            case "is_not_null":
                return Objects.nonNull(fieldValue);
            default:
                errors.add( "Unknown operator: " + operator);
                return false;
        }
    }

    private boolean evaluateCondition(String fieldValue, String ruleValue, String operator, List<String> errors) {
        switch (operator) {
            case "equal":
                return fieldValue.equals(ruleValue);
            case "not_equal":
                return !fieldValue.equals(ruleValue);
            case "contains":
                return fieldValue.contains(ruleValue);
            case "not_contains":
                return !fieldValue.contains(ruleValue);
            case "begins_with":
                return fieldValue.startsWith(ruleValue);
            case "not_begins_with":
                return !fieldValue.startsWith(ruleValue);
            case "ends_with":
                return fieldValue.endsWith(ruleValue);
            case "not_ends_with":
                return !fieldValue.endsWith(ruleValue);
            case "is_empty":
                return ruleValue.isEmpty();
            case "is_not_empty":
                return !ruleValue.isEmpty();
            case "is_null":
                return Objects.isNull(fieldValue);
            case "is_not_null":
                return Objects.nonNull(fieldValue);
            default:
                errors.add( "Unknown operator: " + operator);
                return false;
        }
    }

    private boolean evaluateCondition(double fieldValue, List<Double> ruleValue, String operator, List<String> errors) {
        switch (operator) {
            case "between":
                return fieldValue >= ruleValue.get(0) && fieldValue <= ruleValue.get(1);
            case "in":
                return ruleValue.stream().anyMatch(value -> value.equals(fieldValue));
            case "not_in":
                return ruleValue.stream().noneMatch(value -> value.equals(fieldValue));
            default:
                errors.add( "Unknown operator: " + operator);
                return false;
        }
    }

    private boolean evaluateCondition(int fieldValue, List<Integer> ruleValue, String operator, List<String> errors) {
        switch (operator) {
            case "between":
                return fieldValue >= ruleValue.get(0) && fieldValue <= ruleValue.get(1);
            case "in":
                return ruleValue.stream().anyMatch(value -> value.equals(fieldValue));
            case "not_in":
                return ruleValue.stream().noneMatch(value -> value.equals(fieldValue));
            default:
                errors.add( "Unknown operator: " + operator);
                return false;
        }
    }

    private boolean evaluateCondition(String fieldValue, List<String> ruleValue, String operator, List<String> errors) {
        switch (operator) {
            case "in":
                return ruleValue.stream().anyMatch(value -> value.equals(fieldValue));
            case "not_in":
                return ruleValue.stream().noneMatch(value -> value.equals(fieldValue));
            default:
                errors.add( "Unknown operator: " + operator);
                return false;
        }
    }

    private boolean evaluateCondition(boolean fieldValue, boolean ruleValue, String operator, List<String> errors) {
        switch (operator) {
            case "equal":
                return Objects.equals(fieldValue, ruleValue);
            case "not_equal":
                return !Objects.equals(fieldValue, ruleValue);
            default:
                errors.add( "Unknown operator: " + operator);
                return false;
        }
    }
}

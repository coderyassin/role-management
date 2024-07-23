package org.yascode.role_management.querybuilder;

import org.springframework.stereotype.Component;
import org.yascode.role_management.model.Evaluation;

import java.util.*;

import static org.yascode.role_management.util.Operator.*;

@Component
public class QueryEvaluator {
    public Evaluation evaluate(Query query, Map<String, Object> userMap) {
        List<String> errors = new ArrayList<>();
        return Evaluation.builder()
                .valid(evaluateRules(query.getRules(), query.getCondition(), userMap, errors))
                .errors(errors)
                .build();
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
                return evaluateCondition(String.valueOf(fieldValue), (List<String>) value, operator, errors, field);
            }
        } else if (fieldValue instanceof String) {
            return evaluateCondition(String.valueOf(fieldValue), String.valueOf(value), operator, errors, field);
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
            case EQUAL:
                result = fieldValue == ruleValue;
                if (!result) errors.add(field + " does not equal " + ruleValue);
                return result;
            case NOT_EQUAL:
                result = fieldValue != ruleValue;
                if (!result) errors.add(field + " equals " + ruleValue);
                return result;
            case LESS:
                result = fieldValue < ruleValue;
                if (!result) errors.add(field + " is not less than " + ruleValue);
                return result;
            case LESS_OR_EQUAL:
                result = fieldValue <= ruleValue;
                if (!result) errors.add(field + " is not less than or equal to " + ruleValue);
                return result;
            case GREATER:
                result = fieldValue > ruleValue;
                if (!result) errors.add(field + " is not greater than " + ruleValue);
                return result;
            case GREATER_OR_EQUAL:
                result = fieldValue >= ruleValue;
                if (!result) errors.add(field + " is not greater than or equal to " + ruleValue);
                return result;
            case IS_NULL:
                result = Objects.isNull(fieldValue);
                if (!result) errors.add(field + " is not null");
                return result;
            case IS_NOT_NULL:
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
            case EQUAL:
                result = fieldValue == ruleValue;
                if (!result) errors.add(field + " does not equal " + ruleValue);
                return result;
            case NOT_EQUAL:
                result = fieldValue != ruleValue;
                if (!result) errors.add(field + " equals " + ruleValue);
                return result;
            case LESS:
                result = fieldValue < ruleValue;
                if (!result) errors.add(field + " is not less than " + ruleValue);
                return result;
            case LESS_OR_EQUAL:
                result = fieldValue <= ruleValue;
                if (!result) errors.add(field + " is not less than or equal to " + ruleValue);
                return result;
            case GREATER:
                result = fieldValue > ruleValue;
                if (!result) errors.add(field + " is not greater than " + ruleValue);
                return result;
            case GREATER_OR_EQUAL:
                result = fieldValue >= ruleValue;
                if (!result) errors.add(field + " is not greater than or equal to " + ruleValue);
                return result;
            case IS_NULL:
                result = Objects.isNull(fieldValue);
                if (!result) errors.add(field + " is not null");
                return result;
            case IS_NOT_NULL:
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
            case EQUAL:
                result = fieldValue.equals(ruleValue);
                if (!result) errors.add(field + " does not equal " + ruleValue);
                return result;
            case NOT_EQUAL:
                result = !fieldValue.equals(ruleValue);
                if (!result) errors.add(field + " equals " + ruleValue);
                return result;
            case CONTAINS:
                result = fieldValue.contains(ruleValue);
                if (!result) errors.add(field + " does not contain " + ruleValue);
                return result;
            case NOT_CONTAINS:
                result = !fieldValue.contains(ruleValue);
                if (!result) errors.add(field + " contains " + ruleValue);
                return result;
            case BEGINS_WITH:
                result = fieldValue.startsWith(ruleValue);
                if (!result) errors.add(field + " does not begin with " + ruleValue);
                return result;
            case NOT_BEGINS_WITH:
                result = !fieldValue.startsWith(ruleValue);
                if (!result) errors.add(field + " begins with " + ruleValue);
                return result;
            case ENDS_WITH:
                result = fieldValue.endsWith(ruleValue);
                if (!result) errors.add(field + " does not end with " + ruleValue);
                return result;
            case NOT_ENDS_WITH:
                result = !fieldValue.endsWith(ruleValue);
                if (!result) errors.add(field + " ends with " + ruleValue);
                return result;
            case IS_EMPTY:
                result = ruleValue.isEmpty();
                if (!result) errors.add(field + " is not empty");
                return result;
            case IS_NOT_EMPTY:
                result = !ruleValue.isEmpty();
                if (!result) errors.add(field + " is empty");
                return result;
            case IS_NULL:
                result = Objects.isNull(fieldValue);
                if (!result) errors.add(field + " is not null");
                return result;
            case IS_NOT_NULL:
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
            case BETWEEN:
                result = fieldValue >= ruleValue.get(0) && fieldValue <= ruleValue.get(1);
                if (!result) errors.add(field + " is not between " + ruleValue.get(0) + " and " + ruleValue.get(1));
                return result;
            case IN:
                result = ruleValue.stream().anyMatch(value -> value.equals(fieldValue));
                if (!result) errors.add(field + " is not in the list of values");
                return result;
            case NOT_IN:
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
            case BETWEEN:
                result = fieldValue >= ruleValue.get(0) && fieldValue <= ruleValue.get(1);
                if (!result) errors.add(field + " is not between " + ruleValue.get(0) + " and " + ruleValue.get(1));
                return result;
            case IN:
                result = ruleValue.stream().anyMatch(value -> value.equals(fieldValue));
                if (!result) errors.add(field + " is not in the list of values");
                return result;
            case NOT_IN:
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
            case IN:
                result = ruleValue.stream().anyMatch(value -> value.equals(fieldValue));
                if (!result) errors.add(field + " is not in the list of values");
                return result;
            case NOT_IN:
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
            case EQUAL:
                result = Objects.equals(fieldValue, ruleValue);
                if (!result) errors.add(field + " does not equal " + ruleValue);
                return result;
            case NOT_EQUAL:
                result = !Objects.equals(fieldValue, ruleValue);
                if (!result) errors.add(field + " equals " + ruleValue);
                return result;
            default:
                errors.add("Unknown operator: " + operator + " for field: " + field);
                return false;
        }
    }
}

package org.yascode.role_management.querybuilder;

import org.springframework.stereotype.Component;
import org.yascode.role_management.dto.ValueUserDto;
import org.yascode.role_management.model.Evaluation;
import org.yascode.role_management.util.UserFieldType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.yascode.role_management.util.OperatorUtil.*;

@Component
public class QueryEvaluator {
    public Evaluation evaluate(Query query, List<ValueUserDto> valuesUser) {
        List<String> errors = new ArrayList<>();
        return Evaluation.builder()
                .valid(evaluateRules(query.getRules(), query.getCondition(), valuesUser, errors))
                .errors(errors)
                .build();
    }

    private boolean evaluateRules(List<Rule> rules, String condition, List<ValueUserDto> valuesUser, List<String> errors) {
        boolean result = "AND".equalsIgnoreCase(condition);
        for (Rule rule : rules) {
            boolean ruleResult;

            if (Objects.nonNull(rule.getRules()) && !rule.getRules().isEmpty()) {
                ruleResult = evaluateRules(rule.getRules(), rule.getCondition(), valuesUser, errors);
            } else {
                ruleResult = evaluateRule(rule, valuesUser, errors);
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

    private boolean evaluateRule(Rule rule, List<ValueUserDto> valuesUser, List<String> errors) {
        String field = rule.getField();
        String operator = rule.getOperator();
        ValueUserDto valueUserDto = valuesUser.stream()
                .filter(v -> v.getField().equals(field)).findFirst().orElse(null);

        if (valueUserDto == null) {
            errors.add("Unknown field: " + field);
            return false;
        }

        String fieldValue = String.valueOf(valueUserDto.getValue());
        UserFieldType type = valueUserDto.getType();

        return evaluateCondition(fieldValue, rule.getValue(), operator, type, errors, field);
    }

    private boolean evaluateCondition(String fieldValue, Object ruleValue, String operator, UserFieldType type, List<String> errors, String field) {
        try {
            switch (type) {
                case STRING:
                    return evaluateStringCondition(fieldValue, ruleValue, operator, errors, field);
                case INTEGER:
                    int fieldValueInt = Integer.parseInt(fieldValue);
                    return evaluateNumericCondition(fieldValueInt, ruleValue, operator, errors, field);
                case DOUBLE:
                    double fieldValueDouble = Double.parseDouble(fieldValue);
                    return evaluateNumericCondition(fieldValueDouble, ruleValue, operator, errors, field);
                case BOOLEAN:
                    boolean fieldValueBool = Boolean.parseBoolean(fieldValue);
                    return evaluateBooleanCondition(fieldValueBool, ruleValue, operator, errors, field);
                default:
                    errors.add("Unsupported field type: " + type + " for field: " + field);
                    return false;
            }
        } catch (NumberFormatException e) {
            errors.add("Invalid number format for field: " + field);
            return false;
        }
    }

    private boolean evaluateStringCondition(String fieldValue, Object ruleValue, String operator, List<String> errors, String field) {
        String ruleValueStr = String.valueOf(ruleValue);
        switch (operator) {
            case EQUAL:
                return fieldValue.equals(ruleValueStr);
            case NOT_EQUAL:
                return !fieldValue.equals(ruleValueStr);
            case CONTAINS:
                return fieldValue.contains(ruleValueStr);
            case NOT_CONTAINS:
                return !fieldValue.contains(ruleValueStr);
            case BEGINS_WITH:
                return fieldValue.startsWith(ruleValueStr);
            case NOT_BEGINS_WITH:
                return !fieldValue.startsWith(ruleValueStr);
            case ENDS_WITH:
                return fieldValue.endsWith(ruleValueStr);
            case NOT_ENDS_WITH:
                return !fieldValue.endsWith(ruleValueStr);
            case IS_EMPTY:
                return fieldValue.isEmpty();
            case IS_NOT_EMPTY:
                return !fieldValue.isEmpty();
            case IN:
            case NOT_IN:
                if (ruleValue instanceof List) {
                    List<?> ruleValueList = (List<?>) ruleValue;
                    boolean contains = ruleValueList.contains(fieldValue);
                    return operator.equals(IN) ? contains : !contains;
                }
            default:
                errors.add("Unsupported operator for string: " + operator + " for field: " + field);
                return false;
        }
    }

    private <T extends Comparable<T>> boolean evaluateNumericCondition(T fieldValue, Object ruleValue, String operator, List<String> errors, String field) {
        if (ruleValue instanceof List) {
            List<?> ruleValueList = (List<?>) ruleValue;
            switch (operator) {
                case BETWEEN:
                    if (ruleValueList.size() == 2) {
                        T min = (T) ruleValueList.get(0);
                        T max = (T) ruleValueList.get(1);
                        return fieldValue.compareTo(min) >= 0 && fieldValue.compareTo(max) <= 0;
                    }
                case IN, NOT_IN:
                    boolean contains = ruleValueList.contains(fieldValue);
                    return operator.equals(IN) ? contains : !contains;
            }
        } else {
            T ruleValueT = (T) ruleValue;
            switch (operator) {
                case EQUAL:
                    return fieldValue.compareTo(ruleValueT) == 0;
                case NOT_EQUAL:
                    return fieldValue.compareTo(ruleValueT) != 0;
                case LESS:
                    return fieldValue.compareTo(ruleValueT) < 0;
                case LESS_OR_EQUAL:
                    return fieldValue.compareTo(ruleValueT) <= 0;
                case GREATER:
                    return fieldValue.compareTo(ruleValueT) > 0;
                case GREATER_OR_EQUAL:
                    return fieldValue.compareTo(ruleValueT) >= 0;
            }
        }
        errors.add("Unsupported operator for numeric: " + operator + " for field: " + field);
        return false;
    }

    private boolean evaluateBooleanCondition(boolean fieldValue, Object ruleValue, String operator, List<String> errors, String field) {
        if (ruleValue instanceof Boolean ruleValueBool) {
            return switch (operator) {
                case EQUAL -> fieldValue == ruleValueBool;
                case NOT_EQUAL -> fieldValue != ruleValueBool;
                default -> {
                    errors.add("Unsupported operator for boolean: " + operator + " for field: " + field);
                    yield false;
                }
            };
        }
        errors.add("Invalid boolean value for field: " + field);
        return false;
    }
}

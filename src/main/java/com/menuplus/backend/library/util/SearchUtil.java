package com.menuplus.backend.library.util;

import com.menuplus.backend.library.common.Constant;
import com.menuplus.backend.library.dto.SearchCondition;
import com.menuplus.backend.library.enumeration.OperatorType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SearchUtil {

    // This method is used to get the search predicate for the native query.
    public static String getNativeSearchPredicate(SearchCondition condition) {
        String fieldName = NamingUtil.camelCaseToSnakeCase(condition.getFieldName());
        return switch (condition.getOperandType()) {
            case STRING, ENUM -> getStringPredicate(fieldName, condition);
            case INTEGER, DECIMAL -> getNumericPredicate(fieldName, condition);
            case BOOLEAN -> getBooleanPredicate(fieldName, condition);
            case DATE, DATETIME -> getDatePredicate(fieldName, condition);
            default -> "1=1";
        };
    }

    private static String getStringPredicate(String fieldName, SearchCondition condition) {
        return switch (condition.getOperatorType()) {
            case CONTAIN ->
                    "LOWER(" + fieldName + ") LIKE CONCAT('%', '" + condition.getData().toLowerCase() + "', '%')";
            case START_WITH -> "LOWER(" + fieldName + ") LIKE CONCAT('" + condition.getData().toLowerCase() + "', '%')";
            case END_WITH -> "LOWER(" + fieldName + ") LIKE CONCAT('%', '" + condition.getData().toLowerCase() + "')";
            case EQUAL -> fieldName + " = '" + condition.getData() + "'";
            default -> "1=1";
        };
    }

    private static String getNumericPredicate(String fieldName, SearchCondition condition) {
        return switch (condition.getOperatorType()) {
            case EQUAL -> fieldName + " = " + condition.getData();
            case GREATER_EQUAL -> fieldName + " >= " + condition.getData();
            case LESS_EQUAL -> fieldName + " <= " + condition.getData();
            case BETWEEN ->
                    fieldName + " BETWEEN " + condition.getDatas().get(0) + " AND " + condition.getDatas().get(1);
            default -> "1=1";
        };
    }

    private static String getBooleanPredicate(String fieldName, SearchCondition condition) {
        if (condition.getOperatorType() == OperatorType.EQUAL) {
            return fieldName + " = " + condition.getData();
        }
        return "1=1";
    }

    private static String getDatePredicate(String fieldName, SearchCondition condition) {
        return switch (condition.getOperatorType()) {
            case EQUAL -> fieldName + " = '" + condition.getData() + "'";
            case GREATER_EQUAL -> fieldName + " >= '" + condition.getData() + "'";
            case LESS_EQUAL -> fieldName + " <= '" + condition.getData() + "'";
            case BETWEEN ->
                    fieldName + " BETWEEN '" + condition.getDatas().get(0) + "' AND '" + condition.getDatas().get(1) + "'";
            default -> "1=1";
        };
    }

    public static String format(String data) {
        return "%" + data.toLowerCase() + "%";
    }

    public static Predicate getSearchPredicate(CriteriaBuilder builder, Expression expression, SearchCondition condition) {
        return switch (condition.getOperandType()) {
            case STRING -> getStringPredicate(builder, expression, condition);
            case DATETIME -> getDateTimePredicate(builder, expression, condition);
            case DATE -> getDatePredicate(builder, expression, condition);
            case BOOLEAN -> getBoolPredicate(builder, expression, condition);
            case DECIMAL -> getDecimalPredicate(builder, expression, condition);
            case INTEGER -> getIntegerPredicate(builder, expression, condition);
            case ENUM -> getEnumPredicate(builder, expression, condition);
            default -> null;
        };
    }

    private static Predicate getStringPredicate(CriteriaBuilder builder, Expression<?> expression, SearchCondition condition) {
        return switch (condition.getOperatorType()) {
            case CONTAIN -> builder.like(builder.lower(expression.as(String.class)), format(condition.getData()));
            case START_WITH -> {
                String formatStartSearch = condition.getData().toLowerCase() + "%";
                yield builder.like(builder.lower(expression.as(String.class)), formatStartSearch);
            }
            case END_WITH -> {
                String formatEndSearch = "%" + condition.getData().toLowerCase();
                yield builder.like(builder.lower(expression.as(String.class)), formatEndSearch);
            }
            case EQUAL -> builder.equal(expression.as(String.class), condition.getData());
            default -> null;
        };
    }

    private static Predicate getEnumPredicate(CriteriaBuilder builder, Expression<?> expression, SearchCondition condition) {
        String data = condition.getData().trim().replaceAll(Constant.BLANK_REGEX, Constant.DASH);

        return switch (condition.getOperatorType()) {
            case CONTAIN -> builder.like(builder.lower(expression.as(String.class)), format(data));
            case START_WITH -> {
                String formatStartSearch = data.toLowerCase() + "%";
                yield builder.like(builder.lower(expression.as(String.class)), formatStartSearch);
            }
            case END_WITH -> {
                String formatEndSearch = "%" + data.toLowerCase();
                yield builder.like(builder.lower(expression.as(String.class)), formatEndSearch);
            }
            default -> null;
        };
    }

    private static Predicate getDateTimePredicate(CriteriaBuilder builder, Expression expression, SearchCondition condition) {
        if (condition.getOperatorType() == OperatorType.BETWEEN) {
            return builder.and(
                    builder.greaterThanOrEqualTo(expression, parseDateTime(condition.getDatas().get(0))),
                    builder.lessThanOrEqualTo(expression, parseDateTime(condition.getDatas().get(1)))
            );
        }
        return switch (condition.getOperatorType()) {
            case GREATER_EQUAL -> builder.greaterThanOrEqualTo(expression, parseDateTime(condition.getData()));
            case LESS_EQUAL -> builder.lessThanOrEqualTo(expression, parseDateTime(condition.getData()));
            case EQUAL -> builder.equal(expression, parseDateTime(condition.getData()));
            default -> null;
        };
    }

    private static Predicate getDatePredicate(CriteriaBuilder builder, Expression expression, SearchCondition condition) {
        if (condition.getOperatorType() == OperatorType.BETWEEN) {
            return builder.and(
                    builder.greaterThanOrEqualTo(expression, parseDate(condition.getDatas().get(0))),
                    builder.lessThanOrEqualTo(expression, parseDate(condition.getDatas().get(1)))
            );
        }
        return switch (condition.getOperatorType()) {
            case GREATER_EQUAL -> builder.greaterThanOrEqualTo(expression, parseDate(condition.getData()));
            case LESS_EQUAL -> builder.lessThanOrEqualTo(expression, parseDate(condition.getData()));
            case EQUAL -> builder.equal(expression, parseDate(condition.getData()));
            default -> null;
        };
    }

    private static Predicate getBoolPredicate(CriteriaBuilder builder, Expression expression, SearchCondition condition) {
        if (condition.getOperatorType() == OperatorType.EQUAL) {
            var data = parseBool(condition.getData());
            if (BooleanUtil.isTrue(data)) {
                return builder.isTrue(expression);
            }
            return builder.isFalse(expression);
        }
        return null;
    }

    private static Predicate getDecimalPredicate(CriteriaBuilder builder, Expression expression, SearchCondition condition) {
        if (condition.getOperatorType() == OperatorType.BETWEEN) {
            return builder.and(
                    builder.greaterThanOrEqualTo(expression, parseDecimal(condition.getDatas().get(0))),
                    builder.lessThanOrEqualTo(expression, parseDecimal(condition.getDatas().get(1)))
            );
        }

        return switch (condition.getOperatorType()) {
            case GREATER_EQUAL -> builder.greaterThanOrEqualTo(expression, parseDecimal(condition.getData()));
            case LESS_EQUAL -> builder.lessThanOrEqualTo(expression, parseDecimal(condition.getData()));
            case EQUAL -> builder.equal(expression, parseDecimal(condition.getData()));
            default -> null;
        };
    }

    private static Predicate getIntegerPredicate(CriteriaBuilder builder, Expression expression, SearchCondition condition) {
        if (condition.getOperatorType() == OperatorType.BETWEEN) {
            return builder.and(
                    builder.greaterThanOrEqualTo(expression, parseInteger(condition.getDatas().get(0))),
                    builder.lessThanOrEqualTo(expression, parseInteger(condition.getDatas().get(1)))
            );
        }

        return switch (condition.getOperatorType()) {
            case GREATER_EQUAL -> builder.greaterThanOrEqualTo(expression, parseInteger(condition.getData()));
            case LESS_EQUAL -> builder.lessThanOrEqualTo(expression, parseInteger(condition.getData()));
            case EQUAL -> builder.equal(expression, parseInteger(condition.getData()));
            default -> null;
        };
    }

    public static LocalDate parseDate(String date) {
        if (!StringUtils.hasText(date))
            return null;
        return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static LocalDateTime parseDateTime(String dateTime) {
        if (!StringUtils.hasText(dateTime))
            return null;
        dateTime = dateTime.replace(".000Z", "");
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    private static Boolean parseBool(String data) {
        return Boolean.parseBoolean(data);
    }

    private static BigDecimal parseDecimal(String data) {
        return new BigDecimal(data);
    }

    private static Integer parseInteger(String data) {
        return Integer.parseInt(data);
    }
}

package com.menuplus.backend.library.dto;

import com.menuplus.backend.library.enumeration.OperandType;
import com.menuplus.backend.library.enumeration.OperatorType;
import com.menuplus.backend.library.util.DateUtil;
import com.menuplus.backend.library.util.SearchUtil;
import java.util.List;
import lombok.Data;

@Data
public class SearchCondition {

  private String fieldName;
  private OperandType operandType;
  private OperatorType operatorType;
  private String data;
  private List<String> datas;

  public String toString() {
    return switch (operatorType) {
      case EQUAL -> String.format(
        "%s = %s",
        fieldName,
        getOperandDisplay(data)
      );
      case GREATER_EQUAL -> String.format(
        "%s >= %s",
        fieldName,
        getOperandDisplay(data)
      );
      case LESS_EQUAL -> String.format(
        "%s <= %s",
        fieldName,
        getOperandDisplay(data)
      );
      case BETWEEN -> String.format(
        "%s between %s and %s",
        fieldName,
        !datas.isEmpty() ? getOperandDisplay(datas.get(0)) : "",
        datas.size() >= 2 ? getOperandDisplay(datas.get(1)) : ""
      );
      case IN -> String.format("%s in %s", fieldName, datas.toString());
      case CONTAIN -> String.format(
        "%s contain %s",
        fieldName,
        getOperandDisplay(data)
      );
      case START_WITH -> String.format(
        "%s start with %s",
        fieldName,
        getOperandDisplay(data)
      );
      case END_WITH -> String.format(
        "%s end with %s",
        fieldName,
        getOperandDisplay(data)
      );
    };
  }

  public String getOperandDisplay(String data) {
    return switch (operandType) {
      case STRING -> data;
      case DATETIME -> DateUtil.format(SearchUtil.parseDateTime(data));
      case DATE -> DateUtil.format(SearchUtil.parseDate(data));
      case BOOLEAN -> data;
      case DECIMAL -> data;
      case INTEGER -> data;
      case ENUM -> data;
      default -> data;
    };
  }
}

package com.menuplus.backend.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.reflect.TypeToken;
import com.menuplus.backend.library.common.Constant;
import com.menuplus.backend.library.common.RequestHelper;
import com.menuplus.backend.library.enumeration.GeneralStatus;
import com.menuplus.backend.library.util.PageUtil;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@AllArgsConstructor
@Data
@SuperBuilder
public class BaseListRequest {

  private String keyword;
  private GeneralStatus status;
  private Integer page;
  private Integer size;
  private String sortBy = "";

  private String searchCondition;
  private List<SearchCondition> searchConditions;
  private String fieldNameMap;
  private Map<String, String> fieldNameMaps;

  public BaseListRequest() {
    this.sortBy = "";
  }

  public void setDefault() {
    if (size == null) size = Integer.MAX_VALUE;

    if (page == null) page = 0;

    if (!StringUtils.hasText(sortBy)) sortBy = "";
  }

  public void setSearchCondition(String searchCondition) {
    this.searchCondition = searchCondition;
    if (StringUtils.hasText(searchCondition)) {
      var searchConditions = RequestHelper.fromJson(
        searchCondition,
        SearchCondition[].class
      );
      this.searchConditions = Arrays.asList(searchConditions);
    }
  }

  public void setFieldNameMap(String fieldNameMap) {
    this.fieldNameMap = fieldNameMap;
    if (StringUtils.hasText(fieldNameMap)) {
      Type fieldMapType = new TypeToken<Map<String, String>>() {}.getType();
      fieldNameMaps = RequestHelper.fromJson(fieldNameMap, fieldMapType);
    }
  }

  @JsonIgnore
  public Pageable getPageable() {
    return PageUtil.createPage(page, size);
  }

  @JsonIgnore
  public Sort.Direction getSortDirection() {
    String[] sortByArr = sortBy.split(":");
    Sort.Direction direction = Sort.Direction.ASC;
    if (sortByArr.length > 1) {
      String directionParam = sortByArr[1];
      if (directionParam.toUpperCase().equals(Constant.DESCENDING_SORT)) {
        direction = Sort.Direction.DESC;
      }
    }
    return direction;
  }

  public boolean hasSortDirection() {
    if (!StringUtils.hasText(sortBy)) {
      return false;
    }
    String[] sortByArr = sortBy.split(":");
    return sortByArr.length > 1;
  }

  @JsonIgnore
  public String getSortField() {
    if (!StringUtils.hasText(sortBy)) {
      return "";
    }
    String[] sortByArr = sortBy.split(":");
    return sortByArr[0];
  }

  public String getSearchConditionDisplay() {
    if (CollectionUtils.isEmpty(searchConditions)) return "";
    return searchConditions
      .stream()
      .map(SearchCondition::toString)
      .collect(Collectors.joining(","));
  }
}

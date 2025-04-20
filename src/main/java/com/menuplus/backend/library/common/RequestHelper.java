package com.menuplus.backend.library.common;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import com.google.gson.Gson;

public class RequestHelper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Gson GSON = new Gson();
    private static final Gson GSON_RESPONSE = new GsonBuilder().setPrettyPrinting().create();

    static {
        /*
         * this resolve problem: The destination property matches multiple source property hierarchies:
         *
         * 	com.can.service.sale.entity.Product.getBrandId()
         * 	com.can.service.sale.entity.Product.getBrand()/com.can.service.sale.entity.Brand.getId()
         */
        MODEL_MAPPER.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        /*
         * Unrecognized field: Example: id in UpdateDto and no id in CreateDto
         */
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        /*
         * Map LocalDateTime
         */
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
    }

    public static String convertToJson(Object obj) {
        return GSON.toJson(obj);
    }

    public static String prettyJson(Object obj) {
        return GSON_RESPONSE.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    public static <T> T fromJson(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    public static <S, T> List<T> createDtoList(List<S> entityList, Function<S, T> mapFunction) {
        List<T> dtoList = new ArrayList<>();
        for (S entity : entityList) {
            T dto = mapFunction.apply(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public static String getToken() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        return request.getHeader(Constant.AUTH_HEADER);
    }

    public static String getJwt() {
        String bearerToken = getToken();
        if (org.springframework.util.StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constant.AUTH_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public static String getHeader(String header) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return request.getHeader(header);
    }

}

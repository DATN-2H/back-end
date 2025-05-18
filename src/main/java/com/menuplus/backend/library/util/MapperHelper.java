package com.menuplus.backend.library.util;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MapperHelper {

    public static <S, T> List<T> map(List<S> dtos, Function<S, T> mapFunction) {
        if (dtos == null) return null;
        return dtos.stream().map(mapFunction).collect(Collectors.toList());
    }

    public static <S, T> Set<T> map(Set<S> dtos, Function<S, T> mapFunction) {
        if (dtos == null) return null;
        return dtos.stream().map(mapFunction).collect(Collectors.toSet());
    }

    public static <S> List<S> filter(List<S> dtos, Predicate<S> filter) {
        if (dtos == null) return null;
        return dtos.stream().filter(filter).collect(Collectors.toList());
    }
}

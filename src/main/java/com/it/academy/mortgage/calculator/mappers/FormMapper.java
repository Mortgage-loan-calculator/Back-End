package com.it.academy.mortgage.calculator.mappers;

import java.util.List;

public interface FormMapper<E, D, R, F> {
    E fromFormDto(D dto);
    D toFormDto(E entity);
    R fromResultsDto(F resultsDto);
    F toResultsDto(R results);
    List<F> toResultsDtoList(List<R> results);
    List<D> toDtoList(List<E> entities);
}

package com.loomboom.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommonMapper {

    private final ModelMapper modelMapper;

    <T, S> S mapObject(T fromObj, Class<S> toObj) {
        return modelMapper.map(fromObj, toObj);
    }

    <T, S> List<S> mapListObject(List<T> fromListObj, Class<S> toListObj) {
        return fromListObj.stream().map(list -> modelMapper.map(list, toListObj)).collect(Collectors.toList());
    }

}

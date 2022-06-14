package com.example.springrabbitmq.service.populator;

import com.example.springrabbitmq.exception.PopulatorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public abstract class ConverterPopulator<S,T>{
    protected final Class<T> targetClass;

    public T convert(S source){
        final T target = createFromClass();
        populate(source, target);
        return target;
    }

    public List<T> convertAll(List<S> source){
        List<T> resultList = new ArrayList<>();
        source.forEach(sourceObject -> {
            final T target = createFromClass();
            populate(sourceObject, target);
            resultList.add(target);
        });
        return resultList;
    }

    protected T createFromClass(){
        try {
            return targetClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("ConverterPopulator, PopulatorException: {}", ExceptionUtils.getStackTrace(e));
            throw new PopulatorException("Populator error in Create From Class.", e);
        }
    }

    public abstract void populate(S source, T target);
}

package de.viadee.cameltest.Methods;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class Mapper<T> implements MapperTargetToBeSet, MapperTargetWasSet, MapperLeftWasSet, MapperExecutionAllowed {

    private T sourceObject;

    private Class<T> targetClass;

    private boolean byName = false;

    private HashMap<String, String> mapList = new HashMap<String, String>();

    private String lastLeft;

    public MapperTargetToBeSet source(T sourceList) {
        this.sourceObject = sourceList;
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public MapperTargetWasSet setTarget(String targetClassName) {
        try {
            targetClass = (Class<T>) Class.forName(targetClassName);
            return this;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MapperLeftWasSet left(String left) {
        mapList.put(left, null);
        lastLeft = left;
        return this;
    }

    @Override
    public MapperExecutionAllowed right(String right) {
        mapList.put(lastLeft, right);
        return this;
    }

    @Override
    public MapperExecutionAllowed byName() {
        byName = true;
        return this;
    }

    @Override
    public Object run() {

        try {
            Object targetObject = targetClass.newInstance();

            if (byName) {
                BeanUtils.copyProperties(sourceObject, targetObject);
            }
            if (!mapList.isEmpty()) {
                for (Map.Entry<String, String> entry : mapList.entrySet()) {

                    Class<?> sClass = sourceObject.getClass();
                    Field sourceField = sClass.getDeclaredField(entry.getKey());

                    Field targetField = targetClass.getDeclaredField(entry.getValue());

                    targetField.set(targetObject, sourceField.get(sourceObject));
                }
            }
            return targetObject;

        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }
}

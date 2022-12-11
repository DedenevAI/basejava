package com.uraise.webapp;

import com.uraise.webapp.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Method method = r.getClass().getDeclaredMethod("toString");
        System.out.println(method.invoke(r));
    }
}


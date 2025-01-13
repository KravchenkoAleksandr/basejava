package com.webapp;

import com.webapp.model.Resume;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<Resume> resume = Resume.class;
        Constructor<Resume> constructor = resume.getConstructor(String.class);
        Resume r1 = constructor.newInstance("uuid1");
        Method methodToString = resume.getMethod("toString");
        System.out.println(methodToString.invoke(r1));
    }

}

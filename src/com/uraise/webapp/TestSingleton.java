package com.uraise.webapp;

import com.uraise.webapp.model.SectionType;

public class TestSingleton {
    private static TestSingleton INSTANCE;

    public static TestSingleton getINSTANCE() {
        if (INSTANCE == null){
            INSTANCE = new TestSingleton();
        }
        return INSTANCE;
    }

    private TestSingleton() {
    }

    public static void main(String[] args) {
        TestSingleton.getINSTANCE().toString();
        Singleton instance = Singleton.valueOf("INSTANCE");
        System.out.println(instance.ordinal());
        for (SectionType type : SectionType.values()){
            System.out.println(type.getTitle());
        }
    }
    public enum Singleton{
        INSTANCE
    }
}

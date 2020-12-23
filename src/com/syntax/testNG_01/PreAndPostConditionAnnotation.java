package com.syntax.testNG_01;

import org.testng.annotations.*;

public class PreAndPostConditionAnnotation {

    @BeforeClass
    public void beforeClass () {
        System.out.println("Before class");
    }
    @Test
    public void testMethod () {
        System.out.println("Test method");
    }
    @Test
    public void testMethod2 () {
        System.out.println("Test method 2");
    }
    @Test
    public void testMethod3 () {
        System.out.println("Test method 3");
    }
    @BeforeMethod
    public void beforeMethod () {
        System.out.println("Before method");
    }
    @AfterMethod
    public void afterMethod () {
        System.out.println("After method");
    }
    @AfterClass
    public void afterClass () {
        System.out.println("After class");
    }
}

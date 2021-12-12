package com.example.fitnessapplication;

import java.io.Serializable;

public class TestResult implements Serializable
{
    public String Name;
    public String Result;
    public String Message;
    public String Date;

    public TestResult(String name, String result, String message, String date)
    {
        Name = name;
        Result = result;
        Message = message;
        Date = date;
    }
}

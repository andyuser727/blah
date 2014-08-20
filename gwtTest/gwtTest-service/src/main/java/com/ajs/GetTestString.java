package com.ajs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetTestString {
    public static final String TEST = "TEST";

    public String getTest() {
        return "TEST";
    }
}
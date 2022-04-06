package com.sm;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class TestFunc {

    //https://www.baeldung.com/java-8-lambda-expressions-tips

    public Function<List<String>, List<String>> allUpperOrLowerCase;

    public TestFunc (Boolean upper) {
        if (upper)
            allUpperOrLowerCase = words -> words.stream().map(String::toUpperCase).collect(Collectors.toList());
        else
            allUpperOrLowerCase = words -> words.stream().map(String::toLowerCase).collect(Collectors.toList());
    }

    public TestFunc (Function<List<String>, List<String>> f) {
        allUpperOrLowerCase = f;
    }

    public void eval () {
        List<String> result = allUpperOrLowerCase.apply(Arrays.asList("Java8", "Streams"));
        List<String> expected = Arrays.asList("JAVA8", "STREAMS");

//        assertEquals(expected, result);
        System.out.println("Result: "+ result);
        System.out.println("Eval equal: " + result.equals(expected));
    }

}
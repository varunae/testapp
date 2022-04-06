package com.sm;


import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class TestApp {
    //AWS: https://intellipaat.com/blog/interview-question/amazon-aws-interview-questions/#11
//https://github.com/ghanan94/codility-lesson-solutions/tree/master/Lesson%2010%20-%20Prime%20and%20composite%20numbers/Peaks

    // Stream functions
// https://www.java67.com/2018/10/java-8-stream-and-functional-programming-interview-questions-answers.html
// https://www.baeldung.com/java-8-streams

    public static void main(String[] args)  {
        TestCod testCod = new TestCod();

        TestStreams appStream = new TestStreams();

        int[] minNot = new int[] {-1, 0, -2, -5, -3, -7};
        int[] discs = {1, 5, 2, 1, 4, 0};
        int[] counters = {3,4,4,6,1,4,4};
        int[] manh = {8,8,5,7,9,8,7,4,8};
        int[] maxProf = { 23171,21011,21123,21366,21013,21367};
        int[] pk = { 1,2,3,4,3,4,1,2,3,4,6,2};
        int[] bival = {1,2,4, 3,1,3,1,1,2,2};

        long startT = System.currentTimeMillis(); //nanoTime();
        System.out.println("Solution minNotPresent = " + appStream.minNotPresent(minNot));
        long endT = System.currentTimeMillis();
        System.out.println("Elapsed Time ms: "+ (endT-startT));

        startT = System.currentTimeMillis(); //nanoTime();
        System.out.println("Solution biVal = " + testCod.biValue(bival));
        endT = System.currentTimeMillis();
        System.out.println("Elapsed Time ms: "+ (endT-startT));


//        startT = System.currentTimeMillis();
//        System.out.println("Solution minNotPresent2 = " + appStream.minNotPresent2(minNot));
//        endT = System.currentTimeMillis();
//        System.out.println("Elapsed Time ms: "+ (endT-startT));

//        startT = System.currentTimeMillis();
//        System.out.println("Solution discs= " + appStream.disks(discs));
//        endT = System.currentTimeMillis();
//        System.out.println("Elapsed Time ms: "+ (endT-startT));

//        startT = System.currentTimeMillis();
//        System.out.println("Solution counters= " + Arrays.toString(appStream.maxCounter(5, counters)));
//        endT = System.currentTimeMillis();
//        System.out.println("Elapsed Time ms: "+ (endT-startT));

//        startT = System.currentTimeMillis();
//        System.out.println("Solution manhattan= " + appStream.manhattan(manh));
//        endT = System.currentTimeMillis();
//        System.out.println("Elapsed Time ms: "+ (endT-startT));

//        startT = System.currentTimeMillis();
//        System.out.println("Solution maxProfit= " + appStream.maxProfit(maxProf));
//        endT = System.currentTimeMillis();
//        System.out.println("Elapsed Time ms: "+ (endT-startT));

//        startT = System.currentTimeMillis();
//        System.out.println("Solution maxProfit2= " + appStream.maxProfit2(maxProf));
//        endT = System.currentTimeMillis();
//        System.out.println("Elapsed Time ms: "+ (endT-startT));

//        startT = System.currentTimeMillis();
//        System.out.println("Solution peaks2= " + appStream.peaks2(pk));
//        endT = System.currentTimeMillis();
//        System.out.println("Elapsed Time ms: "+ (endT-startT));

        // streams
//        appStream.listStreams();
//
//        appStream.mapsStreams();

        // functions
        Scanner scanner = new Scanner(System.in);
        System.out.print ("Input upper(1) or other: ");
        String[] input = scanner.nextLine().split(" ");
//        input[0].equals("1")

        TestFunc testFn = new TestFunc(words -> words.stream()
                .map((input[0].equals("1"))?String::toUpperCase:String::toLowerCase)
                .collect(Collectors.toList()));
        testFn.eval();




        Map<String, UserStats> mp1 = new HashMap<>();
        mp1.put("1", new UserStats(10L));
        mp1.put("2", new UserStats(20L));
        mp1.put("3", new UserStats(30L));

        Map<String, UserStats> mp2 = new HashMap<>();
        mp2.put("1", new UserStats(20L));
        mp2.put("2", new UserStats(30L));
        mp2.put("3", new UserStats(40L));

        testCod.count(mp1, mp2);


    }

}

package com.sm;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class TestIterate {

    public void testFor() {
        List<Integer> list = Arrays.asList(1, 5, 4, 2, 6, 8, 3, 9, 7, 10);

        System.out.println(); // basic for
        for (int i=0; i<10; i++) {
            System.out.print(list.get(i));
        }

        System.out.println(); // for enhanced
        for (Integer item : list) {
            System.out.print(item);
        }

        System.out.println(); // iterator
        Iterator<Integer> iter = list.iterator();
        while(iter.hasNext()) {
            System.out.print(iter.next());
        }

        System.out.println(); // Iterable Foreach
        list.forEach(System.out::print);

        System.out.println(); // Stream iterate
        Stream.iterate(0, n -> n + 1)
                .limit(10)
                .forEach(x -> System.out.print(x));

    }

    public void fibonaci() {
        Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
                .limit(20)
                .map(n -> n[0])
                .forEach(x -> System.out.println(x));

        // sum fibonaci
        int sum = Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
                .limit(10)
                .map(n -> n[0]) // Stream<Integer>
                .mapToInt(n -> n)
                .sum();

        System.out.println("Fibonacci 10 sum : " + sum);
    }

}

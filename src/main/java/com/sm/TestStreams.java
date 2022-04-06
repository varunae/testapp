package com.sm;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestStreams {

    //ArrayList - impl of List

    public void listStreams () {
        // CREATE
        // M1: Array.asList -> List.stream() . Can use Collection vs List
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> streamList = list.stream();

        // M2: Arrays.stream() - to use as source: String[]
        String[] arrString = new String[] {"d", "e", "f"};
        Stream<String> streamArray = Arrays.stream(arrString);

        /*  REDUCE
         */
        List<List<Integer>> lists = Arrays.asList( Arrays.asList(1,2),
                Arrays.asList(2,5), Arrays.asList(3,6), Arrays.asList(4,5));

        // Reduce: filter to simple types - collect to list
        List<List<Integer>> filterListOfList = lists.stream().filter( val -> {
//            System.out.println("--- In filter: collect to List ... " + val);
            return (val.get(0) > 1); }).collect(Collectors.toList());
        System.out.println ("-- List of items key > 1: " + filterListOfList);

        //Reduce: filter with inline lambda expr; no map ; collect to average
        System.out.println("-- Avg all values:" + lists.stream().filter( TestStreams::filterSelect)
            .collect(Collectors.averagingInt(TestStreams::getTupleValue)));
        // Collectors.summingInt
        // Double::doubleValue  Integer::intValue   Long::longValue
        // DTO::getMethod returning Int/Double/Long; used as reference for ToIntFunction ...
        // ... OR ...
        //.collect(ArrayList<Integer>::new, ArrayList::add, ArrayList::addAll);

        // Reduce: filter with method reference; no map; collect to average
        java.util.IntSummaryStatistics streamStats = lists.stream().filter( val -> {
//            System.out.println("--- In filter: collect Summarizing ... " + val);
            return (val.get(0) > 0); }).collect(Collectors.summarizingInt(TestStreams::getTupleValue));
        //getCount(), getSum(), getMin(), getAverage(), and getMax()
        System.out.println("-- Summarizing all values:" + streamStats+"\nMin=" + streamStats.getMin());

        //Collectors.groupingBy(Product::getPrice) or mapper
        System.out.println("-- Grouping by values:" + lists.stream().filter( val -> {
//            System.out.println("--- In filter: collect Avg ... " + val);
            return (val.get(0) > 0); }).collect(Collectors.groupingBy(TestStreams::getTupleValue)));

        //No collectors - MapToInt
        System.out.println("-- Avg w/o collector:" + lists.stream().filter(val -> val.get(0) > 0)
                .mapToInt( p -> p.get(1) * 2 ).average().getAsDouble());

        //.map(product -> product.getPrice() * 12) when stream of custom objects

        //parallelStream() for Arrays and Collection

        /* SORTING
         */
        // Sort asc - default
        System.out.print("-- Sorting default:");
        lists.stream().filter( val -> { return (val.get(0) > 0); })
            .map( p -> p.get(1) * 2 ).sorted().forEach(System.out::println);

        // Sort asc - Custom comparator
        System.out.print("-- Sorting list comparator:");
        lists.stream().filter( val -> { return (val.get(0) > 0); }).map( p -> p.get(1) * 2 )
                .sorted((i1, i2) -> i1.compareTo(i2)).forEach(System.out::println);

        System.out.print("-- or custom comparator via method reference:");
        lists.stream().filter( val -> { return (val.get(0) > 0); }).map( p -> p.get(1) * 2 )
                .sorted(Integer::compareTo).forEach(System.out::println);
            // both 1-arg compare() and 2-arg compareTo() can be used as Comparator reference

        // Sort stream of objects - custom Comparator
        // sorted((p1, p2) -> ((Long)p1.getPersonId()).compareTo(p2.getPersonId())).forEach(person -> System.out.println(person.getName()));

        // Sort reverse
        System.out.print("-- Sorting list comparator reverse:");
        lists.stream().filter( val -> { return (val.get(0) > 0); }).map( p -> p.get(1) * 2 )
                .sorted(Comparator.reverseOrder()).forEach(System.out::println);

        // custom desc sort Comparator - w/o compare() or compareTo()
        // .sorted((item1,item2)-> item1.price<item2.price?1:-1)

        // Sort stream of objects - generic Comparator.comparing() with custom key extractor
        System.out.print("-- Sorting list comparator - custom key extract:");
        lists.stream().filter( val -> { return (val.get(0) > 0); }).map( p -> p.get(1) )
//                .sorted(Comparator.comparing(Person::getPersonId)).forEach(person -> System.out.println(person.getName()));
                .sorted(Comparator.comparing(Object::toString)).forEach(System.out::println);

        // Sort stream of objects - sequence sort
        // objList.stream().sorted(Comparator.comparing(Person::getPersonId).thenComparing(Person::getAge)).forEach(person -> System.out.println(person.getName()));

        // IntStream intStreamNew = stream.flatMapToInt(Arrays::stream);

        //peak

    }

    public void mapsStreams () {
        Map<String, String> map = new HashMap<>();
        map.put("1", "2"); map.put("2", "5"); map.put("3", "6"); map.put("4", "5");

        // MAP SORTING - default comparator, custom key extractor
        System.out.println("Sorted map generic Comparator custom key extract: ");
        map.entrySet().stream()
                .sorted(Comparator.comparing( v -> Integer.parseInt(v.getValue()) ))
                .forEach(System.out::println);

        // MAP SORTING - Map Entry comparator - by value
        System.out.println("Reversed map Map.Entry comparator: ");
        map.entrySet().stream()
                .sorted(Map.Entry.<String, String>comparingByValue().reversed()) //comparingByKey
                .forEach(System.out::println);

        System.out.println("Printed sorted using toMap collector" + map.entrySet().stream()
                .sorted(Map.Entry.<String, String>comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)));
    }

    public static Integer getTupleValue(List<Integer> lst) {
//        System.out.println("--- getTuplevalue: " + lst);
        return lst.get(1);
    }
    public static Boolean filterSelect (List<Integer> lst) {
        return lst.get(0) > 0;
    }

    public List<String> findInStream () {
        List<String> ret = null;

        return ret;
    }

    public int minNotPresent(int[] A) {
        int max=0;

        List<Integer> list = Arrays.stream(A).boxed().collect(Collectors.toList());
        OptionalInt omax = Arrays.stream(A).boxed().mapToInt(i -> i).max();
        if ((omax.isPresent()) && (omax.getAsInt()>0))
            max = omax.getAsInt();

        Stream<Integer> allStream = Stream.iterate(1,x -> x+1).limit(max);
        Optional<Integer> res = allStream.filter( a1 -> {
            return list.stream().noneMatch(a2 -> a2.equals(a1));
        }).findFirst();
        if (res.isPresent())
            return res.get();
        else
            return max+1;
    }

    public int minNotPresent2(int[] A) {
        int sol =0;
        for (int i=1; i<100000; i++) {
            int finalI = i;
            if (!Arrays.stream(A).filter(item -> item== finalI).findAny().isPresent()) {
                sol = i;
                break;
            }
        }
        return sol;
    }

    public int disks(int[] A) {
        int sol =0;
        for (int i=0; i<=A.length-2; i++) {
            int right = i+ A[i];
//            System.out.print("i=" + i+ ";A[i]=" +A[i]+ ";Right=" + right +";");
            for (int j = i + 1; j <= A.length-1; j++) {
                int left =  j - A[j];
//                System.out.println(" j=" +j+ ";A[j]=" +A[j]+ ";Left=" +left +";");
                if (right >= left) {
//                    System.out.println("Fit:" + i + ":" + j);
                    sol += 1;
                }
//                    else System.out.println("Not Fit:" + i + ":" +j);
            }
        }
        return (sol>100)?-1:sol;
    }

    public int[] maxCounter(int N, int[] A) {
        int[] sol = new int[N];

        // CONVERT int[] to List (ArrayList):   Arrays.stream(A).boxed().collect(Collectors.toList())
        List<Integer> list = Arrays.stream(sol).boxed().collect(Collectors.toList());;

        // CONVERT List(ArrayList) to int[]:     list.stream().mapToInt(Integer::intValue).toArray()

        // wrap int[] to Map -> Dropped too much mem
        //Map<Integer, List<Integer>> map = Collections.singletonMap(0, list);

        for (int i=0; i<=A.length-1; i++) {
            if (A[i]>N)  { // increase all
                OptionalInt max = list.stream().mapToInt(Integer::intValue).max(); // or maptoInt(i->i)
                list = Stream.iterate(max.getAsInt(),x -> x).limit(N).collect(Collectors.toList());
            } else // increase 1 the A[i] position
                list.set(A[i]-1, list.get(A[i]-1)+1);
//            System.out.println("Counters:" + list);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public int manhattan (int[] H) {
        List<List<Integer>> box = new ArrayList<>();
        int lastHeight=0;
        for (int i=0; i< H.length; i++) {
            if (H[i] > lastHeight) { // add box (altitude, closed)
                box.add(Arrays.asList(H[i], 0));
                lastHeight = H[i];
            } else if (H[i] < lastHeight) {
                // close boxes - start new box, decrease current height
                int tempHeight=H[i];
                boolean mustOpen = true;
                List<Integer> lItem;

                for (int j=0; j<box.size(); j++) {//reverse
                    lItem = box.get(box.size()-1-j);
                    if (lItem.get(1) == 0) { // only if box is not already closed
                        if ( tempHeight < lItem.get(0) ) { // close current box, no break - continue
                            lItem.set(1, 1); //close item;  box.set(box.size()-1-j, lItem);
                        } else if ( tempHeight > lItem.get(0) ) { // no close, break, open new
//                            lItem.set(1, 1); // no close item; box.set(box.size()-1-j, lItem);
//                            box.add (Arrays.asList(H[i], 0));
                            break;
                        } else if ( tempHeight == box.get(box.size()-1-j).get(0) ) { // break, no close, no new
                            mustOpen = false;
                            break;
                        }
                    }
                }
                if (tempHeight == 0 || mustOpen)
                    box.add (Arrays.asList(H[i], 0));
                lastHeight = H[i];
            }
        }
        System.out.println("Boxes" + box);
        return box.size();
    }

    // ranges array: Arrays.copyOfRange(array, beg, end + 1); IntStream.range(beg, end+1);
    // ranges List Arrays.asList(arr).subList(beg, end + 1).toArray(new Integer[0]);
    public int maxProfit (int[] A) {
        List<Integer> res = new ArrayList<>();

        for (int i=0; i< A.length-1; i++) { // last elem excl.
            int max = IntStream.range(i+1,A.length).map(v-> A[v]).max().orElseGet(()->0);
//            int[] subarray = IntStream.range(i+1,A.length).map(v-> A[idx]).toArray();
            res.add (i, max-A[i]<0?0:max-A[i]);
        }
//        System.out.println("Max:" +res);
        return res.stream().mapToInt(Integer::intValue).max().orElseGet(()->0);
    }

    public int maxProfit2 (int[] A) {

        List<Integer> res = IntStream.range(0,A.length).mapToObj( idx ->
                 Math.max(IntStream.range(idx+1,A.length).map(v-> A[v]).max().orElseGet(()->0) - A[idx],0))
                .collect(Collectors.toList());

//        System.out.println("Max:" +res);
        return res.stream().mapToInt(Integer::intValue).max().orElseGet(()->0);
    }

    public int peaks (int[] A) {
        return (int) Stream.iterate(1,i->i+1).limit( A.length-2 ).filter(x -> (A[x-1]<A[x]) && (A[x]>A[x+1]))
                .count();
    }

    public List<Integer> peaks2 (int[] A) {

        return Stream.iterate(1,i->i+1).limit( A.length-2 ).filter(x -> (A[x-1]<A[x]) && (A[x]>A[x+1]))
                .mapToInt(n -> n).boxed().collect(Collectors.toList());
        //.count()
    }
}

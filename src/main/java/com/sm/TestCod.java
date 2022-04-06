package com.sm;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestCod {

    Map<Long, Long> count(Map<String, UserStats>... visits) {
        Map<Long, Long> res = new HashMap<>();

        Arrays.stream(visits).forEach( e -> {
            // for each ms
//            for each user (values) - map String to Long (0 is n/a)
            e.entrySet().stream().forEach( v -> {
                //must move this to function
                Long userID =testLong(v.getKey());
                if (userID>0) {
                    res.put(userID, res.get(userID)==null?0:res.get(userID) + userStat2Long(v.getValue()));
                }

            }
            );
        });
        return res;
    }

    static Long testLong (String value) {
        try {
            return Long.parseLong(value);
        }catch  (Exception e) {
            return 0L;
        }
    }

    static Long userStat2Long (UserStats value) {
        try {
            return value.visitCount.orElse(0L);
        }catch  (Exception e) {
            return 0L;
        }
    }

    public int biValue(int[] A) {

        List<Integer> biValues = new ArrayList<>();
        if (A.length<=1)
            return A.length;

        IntStream.range(0,A.length).boxed().forEach( e -> {
            boolean bival = false;
            int otherVal=-1;
            int len=0;
            for (int j= e; j< A.length; j++) {
                if (A[j] == A[e])
                    len+=1;
                else
                    if ((!bival) && (otherVal == -1)) {
                        //asign new val
                        otherVal = A[j];
                        bival=true;
                        len += 1;
                    } else
                        if (A[j] == otherVal) {
                            len+=1;
                        } else {
                            bival = false; // third value
                            break;
                        }
            }
            //save bivalues len for index e
            biValues.add(e, len);
        }
        );

        System.out.println(biValues.toString());
        //select max
        return biValues.stream().mapToInt(Integer::intValue).max().orElseGet(()->0);

//        Map<Integer, Long> list = Arrays.stream(A).boxed().collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
//        if (list.size()<=2) {
//            return IntStream
//        }
//            else return 0;
    }
}

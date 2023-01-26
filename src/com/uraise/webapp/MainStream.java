package com.uraise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainStream {
    public static void main(String[] args) {
        int[] massive = new int[]{9, 8, 7, 5, 5};
        System.out.println(minValue(massive));

        List<Integer> integers = new ArrayList<>();
        integers.add(3);
        integers.add(3);
        integers.add(2);
        integers.add(1);
        integers.add(10);
        integers.add(10);
        System.out.println(oddOrEven(integers));

    }

    public static int minValue(int[] values) {
        if (values.length > 9 || values.length == 0) {
            throw new IllegalArgumentException("Incorrect length of massive(should be 1...9)");
        }
        return Arrays.stream(values)
                .sorted()
                .distinct()
                .reduce((x, y) -> ((x * 10) + y)).getAsInt();
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        double sum = createStream(integers).reduce(0, Integer::sum);
        if (sum % 2 == 0) {
             return createStream(integers).filter((i) -> i % 2 != 0).collect(Collectors.toList());
        } else {
            return createStream(integers).filter((i) -> i % 2 == 0).collect(Collectors.toList());
        }
    }
    public static Stream<Integer> createStream(List<Integer> list){
        return list.stream();
    }
}

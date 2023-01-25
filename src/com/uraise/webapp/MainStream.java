package com.uraise.webapp;

import java.util.Arrays;
import java.util.List;

public class MainStream {
    public static void main(String[] args) {
        int[] massive = new int[]{9,8,7,5,5};
        System.out.println(minValue(massive));
    }
    public static int minValue(int[] values){//first task method
        if(values.length > 9 || values.length == 0){
           throw new IllegalArgumentException("Incorrect length of massive(should be 1...9)");
        }
        return Arrays.stream(values)
                .sorted()
                .distinct()
                .reduce((x, y) -> ( (x * 10) + y)).getAsInt();
    }
    public List<Integer> oddOrEven(List<Integer> integers){
        int result = integers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        if(result % 2 != 0){

        }
        return null;
    }
}

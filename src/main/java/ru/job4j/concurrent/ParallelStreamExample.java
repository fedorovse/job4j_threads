package ru.job4j.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStreamExample {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 4, 7, 3, 5, 2);
        Stream<Integer> stream = list.parallelStream();
        System.out.println(stream.isParallel());
        Optional<Integer> multiplication = stream.reduce((left, right) -> left * right);
        System.out.println(multiplication.get());
        IntStream parallel = IntStream.range(1, 10).parallel();
        System.out.println(parallel.isParallel());
        IntStream seq = parallel.sequential();
        System.out.println(seq.isParallel());

        list.stream().parallel().peek(System.out::println).toList();
        System.out.println(" ");
        list.stream().parallel().forEach(System.out::println);
        System.out.println(" ");
        list.stream().parallel().forEachOrdered(System.out::println);
    }
}

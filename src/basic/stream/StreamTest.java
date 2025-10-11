package basic.stream;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {

        Stream<Integer> stream = Stream.of(1, 2, 5);
        Optional<Integer> reduce = stream.peek(System.out::println)
                .map(a -> a + 1).peek(System.out::println)
                .filter(a -> a < 10)
                .reduce((x, y) -> x * y);

        System.out.println(reduce.orElse(-1));

        stream = Stream.of(1, 3, 5);
        String collect = stream.map(a -> {
            if (a>3)
                return a+"SS";
            else
                return a+"s";

        }).collect(Collectors.joining(","));
        System.out.println(collect);
    }
}

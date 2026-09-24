package nl.jhutting.modernjava.gatherers;

import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GatherWindow {

    void main () {

        final List<List<Integer>> batches = IntStream.rangeClosed(1, 10)
                .boxed()
                .gather(Gatherers.windowFixed(3))
                .toList();

        IO.println("Fixed window of 3:");
        IO.println(batches);

        final List<List<Integer>> temperatures = Stream.of(18, 20, 23, 21, 19)
                .gather(Gatherers.windowSliding(3))
                .toList();

        IO.println("Sliding window of 3:");
        IO.println(temperatures);
    }
}

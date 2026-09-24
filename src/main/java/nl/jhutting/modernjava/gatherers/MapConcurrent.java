package nl.jhutting.modernjava.gatherers;

import java.util.List;
import java.util.stream.Gatherers;

public class MapConcurrent {

    void main() {
        List<String> products = java.util.stream.IntStream.rangeClosed(1, 20)
                .boxed()
                .gather(Gatherers.mapConcurrent(5, this::fetchProduct))
                .toList();

        IO.println(products);
    }

    String fetchProduct(int id) {
        try {
            Thread.sleep(500);
            return "Product " + id;
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(exception);
        }
    }


}

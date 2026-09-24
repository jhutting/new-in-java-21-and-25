package nl.jhutting.modernjava.collections;

import java.util.ArrayList;
import java.util.List;

public class Sequenced {

    void main() {
        final var numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        numbers.addFirst(0);

        final List<Integer> modifiableNumbers = new ArrayList<>(numbers);

        modifiableNumbers.addFirst(0);

        final var reversed = modifiableNumbers.reversed();
        reversed.removeLast();

        IO.println(reversed);
    }
}

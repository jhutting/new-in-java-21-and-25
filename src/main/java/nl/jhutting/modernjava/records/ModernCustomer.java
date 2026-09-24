package nl.jhutting.modernjava.records;

import java.util.List;

public record ModernCustomer(long id, String name, List<String> products) {
    public ModernCustomer {
        if (id <= 0) {
            throw new IllegalArgumentException("id must be positive");
        }

        name = name.strip();

        if (name.isEmpty()) {
            throw new IllegalArgumentException("name must not be empty");
        }

        products = List.copyOf(products); // ensure unmodifiable copy, uses no resources on already unmodifiable Collection!
    }
}

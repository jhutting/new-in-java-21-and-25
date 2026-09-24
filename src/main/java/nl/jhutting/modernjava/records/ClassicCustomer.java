package nl.jhutting.modernjava.records;

import java.util.Objects;

public final class ClassicCustomer {
    private final long id;
    private final String name;

    public ClassicCustomer(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long id() {
        return id;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ClassicCustomer other)) {
            return false;
        }
        return id == other.id && Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Customer[id=" + id + ", name=" + name + "]";
    }
}

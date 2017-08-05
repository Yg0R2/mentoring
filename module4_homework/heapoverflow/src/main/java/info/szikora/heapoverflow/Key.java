package info.szikora.heapoverflow;

public class Key {
    private final Integer id;

    Key(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return id == ((Key) obj).getId();
    }

    // In this basic example we only have one field, so hash can simply derived from that single field
    @Override
    public int hashCode() {
        return id;
    }

}

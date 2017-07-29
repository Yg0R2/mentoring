package info.szikora.heapoverflow;

public class Key {
    private final Integer id;

    Key(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    // In this basic example we only have one field, so hash can simply derived from that single field
    @Override
    public int hashCode() {
        return id.hashCode();
    }

}

package info.szikora.heapoverflow;

public class Value {

    private final String valueString;

    // to imitate a heavier memory footprint
    @SuppressWarnings("unused")
    private final byte[] array = new byte[1000];

    public Value(String valueString) {
        this.valueString = valueString;
    }

    public String getValueString() {
        return valueString;
    }

}

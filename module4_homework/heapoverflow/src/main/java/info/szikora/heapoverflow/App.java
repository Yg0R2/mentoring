package info.szikora.heapoverflow;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

//
// 
// TODO 2 Investigate app behavior using JVisualVm and play around with command arguments. A starting point may be something like below.
// 
// -Xms100m 
// -Xmx400m 
// -XX:+HeapDumpOnOutOfMemoryError 
// -XX:+PrintGCDetails 
// -XX:+PrintGCDateStamps 
// -Xloggc:gclog.log
//
//
// TODO 2 Fix memory problem to avoid heap overflow
//

public class App {

    public static void main(String[] args) {
        Map<Key, Value> map = new HashMap<>();

        while (true) {
            IntStream.range(1, 100).forEach(i -> map.put(new Key(i), new Value("" + i)));
        }
    }
}

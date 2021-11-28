package justtest;

import java.util.concurrent.ConcurrentSkipListMap;

public class MyTest {
    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();
        map.put(100, "1");
        map.put(2, "2");
        System.out.println(map);
    }
}

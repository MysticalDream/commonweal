package utils;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.utils.BitMap;

class BitMapTest {
    BitMap bitMap = new BitMap(100);

    @Test
    void set() {
        bitMap.set(10);
        bitMap.set(20);
        System.out.println(bitMap.isExist(20));
    }

    @Test
    void isExist() {
    }
}
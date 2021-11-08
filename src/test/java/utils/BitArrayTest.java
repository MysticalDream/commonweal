package utils;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.utils.bitmap.BitArray;

class BitArrayTest {
    BitArray bitArray = new BitArray(100);

    @Test
    void set() {
        bitArray.set(10,true);
        bitArray.set(20,true);
        System.out.println(bitArray.isExist(20));
    }

    @Test
    void isExist() {
    }
}
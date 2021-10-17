package team.skylinekids.commonweal.utils.bitmap;

import java.util.Map;

/**
 * 位图
 * 点赞统计
 * 数据结构
 *
 * @author MysticalDream
 */
public class BitArray {

    private int[] bitArray;

    public BitArray(int size) {
        bitArray = new int[size / 32 + 1];
    }

    public void set(int num) {
        int arrayIndex = num >> 5;
        //num%(n-1)
        int bitIndex = num & 31;

        bitArray[arrayIndex] |= 1 << bitIndex;
    }

    public boolean isExist(int num) {
        //num/32
        int arrayIndex = num >> 5;
        //num%32 该方法只对2的N次方数系有效 hashmap中的容量也是2的N次方
        int bitIndex = num & 31;
        return (bitArray[arrayIndex] & (1 << bitIndex)) != 0 ? true : false;
    }

}

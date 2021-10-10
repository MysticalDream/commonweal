package team.skylinekids.commonweal.utils;


import java.util.Map;

import java.util.concurrent.*;

/**
 * 缓存工具
 * 不适用于分布式
 *
 * @author MysticalDream
 */
public final class CacheUtils {

    private CacheUtils() {

    }

    /**
     * 缓存实体
     */
    private static class Entity {
        //键值对的value
        private Object value;
        //定时器
        private final Future future;

        public Entity(Object value, Future future) {
            this.value = value;
            this.future = future;
        }

        public Object getValue() {
            return value;
        }

        public Future getFuture() {
            return future;
        }
    }

    /**
     * 键值对 线程安全的map
     */
    private final static Map<String, Entity> MAP = new ConcurrentHashMap<>();
    /**
     * 定时器线程池,清除过期缓存
     */
    private final static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    /**
     * 添加缓存,不过期
     *
     * @param key   键
     * @param value 值
     */
    public static void put(String key, Object value) {
        CacheUtils.put(key, value, 0);
    }

    /**
     * 添加缓存
     *
     * @param key   键
     * @param value 值
     * @param delay 存活时间，单位是毫秒,小于等于0表示无限长
     */
    public static void put(String key, Object value, long delay) {
        //清除原键值对
        CacheUtils.remove(key);
        //设置过期时间
        if (delay > 0) {
            Future<?> future = scheduledExecutorService.schedule(() -> {
                //过期后删除键值对
                MAP.remove(key);
            }, delay, TimeUnit.MILLISECONDS);
            MAP.put(key, new Entity(value, future));
        } else {
            //不设置过期时间
            MAP.put(key, new Entity(value, null));
        }

    }

    /**
     * 读取缓存
     *
     * @param key 值
     * @return 返回缓存中的值
     */
    public static Object get(String key) {
        Entity entity = MAP.get(key);
        return entity == null ? null : entity.getValue();
    }

    /**
     * 读取缓存
     *
     * @param key   键
     * @param clazz 值的类型
     * @param <T>   值的类型
     * @return 返回缓存中的值
     */
    public static <T> T get(String key, Class<T> clazz) {
        return clazz.cast(CacheUtils.get(key));
    }

    /**
     * 清除缓存
     *
     * @param key 键
     * @return 返回移除的键值对的值
     */
    public static Object remove(String key) {
        //清除原来缓存的数据
        Entity entity = MAP.remove(key);

        if (entity == null) {
            return null;
        }
        //清除键值对原定时器
        Future<?> future = entity.getFuture();

        if (future != null) {
            future.cancel(true);
        }

        return entity.getValue();
    }

    /**
     * 查询当前缓存的键值对数量
     *
     * @return 当前缓存的键值对数量大小
     */
    public static int size() {
        return MAP.size();
    }

}

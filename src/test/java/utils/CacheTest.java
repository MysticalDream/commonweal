package utils;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.utils.CacheUtils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class CacheTest {

    @Test
    void put() throws ExecutionException, InterruptedException {
        String key = "id";
        //不设置过期时间
        System.out.println("***********不设置过期时间**********");
        CacheUtils.put(key, 123);
        System.out.println("key:" + key + ", value:" + CacheUtils.get(key));
        System.out.println("key:" + key + ", value:" + CacheUtils.remove(key));
        System.out.println("key:" + key + ", value:" + CacheUtils.get(key));

        //设置过期时间
        System.out.println("\n***********设置过期时间**********");
        CacheUtils.put(key, "123456", 1000);
        System.out.println("key:" + key + ", value:" + CacheUtils.get(key));
        Thread.sleep(2000);
        System.out.println("key:" + key + ", value:" + CacheUtils.get(key));

        /******************并发性能测试************/
        System.out.println("\n***********并发性能测试************");
        //创建有10个线程的线程池，将1000000次操作分10次添加到线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future[] futures = new Future[10];

        /********添加********/
        {
            long start = System.currentTimeMillis();
            for (int j = 0; j < 10; j++) {
                futures[j] = executorService.submit(() -> {
                    for (int i = 0; i < 100000; i++) {
                        CacheUtils.put(Thread.currentThread().getId() + key + i, i, 300000);
                    }
                });
            }
            //等待全部线程执行完成，打印执行时间
            for (Future future : futures) {
                 future.get();
            }
            System.out.printf("添加耗时：%dms\n", System.currentTimeMillis() - start);
        }

        /********查询********/
        {
            long start = System.currentTimeMillis();
            for (int j = 0; j < 10; j++) {
                futures[j] = executorService.submit(() -> {
                    for (int i = 0; i < 100000; i++) {
                        CacheUtils.get(Thread.currentThread().getId() + key + i);
                    }
                });
            }
            //等待全部线程执行完成，打印执行时间
            for (Future future : futures) {
                future.get();
            }
            System.out.printf("查询耗时：%dms\n", System.currentTimeMillis() - start);
        }

        System.out.println("当前缓存容量：" + CacheUtils.size());

    }

    @Test
    void testPut() {
    }

    @Test
    void get() {
    }

    @Test
    void testGet() {
    }

    @Test
    void remove() {
    }

    @Test
    void size() {
    }
}
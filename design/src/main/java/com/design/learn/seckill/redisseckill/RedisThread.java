package com.design.learn.seckill.redisseckill;

import com.design.learn.seckill.redisseckill.RedisMsTest;
import com.design.learn.seckill.redisseckill.RedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class RedisThread implements Runnable {

    private JedisPool jedisPool;

    private String productName;

    private int counter;

    private int flag;

    private static long start = 0; // 开始抢购时间

    private static long time = 0; // 多长时间抢购一空

    private CyclicBarrier cyclicBarrier;

    public RedisThread(String productName, JedisPool jedisPool) {
        this.productName = productName;
        this.jedisPool = jedisPool;
    }

    //用于cyclicBarrier高并发测试
    public RedisThread(String productName,JedisPool jedisPool,CyclicBarrier cyclicBarrier){
        this.productName = productName;
        this.jedisPool = jedisPool;
        this.cyclicBarrier = cyclicBarrier;
    }


    //打印日志日志在target class下面
    private synchronized void print(String str) {
        Class clazz = RedisMsTest.class;
        URL url = clazz.getResource("/");
        String path = url.toString();
        System.out.println(MessageFormat.format("path={0}",path));
        path = path.substring(6);

        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(path + "/redisResult.txt", true));
            bw.write(str);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //线程定时器   备注 正式环境上该时间可以配置在数据库中  并存入redis缓存中获取
    private long clock() {
        String clock = "2019-04-18 10:44:52";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = null;
        try {
            date = dateFormat.parse(clock);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }


    @Override
    public void run() {
        try {
            System.out.println("=====================正在等待高并发=====================");
            Thread.currentThread().setName(this.getClass().getSimpleName()+System.currentTimeMillis());
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        //获取当前时间
        long currentTime = System.currentTimeMillis();

        long millis = clock() - currentTime;

        /*if (millis > 0) {
            return;
        }*/

        while (true) {
            Jedis jedis = jedisPool.getResource();
            List<Object> result = null;
            try {

                System.out.println(MessageFormat.format("商品{0}",productName));

                int proNum = Integer.parseInt(jedis.get(productName));
                System.out.println(MessageFormat.format("商品{0}数量为{1}", productName, proNum));
                if (proNum > 0) {
                    //监听商品
                    jedis.watch(productName);
                    int proNumReal = Integer.parseInt(jedis.get(productName));

                    if (proNumReal < proNum) {
                        jedis.unwatch();
                    } else {
                        Transaction transaction = jedis.multi();

                        transaction.set(productName, String.valueOf(proNum - 1));

                        result = transaction.exec();
                    }

                    if (result == null || result.isEmpty()) {
                        System.out.println(Thread.currentThread().getName() + "\t正在排队抢购\t" + productName + "....");
                    } else {
                        counter++;

                        switch (counter) {
                            case 1:
                                start = System.currentTimeMillis();
                                break;

                            case 50:
                                time = System.currentTimeMillis() - start;
                                System.out.println("===================" + time);
                                break;
                            default:
                                break;
                        }
                        String str = Thread.currentThread().getName() + "\t抢购成功，商品名为：\t" + productName + "\t抢购时间："
                                + new Timestamp(new Date().getTime());
                        System.out.println(str);
                        // 把抢购成功的顾客信息打印出去
                        print(str);

                    }


                } else {
                    System.out.println(MessageFormat.format("商品{0}已经抢完", productName));
                    break;
                }
            }catch(Exception e){
                e.printStackTrace();
                RedisUtil.returnResource(jedis);

            }finally {
                RedisUtil.returnResource(jedis);
            }


        }

    }
}

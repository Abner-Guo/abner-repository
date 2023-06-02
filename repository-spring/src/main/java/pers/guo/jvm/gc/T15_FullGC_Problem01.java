package pers.guo.jvm.gc;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/5/23 10:28
 * 从数据库中读取信用数据，套用模型，并把结果进行记录和传输
 * TODO GC优化测试案例，Linux
 */
public class T15_FullGC_Problem01 {

    private static class CardInfo {
        BigDecimal price = new BigDecimal(0.0);
        String name = "张三";
        int age = 5;
        Date birthdate = new Date();

        public void m() {}
    }


    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws Exception {
        executor.setMaximumPoolSize(50);
        for (;;){
            modelFit();
            Thread.sleep(100);
        }
    }

    private static void modelFit(){
        List<CardInfo> taskList = getAllCardInfo();

        taskList.forEach(info -> {

            // do something
            executor.scheduleWithFixedDelay(() -> {
                //do sth with infoNioChannel
                info.m();

            }, 2, 3, TimeUnit.SECONDS);
        });
    }


    private static void modelFit_Update(){
        List<CardInfo> taskList = getAllCardInfo();
        List<CardInfo> cardInfos = taskList.subList(0, 49);
        List<CardInfo> cardInfos1 = taskList.subList(50, 99);


            // do something
            executor.scheduleWithFixedDelay(() -> {
                for (CardInfo cardInfo : cardInfos) {
                    cardInfo.m();
                }

            }, 2, 3, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(() -> {
            for (CardInfo cardInfo : cardInfos1) {
                cardInfo.m();
            }

        }, 2, 3, TimeUnit.SECONDS);
    }

    private static List<CardInfo> getAllCardInfo(){
        List<CardInfo> taskList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            CardInfo ci = new CardInfo();
            taskList.add(ci);
        }

        return taskList;
    }

}

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestCyclicBarrier {

    static void testDifferent(int maxToleration, int timeoutShumei, int timeoutZiyan) {
        long start = System.currentTimeMillis();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                long end = System.currentTimeMillis();
                System.out.println("cyclicBarrier finish" + (end - start));
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                long startNewThread = System.currentTimeMillis();
                try {
                    Thread.sleep(timeoutZiyan);//调用自研
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long endNewThread = System.currentTimeMillis();
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("调用自研花费 " + (endNewThread - startNewThread));
            }
        });
        thread2.start();
        try {
            Thread.sleep(timeoutShumei);//调用数美
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        long timeMillis = System.currentTimeMillis();
        System.out.println("调用数美" + (timeMillis - start));
        try {
            try {
                cyclicBarrier.await(maxToleration-timeoutShumei, TimeUnit.MILLISECONDS);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        catch (TimeoutException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        finally {
            long finish = System.currentTimeMillis();
            System.out.println("finish" + (finish - start));
        }
    }

    public static void main() {
        int max = 40;//数美的调用时间
        int min = 20;//自研的调用时间
        long l = System.currentTimeMillis();
        testDifferent(40,max, min);//正常,数美比自研慢
        long l1 = System.currentTimeMillis();
        testDifferent(40,max, max-1);//正常,数美和自研 调用await()时 自研barrier还没调用await()
        long l2 = System.currentTimeMillis();
        testDifferent(40,min, max);//数美和自研快 可以等一下.正常
        long l3 = System.currentTimeMillis();
        testDifferent(40,50, min);//正常
        long l4 = System.currentTimeMillis();
        testDifferent(40,20, 50);//自研异常,只需要数美 数美比自研快太多,而且数美在等待的时间内已经耗尽时间,
        long l5 = System.currentTimeMillis();
        testDifferent(40,min, min);//正常,数美和自研在40ms阈值内 数美可以等一下.
        long l6 = System.currentTimeMillis();
        System.out.println(l6-l5);
        System.out.println(l5-l4);
        System.out.println(l4-l3);
        System.out.println(l3-l2);
        System.out.println(l2-l1);
        System.out.println(l1-l);
    }
}

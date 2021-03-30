import java.util.Random;
import java.util.concurrent.*;

public class TestFuture {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,1,1L, TimeUnit.SECONDS,new ArrayBlockingQueue(10));
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                //一些阻塞调用
                Thread.sleep(1000);
                System.out.println("Thread name:"+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "你好呀";
        }).thenApplyAsync(s -> {
            System.out.println(s);
            return s+" hello again";
        });
        System.out.println(" "+completableFuture.isDone());
        System.out.println(completableFuture.isDone());
        completableFuture.thenAcceptAsync(s->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread name:" + Thread.currentThread().getName());

        });
        Random random = new Random(10);
        while(!completableFuture.isDone()){
            Thread.sleep(100);
            if (random.nextInt(10) > 10){
                System.out.println("cancel");
                completableFuture.cancel(true);
                System.out.println();
            }
            System.out.println(System.currentTimeMillis()+" is done "+completableFuture.isDone());
        }
        String s = completableFuture.get();
        System.out.println("get from future:"+s);
        System.out.println("is cancelled :"+completableFuture.isCancelled());
        System.out.println("is done "+completableFuture.isDone());

    }
}

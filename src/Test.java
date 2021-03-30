import java.util.concurrent.*;

public class Test {



	public static void main(String[] args) throws Exception {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,1,1L,TimeUnit.SECONDS,new ArrayBlockingQueue(10));
		Callable<String> stringCallable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("aaaaaaaaa");
				return "aaaaaaaaa";
			}
		};
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
			try {
				Thread.sleep(1000);
				//一些阻塞调用
				System.out.println("Thread name:"+Thread.currentThread().getName());
				System.out.println("aaaaaaaaa+1000");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "aaaaaaa";
		},threadPoolExecutor);

		completableFuture.cancel(true);
		while (completableFuture.isDone()){
			String s1 = completableFuture.get();
			System.out.println("task had no complete get value "+s1);
		}
		FutureTask futureTask = new FutureTask(stringCallable);
		threadPoolExecutor.execute(futureTask);
		Thread.sleep(10000);
	}
}

```java
ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,1,1L,TimeUnit.SECONDS,new ArrayBlockingQueue(10));
//用法
CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
			try {
				Thread.sleep(1000);
				System.out.println("Thread name:"+Thread.currentThread().getName());
				System.out.println("aaaaaaaaa+1000");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "aaaaaaa";
		},threadPoolExecutor);
```
分析
```java
supplyAsync()
    asyncSupplyStage(screenExecutor(executor), supplier)//异步提交阶段
        CompletableFuture<U> d = new CompletableFuture<U>();//这里new CompletableFuture
        e.execute(new AsyncSupply<U>(d, f)); //这个异步提交者实现了Runnable static final class AsyncSupply<T> extends ForkJoinTask<Void> implements Runnable, AsynchronousCompletionTask
```
//然后到CompletableFuture$AsyncSupply#run()
```java
    public void run() {
        CompletableFuture<T> d; Supplier<T> f;
        if ((d = dep) != null && (f = fn) != null) {
            dep = null; fn = null;
            if (d.result == null) {//判断结果是否拿到,如果没有拿到的话
                try {
                    d.completeValue(f.get());//从提供者里获取结果.里面用了UNSAFE类获取结果
                } catch (Throwable ex) {
                    d.completeThrowable(ex);//提供函数抛出异常
                }
            }
            d.postComplete();//发布完成事件
        }
    }
```
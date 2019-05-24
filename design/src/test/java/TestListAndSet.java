import java.util.*;
import java.util.concurrent.*;

public class TestListAndSet {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
       while (true) {
           Future<Boolean> future = executorService.submit(new MyThread());
           System.out.println(future.get().booleanValue());
           if(!future.get().booleanValue()){
               break;
           }
       }
       executorService.shutdown();


       List list = new ArrayList<>();
       list.add(1);
       list.add(2);
       LinkedList linkedList = new LinkedList(list);
      // HashMap
    }


    static class MyThread implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            System.out.println("===================start======================");
            Set<Integer> set = new HashSet<Integer>();
            for (int i = 1 ; i < 300 ; i ++){
                set.add(i);
            }

            for (Integer integer : set) {
                System.out.println(integer);
            }

            return true;
        }
    }
}

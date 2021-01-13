import java.util.HashMap;

public class ReadHashMapMain {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(1<<30);
        Object o = new Object();
        //error
        //IllegalArgumentException
        //HashMap hashMap1 = new HashMap(1<<31);
        //IllegalArgumentException
        //HashMap hashMap = new HashMap(0);
        // initial capacity = 1<<4 = 16
        HashMap hashMap = new HashMap();
        hashMap.put(1,1);
        System.out.println();
        for(int i=0;i<16;i++){
            hashMap.put(1,1);
        }
        HashMap hashMap1 = new HashMap();
        for(int i=0;i<16;i++){
            hashMap1.put(i,i);
        }


        hashMap.put(1,1);
        System.out.println(hashMap);
        boolean interrupted = Thread.interrupted();
        System.out.println(interrupted);
//        Object o = null;
//        System.out.println(o.hashCode());
    }
}

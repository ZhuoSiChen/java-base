import java.util.HashMap;

public class ReadHashMapMain {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(1<<30);
        //error
        //IllegalArgumentException
        //HashMap hashMap1 = new HashMap(1<<31);
        //IllegalArgumentException
        //HashMap hashMap = new HashMap(0);
        // initial capacity = 1<<4 = 16
        HashMap hashMap = new HashMap();
        System.out.println();
        System.out.println(hashMap);
//        Object o = null;
//        System.out.println(o.hashCode());
    }
}

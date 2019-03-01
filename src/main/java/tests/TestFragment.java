package tests;

import leetcode.TestCase;
import org.junit.jupiter.api.Test;
import util.Either;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * Created by camork on 2019-01-19.
 */
public class TestFragment extends TestCase {

    void swap(IntClass a, IntClass b) {
        int c = a._a;
        a._a = b._a;
        b._a = c;
    }

    public void add(Byte b) {
        b = b++;
    }

    @Test
    void testCollection() {
        Set<String> listeners = new LinkedHashSet<>();

        listeners.add("11");
        listeners.add("22");
        listeners.add("33");
        listeners.add("44");

        Collection<String> _listeners;

        _listeners = new ArrayList<>(listeners);

        for (String listener : _listeners) {
            listeners.remove(listener);
        }
    }

    @Test
    void testSwap() {
        IntClass a = new IntClass(1);
        IntClass b = new IntClass(2);
        System.out.println(a + "" + b);
        swap(a, b);
        System.out.println(a + "" + b);
    }

    @Test
    void testEither() {
        File[] a = new File[]{new File("pom.xml"), new File("bb")};

        Stream.of(
                a
        ).map(
                Either.lift(FileInputStream::new)
        ).filter(
                Either::isRight
        ).forEach(
                System.out::println
        );
    }

    @Test
    void testString() {
        String foo = "foo";
        String foo1 = "foo";
        String foo2 = new String("foo").intern();
        assert foo == foo2;
    }

    @Test
    void testModifyMap() {
        Map<String,String> myMap = new ConcurrentHashMap<>();
        myMap.put("1", "1");
        myMap.put("2", "2");
        myMap.put("3", "3");

        Iterator<String> it1 = myMap.keySet().iterator();
        while (it1.hasNext()) {
            String key = it1.next();
            System.out.println("Map Value:" + myMap.get(key));
            if (key.equals("1")) {
                myMap.remove("3");
                myMap.put("4", "4");
                myMap.put("5", "5");
            }
        }

        System.out.println("Map Size:" + myMap.size());
    }

    @Test
    void testConstant() {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
    }

    @Test
    void testEnum() {
        SingletonEnum a = SingletonEnum.INSTANCE;
        System.out.println(a);

        SingletonEnum.values();
    }

    @Test
    void testAutoUnbox() {
        Integer w = 3;//INVOKESTATIC java/lang/Integer.valueOf (I)Ljava/lang/Integer;
        w = 4;
        ArrayList arrayList = new ArrayList(w);//INVOKEVIRTUAL java/lang/Integer.intValue ()I

        Byte a = 127;
        Byte b = 127;
        add(++a);
        System.out.print(a + " ");
        add(b);
        System.out.print(b + "");
    }

    @Test
    void testMonitor() throws InterruptedException {
        new Thread(Monitor::staticSyn).start();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> new Monitor().syn()).start();  //won't be blocked.
            new Thread(() -> new Monitor().staticSyn()).start();//blocked
        }
        Thread.sleep(999999999);
    }

    @Test
    void testIncrease() {
        int x = -1, y = 0;

        y = x++ + ++x; // y= x + (x+1+1) x
        System.out.println(x + y + ++x);
    }

    @Test
    void testThreadState() throws InterruptedException {
        Thread a=new Thread(()->{
            while (true){

            }
        });

        a.start();

        synchronized (a){
            a.wait(10000);
        }

        synchronized (a){
            Thread.sleep(10000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
       int i=2;
        System.out.println(i++);

    }
}

class Monitor {
    synchronized static void staticSyn() {//lock Monitor.Class
        try {
            System.out.println("static locked");

            Thread.sleep(999999999);           //own the monitor
//			synchronized (Thread.currentThread()) {
//				Thread.currentThread().wait();       //won't own the monitor
//			}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void syn() {//lock instance
        System.out.println("locked");
    }
}

class IntClass {
    int _a;

    public IntClass(int a) {
        _a = a;
    }

    public String toString() {
        return "IntClass{" + "_a=" + _a + '}';
    }
}

enum SingletonEnum {
    INSTANCE;
    int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

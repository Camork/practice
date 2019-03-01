public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal<MyInnerClass> a = new ThreadLocal<>();

        // myMap.put(new MyInnerClass("ABc"), "First");
        a.set(new MyInnerClass("AB"));
        a.set(new MyInnerClass("Aw"));
        a.set(new MyInnerClass("x"));
        System.out.println(a);

        new Thread(()->{

            a.set(new MyInnerClass("ABdwdw"));
            a.set(new MyInnerClass("ABdwdw"));
            a.set(new MyInnerClass("ABdwdw"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println(a);
    }
}

class MyInnerClass {
    private String StrA;

    public MyInnerClass(String strA) {
        StrA = strA;
    }

    @Override
    public int hashCode() {
        return StrA.length();
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public String toString() {
        return "StrA='"+StrA;
    }
}
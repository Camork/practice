package tests;
 
import java.util.LinkedHashMap;

public class HashCollision {

    public static void main(String[] args) {
        LinkedHashMap<MyInnerClass, String> myMap = new LinkedHashMap<>(16, 0.75f,false);

        // myMap.put(new MyInnerClass("ABc"), "First");
        myMap.put(new MyInnerClass("AB"), "First");
        myMap.put(new MyInnerClass("Aw"), "First");
        myMap.put(new MyInnerClass("x"), "First");
        System.out.println(myMap);
    }
}
 
class MyInnerClass {
    private String StrA;
 
    public MyInnerClass(String strA) {
        super();
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
        return "StrA='" + StrA;
    }
}
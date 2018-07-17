package annotation;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@interface Ares {
	String[] value() default "beijing,dalian,wuhan";

	
	String currentPlace() default "dalian";
}

public class AnnotationTest2 {
	@Ares
	private String addres;
	@Ares(currentPlace = "dalian")
	private String location;

	@Ares
//	public String[] getAddress() throws Exception {
//		Method method = AnnotationTest2.class.getMethod("getAddress", null);
//		method.setAccessible(true);
//		String[] addressArray = null;
//		if (method != null) {
//			boolean bl = method.isAnnotationPresent(Ares.class);
//			if (bl) {
//				Ares add1 = method.getAnnotation(Ares.class);
//				addressArray = add1.value();
//			}
//		}
//		return addressArray;
//	}

	public static void main(String[] args) throws Exception{
		Field f = AnnotationTest2.class.getDeclaredField("addres");
		if (f != null) {
			boolean b = f.isAnnotationPresent(Ares.class);
			if (b) {
				Ares add = f.getAnnotation(Ares.class);
				String[] str = add.value();
				for (String str2 : str) {
					System.out.println(str2);
				}
			}
		}
//		AnnotationTest2 at = new AnnotationTest2();
//		String[] address = at.getAddress();
//		for (String str : address) {
//			System.out.println(str);
//		}
	}
}
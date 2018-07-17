package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.Field;

public class Demo {
	public static void main(String[] args) throws Exception {
		Class clazz = annotation.SxtStudent.class;
		Annotation[] annotations=clazz.getAnnotations();
		for(Annotation a:annotations){
			System.out.println(a);
		}
		Field f=clazz.getDeclaredField("studentName");
		SxtField sxt=f.getAnnotation(SxtField.class);
		System.out.println("name: "+sxt.columnName()+"--"+sxt.type()+"--"+sxt.length());
	}
}

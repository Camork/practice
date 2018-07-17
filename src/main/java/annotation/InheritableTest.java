package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface Inheritable {

}

@Inheritable
class Base {

}

public class InheritableTest extends Base {
	public static void main(String[] args) {
		System.out.println(Inheritable.class.getName());
		System.out.println(InheritableTest.class
				.isAnnotationPresent(Inheritable.class));
	}
}
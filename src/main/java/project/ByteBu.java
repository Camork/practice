//package project;
//
//import net.bytebuddy.ByteBuddy;
//import net.bytebuddy.agent.ByteBuddyAgent;
//import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
//import net.bytebuddy.implementation.MethodDelegation;
//import net.bytebuddy.implementation.bind.annotation.Origin;
//import net.bytebuddy.implementation.bind.annotation.RuntimeType;
//import net.bytebuddy.implementation.bind.annotation.This;
//
//import java.lang.invoke.MethodHandle;
//
//import static net.bytebuddy.matcher.ElementMatchers.nameStartsWith;
//
//class Source {
//	private Integer qqe(){
//		return 1;
//	}
//
//	public void hello(String name) {
//		System.out.println("original");
//	}
//}
//
//class Target {
//	@RuntimeType
//	public static void hello(String name, @This Source self, @Origin MethodHandle method) throws Throwable {
//		//System.out.println(runnable);
//		method.invoke(self,name);
//		int i = 0;
//
//		//self.hello("");
//		System.out.println("intercepted");
//	}
//}
//
//public class ByteBu {
//
//	public ByteBu() {
//	}
//
//
//	public static void main(String[] args) throws NoSuchMethodException {
//		ByteBuddyAgent.install();
//
//		new ByteBuddy()
//			.redefine(Source.class)
//			.method(nameStartsWith("hello"))
//			.intercept(MethodDelegation.to(Target.class))
//			.make()
//			.load(Source.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
//
//		Source aa = new Source();
//		aa.hello("aaawd");
//
//	}
//}

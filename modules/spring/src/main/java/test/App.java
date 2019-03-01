package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");

		System.out.println("context 启动成功");
		MessageService messageService = context.getBean(MessageService.class);
		System.out.println(messageService.getMessage());
	}
}
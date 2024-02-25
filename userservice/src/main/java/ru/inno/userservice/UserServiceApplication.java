package ru.inno.userservice;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.inno.userservice.service.UserServiceImpl;

public class UserServiceApplication {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext("ru/inno/userservice");
		UserServiceImpl userServiceImpl = context.getBean(UserServiceImpl.class);
		System.out.println("Filling DB:");
		userServiceImpl.createUser(1L,"ALEX");
		userServiceImpl.createUser(2L,"BOB");
		userServiceImpl.createUser(3L,"DASHA");
		userServiceImpl.createUser(4L,"OLEG");
		userServiceImpl.createUser(5L,"MAX");
		userServiceImpl.createUser(6L,"OLGA");
		userServiceImpl.createUser(7L,"MARINA");
		System.out.println(userServiceImpl.selectUsers());
		System.out.println("Deleting user with ID 3");
		userServiceImpl.deleteUser(3L);
		System.out.println(userServiceImpl.selectUsers());
		System.out.println("Updating user with ID 2");
		userServiceImpl.updateUser(2L,"Sasha");
		System.out.println(userServiceImpl.selectUsers());
		System.out.println("Getting user with ID 7 " + userServiceImpl.getUserById(7L));

		for (int i = 0; i <= userServiceImpl.maxId(); i++) {    // Clear table
			userServiceImpl.deleteUser((long) i);
		}
	}

}

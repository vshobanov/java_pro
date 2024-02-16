package ru.inno.userservice;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.inno.userservice.service.UserService;

public class UserServiceApplication {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext("ru/inno/userservice");
		UserService userService = context.getBean(UserService.class);
		System.out.println("Filling DB:");
		userService.createUser(1L,"ALEX");
		userService.createUser(2L,"BOB");
		userService.createUser(3L,"DASHA");
		userService.createUser(4L,"OLEG");
		userService.createUser(5L,"MAX");
		userService.createUser(6L,"OLGA");
		userService.createUser(7L,"MARINA");
		System.out.println(userService.selectUsers());
		System.out.println("Deleting user with ID 3");
		userService.deleteUser(3L);
		System.out.println(userService.selectUsers());
		System.out.println("Updating user with ID 2");
		userService.updateUser(2L,"Sasha");
		System.out.println(userService.selectUsers());
		System.out.println("Getting user with ID 7 " + userService.getUserById(7L));

		for (int i = 0; i <= userService.maxId(); i++) {    // Clear table
			userService.deleteUser((long) i);
		}
	}

}

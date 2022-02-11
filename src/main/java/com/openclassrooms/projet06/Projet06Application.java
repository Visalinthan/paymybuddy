package com.openclassrooms.projet06;

import com.openclassrooms.projet06.model.Role;
import com.openclassrooms.projet06.model.User;
import com.openclassrooms.projet06.service.RoleService;
import com.openclassrooms.projet06.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class Projet06Application {

	public static void main(String[] args) {
		SpringApplication.run(Projet06Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner runner(UserService userService, RoleService roleService){
		return args -> {
			/*
			Collection<Role> roles = new ArrayList<>();
			Role roleAdmin = new Role();
			roleAdmin.setName("ADMIN");

			Role roleUser = new Role();
			roleUser.setName("USER");

			roles.add(roleAdmin);

			roleService.saveRole(roleAdmin);
			roleService.saveRole(roleUser);

			User user = new User();

			List<User> users = userService.getAllUsers();
			user.setFirstName("Manu");
			user.setFirstName("Phillipe");
			user.setEmail("manu@live.fr");
			user.setPassword("123456");
			user.setContact(users);
			user.setRoles(roles);

			userService.saveAdmin(user);
			*/

		};

	}

}

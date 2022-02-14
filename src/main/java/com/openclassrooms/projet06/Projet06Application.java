package com.openclassrooms.projet06;

import com.openclassrooms.projet06.model.Role;
import com.openclassrooms.projet06.model.User;
import com.openclassrooms.projet06.service.AccountService;
import com.openclassrooms.projet06.service.RoleService;
import com.openclassrooms.projet06.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Projet06Application {

	public static void main(String[] args) {
		SpringApplication.run(Projet06Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner runner(UserService userService, RoleService roleService, AccountService accountService){
		return args -> {

			List<Boolean> users = userService.getAllUsers().stream().map(user -> user.getRole().getName().equals("ROLE_ADMIN")).collect(Collectors.toList());

				if(users.isEmpty()) {
					Role roleAdmin = new Role();
					roleAdmin.setName("ROLE_ADMIN");

					Role roleUser = new Role();
					roleUser.setName("ROLE_USER");

					roleService.saveRole(roleAdmin);
					roleService.saveRole(roleUser);

					User user = new User();

					user.setFirstName("Sandhiran");
					user.setLastName("Vishal");
					user.setEmail("vishal@live.fr");
					user.setPassword(new BCryptPasswordEncoder().encode("123456"));
					user.setContact(null);
					user.setRole(roleAdmin);

					userService.saveAdmin(user);
					accountService.saveAccount(user);
				}

		};

	}

}

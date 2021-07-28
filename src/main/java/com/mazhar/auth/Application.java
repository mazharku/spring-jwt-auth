package com.mazhar.auth;

import com.mazhar.auth.model.Role;
import com.mazhar.auth.model.User;
import com.mazhar.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void initData(){
		Set<Role> roleAdmin = Stream.of(new Role(1, "ADMIN")).collect(Collectors.toSet());
		Set<Role> roleUser = Stream.of(new Role(2, "USER")).collect(Collectors.toSet());

		List<User> users = Stream.of(new User(1, "mazhar", "123", "mazahr@gmail.com", roleAdmin),
				new User(2, "user", "123", "mOne@gmail.com", roleUser))
				.collect(Collectors.toList());

		userRepository.saveAll(users);
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

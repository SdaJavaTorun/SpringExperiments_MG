package com.example.sqlstore;

import com.example.sqlstore.login.Role;
import com.example.sqlstore.login.RoleRepository;
import com.example.sqlstore.login.User;
import com.example.sqlstore.login.UserRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SqlstoreApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SqlstoreApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		Role adminRole = roleRepository.save(new Role("ADMIN"));
		Role userRole = roleRepository.save(new Role("USER"));

		userRepository.save(new User("admin",
				passwordEncoder.encode("admin"),
				Sets.newHashSet(adminRole)));
		userRepository.save(new User("user",
				passwordEncoder.encode("user"),
				Sets.newHashSet(userRole)));

		System.out.println(userRepository.findByUsername("user"));
		System.out.println(roleRepository.findRolesByUsername("admin"));
	}
}

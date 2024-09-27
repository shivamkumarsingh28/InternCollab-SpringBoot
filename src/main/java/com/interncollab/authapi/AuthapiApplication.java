package com.interncollab.authapi;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
// import org.springframework.security.crypto.password.PasswordEncoder;

import com.interncollab.authapi.config.AppConstants;
import com.interncollab.authapi.entities.Role;
import com.interncollab.authapi.repository.RoleRepo;


@SpringBootApplication
public class AuthapiApplication implements CommandLineRunner{

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(AuthapiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// System.out.println(this.passwordEncoder.encode("123"));
		try{
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ROLE_ADMIN");

			Role role1 = new Role();
			role1.setId(AppConstants.STUDENT_USER);
			role1.setName("ROLE_STUDENT");

			Role role2 = new Role();
			role2.setId(AppConstants.COMPANY_USER);
			role2.setName("ROLE_COMPANY");

			List<Role> roles = List.of(role,role1,role2);
			List<Role> result = this.roleRepo.saveAll(roles);

			result.forEach(r->{
			System.out.println(r.getName());
			});
		}catch(Exception e){
			e.printStackTrace();
		}
	}


}

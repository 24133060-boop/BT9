package vn.iotstar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;
import vn.iotstar.repositories.RoleRepository;
import vn.iotstar.repositories.UserRepository;

@SpringBootApplication
public class SpringSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // 1. Kiểm tra hoặc tạo mới Role "ADMIN"
            Role adminRole = roleRepository.findByName("ADMIN");
            if (adminRole == null) {
                adminRole = new Role();
                adminRole.setName("ADMIN");
                adminRole = roleRepository.save(adminRole);
            }

            // 2. Kiểm tra hoặc tạo mới User "admin"
            User admin = userRepository.findByUsername("admin");
            if (admin == null) {
                admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("123456"));
                admin.setEnabled(true);
                admin.getRoles().add(adminRole);
                userRepository.save(admin);
                System.out.println(">>> Đã tạo mới tài khoản admin thành công!");
            } else {
                admin.setPassword(passwordEncoder.encode("123456"));
                admin.setEnabled(true);
                admin.getRoles().clear();
                admin.getRoles().add(adminRole);
                userRepository.save(admin);
                System.out.println(">>> Đã cập nhật tài khoản admin thành công!");
            }
        };
    }
}
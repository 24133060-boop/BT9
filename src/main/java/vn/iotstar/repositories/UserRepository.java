package vn.iotstar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iotstar.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
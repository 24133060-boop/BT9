package vn.iotstar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iotstar.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // Khai báo phương thức tìm kiếm role theo tên
    Role findByName(String name);
}
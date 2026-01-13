package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.User;
import java.util.List;

// crud: create, read, update, delete
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // viết các phương thức trong này
    User save(User hoidanit);

    List<User> findByEmail(String email);

    User findById(long id); // phải định nghĩa ra vì cái cung cấp sẵn có kiểu dữ liệu là Optional - có thể
                            // trả ra null
}

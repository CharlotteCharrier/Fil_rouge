package initiativedeuxsevres.ttm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import initiativedeuxsevres.ttm.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
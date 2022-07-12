package digi.gdt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import digi.gdt.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findById(Integer id);

	User findByEmail(String email);

	boolean existsByEmail(String email);
}

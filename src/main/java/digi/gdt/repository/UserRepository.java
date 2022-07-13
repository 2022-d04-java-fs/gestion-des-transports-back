package digi.gdt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import digi.gdt.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	Optional<Users> findById(Integer id);

	Users findByEmail(String email);

	boolean existsByEmail(String email);
}

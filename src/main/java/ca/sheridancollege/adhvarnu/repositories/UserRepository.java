package ca.sheridancollege.adhvarnu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.adhvarnu.beans.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
}

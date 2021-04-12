package ca.sheridancollege.adhvarnu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.adhvarnu.beans.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}

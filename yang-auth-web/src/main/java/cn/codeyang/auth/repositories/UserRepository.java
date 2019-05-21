package cn.codeyang.auth.repositories;

import cn.codeyang.auth.api.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author akafra
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	Optional<User> findOneByUsername(String username);

	Optional<User> findOneByEmail(String email);

	Optional<User> findOneByEmailIgnoreCase(String email);

	Optional<User> findOneByActivationKey(String activationKey);

	@EntityGraph(attributePaths = "authorities")
	Optional<User> findOneWithAuthoritiesByEmail(String email);

	@EntityGraph(attributePaths = {"authorities", "roles"})
	Optional<User> findOneWithAUthoritiesAndRolesByEmail(String email);

	@EntityGraph(attributePaths = {"authorities", "roles"})
	Optional<User> findOneWithAuthoritiesAndRolesByUsername(String username);

}

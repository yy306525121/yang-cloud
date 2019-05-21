package cn.codeyang.auth.repositories;

import cn.codeyang.auth.api.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author akafra
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	Optional<Authority> findByAuthorityValue(String authorityValue);
}

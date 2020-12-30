package net.galuhpradipta.bbwapi.repository;

import net.galuhpradipta.bbwapi.entity.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {

    @Query(value = "SELECT * FROM auth_tokens WHERE auth_token = :authToken AND expired_at < now() ORDER BY id DESC LIMIT 1", nativeQuery = true)
    AuthToken findByAuthToken(@Param("authToken") String authToken);
}

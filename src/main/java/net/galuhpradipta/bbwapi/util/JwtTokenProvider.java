package net.galuhpradipta.bbwapi.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.extern.slf4j.Slf4j;
import net.galuhpradipta.bbwapi.constant.Constants;
import net.galuhpradipta.bbwapi.entity.AuthToken;
import net.galuhpradipta.bbwapi.repository.AuthTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    @Autowired
    private AuthTokenRepository authTokenRepository;

    public String generate() {
        try {
            Algorithm algorithm = Algorithm.HMAC256(Constants.JWT.SECRET_KEY);
            Date expiredAt = new Date(System.currentTimeMillis() + Constants.JWT.TOKEN_EXPIRATION_TIME);
            String authToken = JWT.create()
                    .withIssuer(Constants.JWT.ISSUER)
                    .withExpiresAt(expiredAt)
                    .sign(algorithm);

            AuthToken model = new AuthToken();
            model.setAuthToken(authToken);
            model.setExpiredAt(expiredAt);
            authTokenRepository.save(model);

            return authToken;
        } catch (JWTCreationException e){
            log.error("failed create token, {}", e.getMessage());
        }
        return null;
    }

}

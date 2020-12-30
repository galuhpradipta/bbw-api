package net.galuhpradipta.bbwapi.service;

import lombok.extern.slf4j.Slf4j;
import net.galuhpradipta.bbwapi.constant.Constants;
import net.galuhpradipta.bbwapi.entity.AuthToken;
import net.galuhpradipta.bbwapi.exception.BBWException;
import net.galuhpradipta.bbwapi.repository.AuthTokenRepository;
import net.galuhpradipta.bbwapi.util.JwtTokenProvider;
import net.galuhpradipta.bbwapi.vo.ResponseAuthTokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Slf4j
@Service
public class AuthService {

    @Value("${bbw.auth.user}")
    String authUser;

    @Value("${bbw.auth.password}")
    String authPassword;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthTokenRepository authTokenRepository;

    public Boolean validateBasicAuth(String authHeader) {
        String originalStringAuth = String.format("%s:%s", authUser, authPassword);
        String encodeToStringAuth = Base64.getEncoder().encodeToString(originalStringAuth.getBytes());
        if (!authHeader.replace("Basic ", "").equals(encodeToStringAuth)) {
            return false;
        }

        return true;
    }

    public ResponseAuthTokenVO generateAuthToken() {
        ResponseAuthTokenVO response = new ResponseAuthTokenVO();
        String authToken = jwtTokenProvider.generate();
        response.setAuthToken(authToken);
        response.setTokenType("Bearer");
        response.setExpiresIn(Constants.JWT.TOKEN_EXPIRATION_TIME.toString());

        return response;
    }

    public Boolean validateAuthToken(String auth) {
        auth = auth.replace("Bearer ", "");
        AuthToken model = authTokenRepository.findByAuthToken(auth);
        if (model == null) throw BBWException.tokenNotValid();
        return true;
    }
}

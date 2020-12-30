package net.galuhpradipta.bbwapi.controller;

import net.galuhpradipta.bbwapi.constant.Constants;
import net.galuhpradipta.bbwapi.service.AuthService;
import net.galuhpradipta.bbwapi.util.JwtTokenProvider;
import net.galuhpradipta.bbwapi.vo.ResponseAuthTokenVO;
import net.galuhpradipta.bbwapi.vo.ResponseVO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@RestController
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/token",
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> generateAuthToken(@RequestHeader("Authorization") String authHeader,
                                    @RequestParam Map<String, String> body)
    {
        ResponseVO responseVO = new ResponseVO();

        if (body.get("grant_type") == null || !body.get("grant_type").equals("client_credentials")) {
            responseVO.setRc(Constants.ResponseCode.GeneralError);
            responseVO.setMessage("field grant_type is required or not valid");
            return new ResponseEntity<Object>(responseVO, HttpStatus.BAD_REQUEST);
        }

        if (authService.validateBasicAuth(authHeader).equals(false)) return new ResponseEntity<Object>(null, HttpStatus.UNAUTHORIZED);
        ResponseAuthTokenVO vo = authService.generateAuthToken();

        return new ResponseEntity<Object>(vo, HttpStatus.OK);
    }
}

package net.galuhpradipta.bbwapi.controller;

import lombok.extern.slf4j.Slf4j;
import net.galuhpradipta.bbwapi.constant.Constants;
import net.galuhpradipta.bbwapi.exception.BBWException;
import net.galuhpradipta.bbwapi.service.AuthService;
import net.galuhpradipta.bbwapi.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;

@Slf4j
public abstract class AbstractRequestHandler {

    @Autowired
    private AuthService authService;

    public AbstractRequestHandler() {
    }

    public AbstractRequestHandler(AuthService authService) {
        this.authService = authService;
    }

    public abstract Object processRequest() throws BBWException, IOException, ParseException;

    public ResponseEntity<Object> getResult(String auth) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        ResponseVO responseVO = new ResponseVO();

        Object data;
        try {
            validateAuth(auth);
            data = processRequest();

        } catch (BBWException e){
            responseVO.setRc(e.getResponseCode());
            responseVO.setMessage(e.getMessage());

            HttpStatus code;
            switch (e.getResponseCode()) {
                case Constants.ResponseCode.VaNotFound:
                    code = HttpStatus.NOT_FOUND;
                    break;
                default:
                    code = HttpStatus.BAD_REQUEST;
            }

            return new ResponseEntity<>(responseVO, headers, code);
        } catch (Exception e){
            responseVO.setRc(Constants.ResponseCode.GeneralError);
            responseVO.setMessage("General Error");
            log.error("SYSTEM ERROR", e);
            return new ResponseEntity<>(responseVO, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        responseVO.setRc(Constants.ResponseCode.Success);
        responseVO.setMessage("Success");
        responseVO.setData(data);

        return new ResponseEntity<>(responseVO, headers, HttpStatus.OK);
    }

    private void validateAuth(String authToken) {
        if (authService != null) authService.validateAuthToken(authToken);
    }


}

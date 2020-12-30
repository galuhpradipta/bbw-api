package net.galuhpradipta.bbwapi.controller;

import lombok.extern.slf4j.Slf4j;
import net.galuhpradipta.bbwapi.service.AuthService;
import net.galuhpradipta.bbwapi.service.SignatureService;
import net.galuhpradipta.bbwapi.service.VirtualAccountService;
import net.galuhpradipta.bbwapi.vo.RequestInquiryVAVO;
import net.galuhpradipta.bbwapi.vo.RequestPaymentVAVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/va")
public class VirtualAccountController {

    @Autowired
    private AuthService authService;

    @Autowired
    private VirtualAccountService virtualAccountService;

    @Autowired
    private SignatureService signatureService;

    @RequestMapping(value = "/inquiry",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> inquiry(
            @RequestHeader("Authorization") String auth,
            @RequestHeader("X-BBW-Timestamp") String requestTimestamp,
            @RequestHeader("X-BBW-Signature") String requestSignature,
            @RequestBody RequestInquiryVAVO vo
            ) {
        AbstractRequestHandler handler = new AbstractRequestHandler(authService) {
            @Override
            public Object processRequest() {
                return virtualAccountService.inquiry(auth, vo, requestTimestamp, requestSignature);
            }
        };

        return handler.getResult(auth);
    }

    @RequestMapping(value = "/payment",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> payment(
            @RequestHeader("Authorization") String auth,
            @RequestHeader("X-BBW-Timestamp") String requestTimestamp,
            @RequestHeader("X-BBW-Signature") String requestSignature,
            @RequestBody RequestPaymentVAVO vo
    ) {
        AbstractRequestHandler handler = new AbstractRequestHandler(authService) {
            @Override
            public Object processRequest() {
                return virtualAccountService.payment(auth, vo, requestTimestamp, requestSignature);
            }
        };

        return handler.getResult(auth);

    }
}

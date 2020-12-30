package net.galuhpradipta.bbwapi.service;

import net.galuhpradipta.bbwapi.constant.Constants;
import net.galuhpradipta.bbwapi.entity.Transaction;
import net.galuhpradipta.bbwapi.entity.VirtualAccount;
import net.galuhpradipta.bbwapi.exception.BBWException;
import net.galuhpradipta.bbwapi.repository.TransactionRepository;
import net.galuhpradipta.bbwapi.repository.VirtualAccountRepository;
import net.galuhpradipta.bbwapi.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VirtualAccountService {

    @Autowired
    private SignatureService signatureService;

    @Autowired
    private VirtualAccountRepository virtualAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public VirtualAccountVO inquiry(String authToken, RequestInquiryVAVO vo, String reqTimestamp, String reqSignature) {
        signatureService.validateSignature(authToken, vo, reqTimestamp, reqSignature, vo.getClientId());
        VirtualAccount virtualAccount = virtualAccountRepository.findByAccountNumber(vo.getVirtualAccount());
        if (virtualAccount == null) throw BBWException.vaNotFound();

        VirtualAccountVO virtualAccountVO = new VirtualAccountVO();
        virtualAccountVO.setAccountName(virtualAccount.getAccountName());
        return virtualAccountVO;
    }

    public TransactionVO payment(String authToken, RequestPaymentVAVO vo, String reqTimestamp, String reqSignature) {
        signatureService.validateSignature(authToken, vo, reqTimestamp, reqSignature, vo.getClientId());
        VirtualAccount virtualAccount = virtualAccountRepository.findByAccountNumber(vo.getVirtualAccount());
        if (virtualAccount == null) throw BBWException.vaNotFound();

        Double amount;
        try {
            amount = Double.parseDouble(vo.getAmount());
        } catch (NumberFormatException e) {
            throw BBWException.generalError();
        }

        UUID transactionNumber = UUID.randomUUID();

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionNumber(transactionNumber.toString());
        transaction.setNote(vo.getNote());
        transaction.setReferenceNumber(vo.getReferenceNumber());
        transactionRepository.save(transaction);

        TransactionVO transactionVO = new TransactionVO();
        transactionVO.setTransactionNumber(transactionNumber.toString());

        return transactionVO;
    }
}

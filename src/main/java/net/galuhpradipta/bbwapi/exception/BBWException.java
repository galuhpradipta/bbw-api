package net.galuhpradipta.bbwapi.exception;

import net.galuhpradipta.bbwapi.constant.Constants;

public class BBWException extends RuntimeException {


    private String responseCode;
    private String message;
    public BBWException(){
        super();
    }

    public BBWException(String message){
        super(message);
    }

    public BBWException(String responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
        this.message = message;
    }

    public BBWException(String messageFormat, Object... args) {
        super(String.format(messageFormat, args));
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getMessage() {
        return message;
    }

    public static BBWException tokenNotValid() { return new BBWException(Constants.ResponseCode.TokenNotValid, "Token not Valid"); }
    public static BBWException vaNotFound() {return new BBWException(Constants.ResponseCode.VaNotFound, "Va not Found"); }
    public static BBWException signatureNotValid() {return new BBWException(Constants.ResponseCode.SignatureNotValid, "Signature not Valid"); }
    public static BBWException clientIDNotFound() {return new BBWException(Constants.ResponseCode.ClientIDNotFount, "Client ID not found"); }
    public static BBWException generalError() {return new BBWException(Constants.ResponseCode.GeneralError, "General Error"); }



}

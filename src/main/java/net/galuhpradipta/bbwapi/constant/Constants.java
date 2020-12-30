package net.galuhpradipta.bbwapi.constant;

public class Constants {

    public static final class JWT {
        public static final String ISSUER = "bbw";
        public static final String SECRET_KEY = "bbw-s3cr3t";
        public static final Integer TOKEN_EXPIRATION_TIME = 3600;
    }

    public static final class ResponseCode {
        public static final String Success = "000";
        public static final String HeaderNotComplete = "001";
        public static final String TokenNotValid = "002";
        public static final String VaNotFound = "003";
        public static final String SignatureNotValid = "004";
        public static final String ClientIDNotFount = "005";
        public static final String GeneralError = "006";
    }
}

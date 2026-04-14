package com.oynee.portfolio.partner.domain.exception;

public abstract class PartnerDomainException extends RuntimeException {
    protected PartnerDomainException(String message) {
        super(message);
    }

    protected PartnerDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}

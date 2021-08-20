package com.springboot.lombok.exception;


public class NotEnoughStockQuantityException extends RuntimeException {

	
    public NotEnoughStockQuantityException() {
        super("해당 상품의 재고가 부족합니다.");
    }

    public NotEnoughStockQuantityException(String message) {
        super(message);
    }

    public NotEnoughStockQuantityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockQuantityException(Throwable cause) {
        super(cause);
    }

    protected NotEnoughStockQuantityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

	
}

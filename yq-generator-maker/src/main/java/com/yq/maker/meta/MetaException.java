package com.yq.maker.meta;

/**
 * @author lyq
 * @description:
 * @date 2023/12/8 14:50
 */
public class MetaException extends RuntimeException{

    public MetaException(String message) {
        super(message);
    }

    public MetaException(String message, Throwable cause) {
        super(message, cause);
    }
}

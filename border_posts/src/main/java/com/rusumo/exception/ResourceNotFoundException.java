package com.rusumo.exception;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
public class ResourceNotFoundException extends RuntimeException {
     private static final long serialVersionUID = 1L;

             public ResourceNotFoundException(String msg) {
        super(msg);
    }
}

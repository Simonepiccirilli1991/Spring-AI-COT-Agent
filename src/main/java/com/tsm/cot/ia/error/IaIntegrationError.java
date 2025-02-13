package com.tsm.cot.ia.error;

import lombok.Data;

@Data
public class IaIntegrationError extends RuntimeException{

    private String msg;

    public IaIntegrationError(String msg) {
        this.msg = msg;
    }
}

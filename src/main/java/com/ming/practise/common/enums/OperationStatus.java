package com.ming.practise.common.enums;

import lombok.Getter;

public enum OperationStatus {
    SUCCESS("success"),
    FAILURE("failure");

    @Getter
    private String status;

    OperationStatus(String status) {
        this.status = status;
    }
}


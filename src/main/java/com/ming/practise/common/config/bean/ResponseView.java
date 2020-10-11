package com.ming.practise.common.config.bean;

import com.ming.practise.common.enums.OperationStatus;
import lombok.Data;

@Data
public class ResponseView<T> {
    private String status;
    private T data;

    public ResponseView(OperationStatus operationStatus, T data) {
        this.status = operationStatus.getStatus();
        this.data = data;
    }
}

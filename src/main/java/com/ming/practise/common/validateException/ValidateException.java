package com.ming.practise.common.validateException;

import java.util.List;

public class ValidateException extends RuntimeException {
    private List<String> messages;

    public List<String> getMessages () {
        return messages;
    }

    public void setMessages (List<String> messages) {
        this.messages = messages;
    }

    public ValidateException (List<String> messages) {
        this.messages = messages;
    }
}

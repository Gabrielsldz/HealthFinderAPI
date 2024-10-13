package com.example.saudeapiback.utils;

import org.springframework.stereotype.Component;

@Component
public class UpdateStatus {

    private volatile boolean updating = false;

    public boolean isUpdating() {
        return updating;
    }

    public void setUpdating(boolean updating) {
        this.updating = updating;
    }
}

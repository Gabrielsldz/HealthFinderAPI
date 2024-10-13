package com.example.saudeapiback.utils;

import lombok.*;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatus {

    private volatile boolean updating = false;

}

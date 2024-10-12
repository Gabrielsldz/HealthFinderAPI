package com.example.saudeapiback.controllers;


import com.example.saudeapiback.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/database")
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;

    @PostMapping("/updateDb")
    public String iniciarAtualizacao() {
        databaseService.updateAndReset();  // Chama o método assíncrono
        return "Atualização do banco de dados iniciada.";  // Retorna imediatamente
    }
}

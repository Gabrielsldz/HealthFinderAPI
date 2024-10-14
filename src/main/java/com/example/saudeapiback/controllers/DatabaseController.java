package com.example.saudeapiback.controllers;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.saudeapiback.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/database")
@Tag(name = "Base de Dados", description = "Gerenciamento e atualização da base de dados")
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;

    @PostMapping("/updateDb")
    public String iniciarAtualizacao() {
        databaseService.updateAndReset();  // Chama o método assíncrono
        return "Atualização do banco de dados iniciada.";  // Retorna imediatamente
    }
}
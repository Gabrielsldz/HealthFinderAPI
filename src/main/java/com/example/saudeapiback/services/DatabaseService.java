package com.example.saudeapiback.services;

import com.example.saudeapiback.utils.ScraperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    @Autowired
    private ScraperUtil scraperUtil;

    @Async
    public void updateAndReset() {
        try {
            System.out.println("Atualizando banco de dados...");
            scraperUtil.getAndPopulateEstablishments();  // Chamando o método de instância
            System.out.println("Banco de dados atualizado com sucesso!");
        } finally {
            System.out.println("Atualização do banco de dados finalizada.");
        }
    }
}
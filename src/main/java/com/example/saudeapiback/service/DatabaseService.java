package com.example.saudeapiback.service;

import com.example.saudeapiback.utils.ScraperUtil;
import com.example.saudeapiback.utils.UpdateStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    @Autowired
    private ScraperUtil scraperUtil;

    @Autowired
    private UpdateStatus updateStatus;

    @Async
    public void updateAndReset() {
        try {
            updateStatus.setUpdating(true);  // Inicia o flag de atualização
            System.out.println("Atualizando banco de dados...");
            scraperUtil.getAndPopulateEstablishments();  // Chamando o método de instância
            System.out.println("Banco de dados atualizado com sucesso!");
        } finally {
            updateStatus.setUpdating(false);  // Finaliza o flag de atualização
            System.out.println("Atualização do banco de dados finalizada.");
        }
    }
}

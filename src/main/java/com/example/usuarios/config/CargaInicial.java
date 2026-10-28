package com.example.usuarios.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.usuarios.model.Usuario;
import com.example.usuarios.repository.UsuarioRepository;

@Configuration
public class CargaInicial {

    @Bean
    CommandLineRunner carregarUsuarios(UsuarioRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Usuario("Pedro", "Ramos", "12345678900"));
                repository.save(new Usuario("Ana", "Silva", "23456789012"));
                repository.save(new Usuario("Lucas", "Santos", "34567890123"));
                repository.save(new Usuario("Mariana", "Oliveira", "45678901234"));
                repository.save(new Usuario("João", "Souza", "56789012345"));
            }
        };
    }
}

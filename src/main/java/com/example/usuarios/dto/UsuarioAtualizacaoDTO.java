package com.example.usuarios.dto;

import jakarta.validation.constraints.Pattern;

public class UsuarioAtualizacaoDTO {
	
	@Pattern(regexp = ".*\\S.*", message = "O nome não pode estar vazio")
	private String nome;
	
	
	@Pattern(regexp = ".*\\S.*", message = "O sobrenome não pode estar vazio")
	private String sobrenome;
	
	@Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 números")
	private String cpf;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
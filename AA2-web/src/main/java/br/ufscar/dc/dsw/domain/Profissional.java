package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
create table Profissional( 
    email varchar(30) not null, 
    senha varchar(20) not null, 
    cpf bigint not null, 
    nome varchar(50) not null, 
    telefone varchar(15) not null, 
    sexo varchar(1) not null, 
    nascimento date not null, 
    primary key (cpf)
);
	*/

@SuppressWarnings("serial")
@Entity
@Table(name = "Profissional")
public class Profissional extends AbstractEntity<Long>{
	@NotBlank(message = "{NotBlank.profissional.cpf}")
	@Size(min = 11, max = 11, message = "{Size.profissional.CPF}")
	@Column(nullable = false, unique = true, length = 60)
	private String cpf;

	@NotBlank(message = "{NotBlank.profissional.email}")
	@Column(nullable = false, length = 60)
	private String email;
    
	@NotBlank(message = "{NotBlank.profissional.senha}")
	@Size(min = 6, message = "{Size.profissional.senha}")
	@Column(nullable = false, length = 60)
	private String senha;
	
	@NotNull(message = "{NotNull.profissional.nome}")
	@Column(nullable = false, length = 60)
	private String nome;

	@NotNull(message = "{NotNull.profissional.telefone}")
	@Size(min = 10, message="{Size.profissional.telefone}")
	@Column(nullable = false, length = 60)
	private String telefone;

	@NotNull(message = "{NotNull.profissional.sexo}")
	@Column(nullable = false, length = 60)
	private String sexo;

	@NotNull(message = "{NotNull.profissional.nascimento}")
	@Size(min = 6, max = 8, message="{Size.profissional.nascimento}")
	@Column(nullable = false, length = 60)
	private String nascimento;
	
	@OneToMany(mappedBy = "profissional")
	private List<Inscricao> inscricoes;

	public String getCPF() {
		return cpf;
	}
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getNascimento() {
		return nascimento;
	}
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
}

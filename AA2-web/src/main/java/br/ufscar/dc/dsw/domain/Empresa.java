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
create table Empresa(
	cnpj bigint not null, 
	email varchar(30) not null, 
	senha varchar(20) not null, 
	nome varchar(50) not null, 
	descricao varchar(256) not null, 
	cidade varchar(30) not null, 
	primary key (cnpj)
);
*/

@SuppressWarnings("serial")
@Entity
@Table(name = "Empresa")
public class Empresa extends AbstractEntity<Long> {

	@NotBlank(message = "{NotBlank.empresa.CNPJ}")
	@Size(min = 18, max = 18, message = "{Size.profissional.CNPJ}")
	@Column(nullable = false, length = 60)
	private String cnpj;

	@NotBlank(message = "{NotBlank.empresa.email}")
	@Column(nullable = false, length = 60)
	private String email;
    
	@NotBlank(message = "{NotBlank.empresa.senha}")
	@Size(min = 6, message = "{Size.empresa.senha}")
	@Column(nullable = false, length = 60)
	private String senha;
	
	@NotBlank(message = "{NotBlank.empresa.nome}")
	@Column(nullable = false, length = 60)
	private String nome;
    
	@NotNull(message = "{NotNull.empresa.descricao}")
	@Column(nullable = false, length = 200)
	private String descricao;
	
	@NotNull(message = "{NotNull.empresa.cidade}")
	@Column(nullable = false, length = 60)
	private String cidade;
	
	@OneToMany(mappedBy = "empresa")
	private List<Vaga> vagas;
	


	public String getCNPJ() {
		return cnpj;
	}
	public void setCNPJ(String cnpj) {
		this.cnpj = cnpj;
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
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
}
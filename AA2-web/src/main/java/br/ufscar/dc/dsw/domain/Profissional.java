package br.ufscar.dc.dsw.domain;

import java.util.List;
import br.ufscar.dc.dsw.domain.Usuario;
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
public class Profissional extends Usuario{
	@NotBlank(message = "{NotBlank.profissional.cpf}")
	@Size(min = 14, max = 14, message = "{Size.profissional.CPF}")
	@Column(nullable = false, unique = true, length = 60)
	private String cpf;

	@NotNull(message = "{NotNull.profissional.telefone}")
	@Size(min = 10, message="{Size.profissional.telefone}")
	@Column(nullable = false, length = 60)
	private String telefone;

	@NotNull(message = "{NotNull.profissional.sexo}")
	@Column(nullable = false, length = 60)
	private String sexo;

	@NotNull(message = "{NotNull.profissional.nascimento}")
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
	
	public List<Inscricao> getInscricao() {
		return inscricoes;
	}
	public void setLivros(List<Inscricao> inscricoes) {
		this.inscricoes = inscricoes;
	}
}

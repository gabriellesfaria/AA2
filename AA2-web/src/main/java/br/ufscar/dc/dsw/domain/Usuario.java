package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "Usuario")
public class Usuario extends AbstractEntity<Long> {
	@NotBlank(message = "{NotBlank.usuario.email}")
	@Column(nullable = false, length = 60)
	private String email;
    
	@NotBlank(message = "{NotBlank.usuario.senha}")
	@Size(min = 6, message = "{Size.usuario.senha}")
	@Column(nullable = false, length = 60)
	private String senha;
	
	@NotNull(message = "{NotNull.profissional.nome}")
	@Column(nullable = false, length = 60)
	private String nome;
	
    @NotBlank
    @Column(nullable = false, length = 20)
    private String role;
    
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}

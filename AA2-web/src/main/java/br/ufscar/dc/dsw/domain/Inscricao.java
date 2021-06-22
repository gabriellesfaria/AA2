package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/*
create table Inscricao(
    id bigint not null auto_increment, 
    cv varchar(100) not null, 
    status varchar(20) not null, 
    data_inscricao date not null, 
    profissional bigint not null, 
    vaga bigint not null, 
    primary key(id),  
    foreign key(profissional) references Profissional(cpf) ON DELETE CASCADE, 
    foreign key(vaga) references Vaga(id) ON DELETE CASCADE
); 
*/

@SuppressWarnings("serial")
@Entity
@Table(name = "Inscricao")
public class Inscricao extends AbstractEntity<Long> {
	
	@NotBlank(message = "{NotBlank.inscricao.cv}")
	@Column(nullable = false, length = 60)
	private String cv;
	
	@Column(nullable = false, length = 60)
	private String status;  // ABERTO, NAO SELECIONADO OU ENTREVISTA
	
	@Column(nullable = false, length = 19)
	private String data;
	
	@ManyToOne // muitas inscrições possíveis para cada profissional
	@JoinColumn(name = "profissional")
	private Profissional profissional;
	
	@ManyToOne // muitas inscricoes em uma vaga apenas
	@JoinColumn(name = "vaga")
	private Vaga vaga;

	public String getCV() {
		return cv;
	}
	public void setCV(String cv) {
		this.cv = cv;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public Profissional getProfissional() {
		return profissional;
	}
	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
	
	public Vaga getVaga() {
		return vaga;
	}
	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

}
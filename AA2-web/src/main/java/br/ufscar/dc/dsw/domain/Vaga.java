package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

/*
create table Vaga( 
    id bigint not null auto_increment,
    nome varchar(60) not null, 
    status varchar(20) not null, 
    descricao varchar(256) not null, 
    salario float not null, 
    data_limite date not null, 
    empresa bigint not null, 
    primary key(id), 
    foreign key (empresa) references Empresa(cnpj) ON DELETE CASCADE
); 
 * */

@SuppressWarnings("serial")
@Entity
@Table(name = "Vaga")
public class Vaga extends AbstractEntity<Long> {

	@NotBlank(message = "{NotBlank.vaga.nome}")
	@Size(max = 60)
	@Column(nullable = false, length = 60)
	private String nome;

	@NotNull(message = "{NotBlank.vaga.status}")
	@Size(max = 60)
	@Column(nullable = false, length = 60)
	private String status;
    
	@NotNull(message = "{NotNull.vaga.descricao}")
	@Column(nullable = false, length = 60)
	private String descricao;
	
	@NotNull(message = "{NotNull.vaga.salario}")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
	private BigDecimal salario;
    
	@NotNull(message = "{NotNull.vaga.datalimite}")
	@Column(nullable = false, length = 19)
	private String datalimite;
	
	@NotNull(message = "{NotNull.vaga.empresa}")
	@ManyToOne // muitas vagas em apenas uma empresa
	@JoinColumn(name = "empresa")
	private Empresa empresa;
	
	@OneToMany(mappedBy = "vaga")
	private List<Inscricao> inscricoes;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	
	public String getDataLimite() {
		return datalimite;
	}
	public void setDataLimite(String datalimite) {
		this.datalimite = datalimite;
	}

	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}
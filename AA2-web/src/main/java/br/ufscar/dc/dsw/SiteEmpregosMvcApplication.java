package br.ufscar.dc.dsw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.math.BigDecimal;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.dao.InscricaoDAO;
import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.dao.VagaDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SiteEmpregosMvcApplication {
	public static void main(String[] args) {
		SpringApplication.run(SiteEmpregosMvcApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(UsuarioDAO usuarioDAO, ProfissionalDAO profissionalDAO, EmpresaDAO empresaDAO, VagaDAO vagaDAO, InscricaoDAO inscricaoDAO, BCryptPasswordEncoder encoder) {
		return (args) -> {	
			
			Usuario u1 = new Usuario();
			u1.setEmail("admin");
			u1.setNome("admin");
			u1.setRole("ADMIN");
			u1.setSenha(encoder.encode("senha123"));
			usuarioDAO.save(u1);
			
														/** PROFISSIONAIS **/  
			
			Profissional p1 = new Profissional();
			p1.setCPF("926.889.390-84");
			p1.setSexo("Masculino");
			p1.setNascimento("02/03/2010");
			p1.setTelefone("1923453344");
			p1.setEmail("mario@gmail.com");
			p1.setSenha(encoder.encode("senha123"));
			p1.setNome("Mario Luis Goncalves");
			p1.setRole("PROFISSIONAL");
			profissionalDAO.save(p1);
			
			Profissional p2 = new Profissional();
			p2.setCPF("600.444.280-14");
			p2.setTelefone("1123451344");
			p2.setSexo("Feminino");
			p2.setNascimento("02/03/2020");
			p2.setEmail("maria@gmail.com");
			p2.setSenha(encoder.encode("senha123"));
			p2.setNome("Maria Aparecida");
			p2.setRole("PROFISSIONAL");
			profissionalDAO.save(p2);
			
			Profissional p3 = new Profissional();
			p3.setCPF("731.438.430-47");
			p3.setTelefone("3523451354");
			p3.setSexo("Feminino");
			p3.setNascimento("02/04/2020");
			p3.setEmail("joana@gmail.com");
			p3.setSenha(encoder.encode("senha123"));
			p3.setNome("Joana Das Dores");
			p3.setRole("PROFISSIONAL");
			profissionalDAO.save(p3);			
			
			Profissional p4 = new Profissional();
			p4.setCPF("359.226.590-30");
			p4.setTelefone("1234123412");
			p4.setSexo("Feminino");
			p4.setNascimento("22/11/1998");
			p4.setEmail("mariana@gmail.com");
			p4.setSenha(encoder.encode("senha123"));
			p4.setNome("Mariana Coelho");
			p4.setRole("PROFISSIONAL");
			profissionalDAO.save(p4);			
			
			Profissional p5 = new Profissional();
			p5.setCPF("773.459.710-66");
			p5.setTelefone("1132561523");
			p5.setSexo("Feminino");
			p5.setNascimento("02/04/2020");
			p5.setEmail("claus@gmail.com");
			p5.setSenha(encoder.encode("senha123"));
			p5.setNome("Claudia Silva");
			p5.setRole("PROFISSIONAL");
			profissionalDAO.save(p5);			
			
			Profissional p6 = new Profissional();
			p6.setCPF("767.276.750-07");
			p6.setTelefone("1962731524");
			p6.setSexo("Masculino");
			p6.setNascimento("02/04/2020");
			p6.setEmail("joaomiguel@gmail.com");
			p6.setSenha(encoder.encode("senha123"));
			p6.setNome("Joao Miguel Freitas");
			p6.setRole("PROFISSIONAL");
			profissionalDAO.save(p6);			
			
													/** EMPRESAS **/  
			
			Empresa e1 = new Empresa();
			e1.setCNPJ("04.159.402/0001-94");
			e1.setCidade("Campinas");
			e1.setDescricao("Melhor padaria para se trabalhar");
			e1.setEmail("padaria@gmail.com");
			e1.setSenha(encoder.encode("senha123"));
			e1.setNome("Padaria de Pão");
			e1.setRole("EMPRESA");
			empresaDAO.save(e1);	

			Empresa e2 = new Empresa();
			e2.setCNPJ("44.083.863/0001-45");
			e2.setCidade("Santa Catarina");
			e2.setDescricao("Loja de roupas");
			e2.setEmail("loja@gmail.com");
			e2.setSenha(encoder.encode("senha123"));
			e2.setNome("Loja da Maria");
			e2.setRole("EMPRESA");
			empresaDAO.save(e2);
			
															/** VAGAS **/  
		
			Vaga v1 = new Vaga();
			v1.setDataLimite("01/01/2022");
			v1.setDescricao("Faz biscoitos e pães.");
			v1.setNome("Padeiro");
			v1.setSalario(BigDecimal.valueOf(5140.9));
			v1.setStatus("ABERTO");
			v1.setEmpresa(e1);
			vagaDAO.save(v1);
			
			Vaga v2 = new Vaga();
			v2.setDataLimite("01/10/2021");
			v2.setDescricao("Cuida dos pagamentos da padaria");
			v2.setNome("Caixa");
			v2.setSalario(BigDecimal.valueOf(1200.9));
			v2.setStatus("ABERTO");
			v2.setEmpresa(e1);
			vagaDAO.save(v2);
			
			Vaga v3 = new Vaga();
			v3.setDataLimite("01/11/2022");
			v3.setDescricao("Fica responsável pelos pagamentos da loja");
			v3.setNome("Caixa");
			v3.setSalario(BigDecimal.valueOf(4400.86));
			v3.setStatus("ABERTO");
			v3.setEmpresa(e2);
			vagaDAO.save(v3);
			
			Vaga v4 = new Vaga();
			v4.setDataLimite("01/12/2021");
			v4.setDescricao("Cuida do estoque da loja");
			v4.setNome("Estoque");
			v4.setSalario(BigDecimal.valueOf(4000.0));
			v4.setStatus("ABERTO");
			v4.setEmpresa(e2);
			vagaDAO.save(v4);
			
			Vaga v5 = new Vaga();
			v5.setDataLimite("01/09/2021");
			v5.setDescricao("Faz vendas, tem benefícios como VR e VA");
			v5.setNome("Vendedora");
			v5.setSalario(BigDecimal.valueOf(3000.0));
			v5.setStatus("FECHADO");
			v5.setEmpresa(e2);
			vagaDAO.save(v5);
			
			Vaga v6 = new Vaga();
			v6.setDataLimite("01/01/2022");
			v6.setDescricao("Cuida de tudo da loja");
			v6.setNome("Gerente");
			v6.setSalario(BigDecimal.valueOf(6000.0));
			v6.setStatus("ABERTO");
			v6.setEmpresa(e2);
			vagaDAO.save(v6);
																/** INSCRICOES **/  
			Inscricao i1 = new Inscricao();
			i1.setStatus("ABERTO");
			i1.setCV("curriculoMario.pdf");
			i1.setData("22/06/2021");
			i1.setProfissional(p1);
			i1.setVaga(v4);
			inscricaoDAO.save(i1);
			
			Inscricao i2 = new Inscricao();
			i2.setStatus("ABERTO");
			i2.setCV("meucv.pdf");
			i2.setData("25/06/2021");
			i2.setProfissional(p2);
			i2.setVaga(v4);
			inscricaoDAO.save(i2);
			
			Inscricao i3 = new Inscricao();
			i3.setStatus("ABERTO");
			i3.setCV("Maria.pdf");
			i3.setData("27/06/2021");
			i3.setProfissional(p1);
			i3.setVaga(v3);
			inscricaoDAO.save(i3);
			
			Inscricao i4 = new Inscricao();
			i4.setStatus("ABERTO");
			i4.setCV("cuvi.pdf");
			i4.setData("28/04/2021");
			i4.setProfissional(p2);
			i4.setVaga(v6);
			inscricaoDAO.save(i4);
			
			Inscricao i5 = new Inscricao();
			i5.setStatus("ABERTO");
			i5.setCV("cv.pdf");
			i5.setData("22/04/2021");
			i5.setProfissional(p3);
			i5.setVaga(v6);
			inscricaoDAO.save(i5);
			
			Inscricao i6 = new Inscricao();
			i6.setStatus("ABERTO");
			i6.setCV("curriculovitae.pdf");
			i6.setData("22/05/2021");
			i6.setProfissional(p4);
			i6.setVaga(v2);
			inscricaoDAO.save(i6);
			
		};
	}
}
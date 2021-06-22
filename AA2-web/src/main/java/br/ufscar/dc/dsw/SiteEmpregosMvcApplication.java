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
import br.ufscar.dc.dsw.domain.Vaga;
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
			
			Profissional p1 = new Profissional();
			p1.setCPF("926.889.390-84");
			p1.setSexo("Masculino");
			p1.setNascimento("02/03/2010");
			p1.setTelefone("1923453344");
			p1.setEmail("mario@gmail.com");
			p1.setSenha(encoder.encode("senha123"));
			p1.setNome("Mario");
			p1.setRole("PROFISSIONAL");
			profissionalDAO.save(p1);
			
			Profissional p2 = new Profissional();
			p2.setCPF("600.444.280-14");
			p2.setTelefone("1123451344");
			p2.setSexo("Feminino");
			p2.setNascimento("02/03/2020");
			p2.setEmail("maria@gmail.com");
			p2.setSenha(encoder.encode("senha123"));
			p2.setNome("Maria");
			p2.setRole("PROFISSIONAL");
			profissionalDAO.save(p2);
			
			Profissional p3 = new Profissional();
			p3.setCPF("731.438.430-47");
			p3.setTelefone("3523451354");
			p3.setSexo("Feminino");
			p3.setNascimento("02/04/2020");
			p3.setEmail("joana@gmail.com");
			p3.setSenha(encoder.encode("senha123"));
			p3.setNome("Joana");
			p3.setRole("PROFISSIONAL");
			profissionalDAO.save(p3);			
			
			Empresa e1 = new Empresa();
			e1.setCNPJ("04.159.402/0001-94");
			e1.setCidade("Campinas");
			e1.setDescricao("Empresa melhor de Campinas e regi");
			e1.setEmail("empresaa@gmail.com");
			e1.setSenha(encoder.encode("senha123"));
			e1.setNome("Empresa A");
			e1.setRole("EMPRESA");
			empresaDAO.save(e1);		

			Vaga v1 = new Vaga();
			v1.setDataLimite("01/01/2022");
			v1.setDescricao("Vaga muito interessante com salário bom");
			v1.setNome("Padeiro");
			v1.setSalario(BigDecimal.valueOf(5140.9));
			v1.setStatus("ABERTO");
			v1.setEmpresa(e1);
			vagaDAO.save(v1);
			
			Inscricao i1 = new Inscricao();
			i1.setStatus("ABERTO");
			i1.setCV("curriculoMario.pdf");
			i1.setData("21/06/2021");
			i1.setProfissional(p1);
			i1.setVaga(v1);
			inscricaoDAO.save(i1);
			
		};
	}
}
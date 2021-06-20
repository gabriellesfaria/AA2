package br.ufscar.dc.dsw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SiteEmpregosMvcApplication {
	public static void main(String[] args) {
		SpringApplication.run(SiteEmpregosMvcApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(ProfissionalDAO profissionalDAO, BCryptPasswordEncoder encoder) {
		return (args) -> {	
			Profissional p1 = new Profissional();
			p1.setCPF("12543287635");
			p1.setEmail("mario@gmail.com");
			p1.setNome("Mario");
			p1.setSenha(encoder.encode("senha123"));
			p1.setTelefone("1923453344");
			p1.setRole("ROLE_PROFISSIONAL");
			p1.setSexo("masc");
			p1.setNascimento("02/03/2010");
			profissionalDAO.save(p1);
			
			Profissional p2 = new Profissional();
			p2.setCPF("12346623487");
			p2.setEmail("maria@gmail.com");
			p2.setSenha(encoder.encode("senha123"));
			p2.setRole("ROLE_PROFISSIONAL");
			p2.setNome("Maria");
			p2.setTelefone("1123451344");
			p2.setSexo("fem");
			p2.setNascimento("02/03/2020");
			profissionalDAO.save(p2);
			
			Profissional p3 = new Profissional();
			p3.setCPF("13788832408");
			p3.setEmail("joana@gmail.com");
			p3.setRole("ROLE_PROFISSIONAL");
			p3.setNome("Joana");
			p3.setSenha(encoder.encode("senha123"));
			p3.setTelefone("3523451354");
			p3.setSexo("fem");
			p3.setNascimento("02/04/2020");
			profissionalDAO.save(p3);			
		};
	}
}
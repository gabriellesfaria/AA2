package br.ufscar.dc.dsw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;
 
public class ProfissionalDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private ProfissionalDAO dao;
     
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Profissional profissional = dao.getProfissionalByEmail(email);
         
        if (profissional == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new ProfissionalDetails(profissional);
    }
}
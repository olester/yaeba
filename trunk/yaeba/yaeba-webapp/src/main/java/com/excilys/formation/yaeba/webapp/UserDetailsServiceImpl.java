package com.excilys.formation.yaeba.webapp;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.formation.yaeba.model.Role;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.UtilisateurService;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UtilisateurService utilisateurService;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException, DataAccessException {
		Utilisateur utilisateur = utilisateurService.getUtilisateurByLogin(login);
		if (utilisateur == null) throw new UsernameNotFoundException("Utilisateur introuvable");
		return buildUserFromUtilisateur(utilisateur);
	}

	private User buildUserFromUtilisateur(Utilisateur utilisateur) {

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : utilisateur.getRoles()) {
			authorities.add(new GrantedAuthorityImpl(role.getType()));
		}

		CustomUser cu = new CustomUser(utilisateur, authorities);
		return cu;
	}

}

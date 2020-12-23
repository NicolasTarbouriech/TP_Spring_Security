package fr.stazi.epsi.spring.boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import fr.stazi.epsi.spring.boot.entity.user.User;
import fr.stazi.epsi.spring.boot.repository.UserRepository;

@Service
public class SecurityMethodService {

	@Autowired
	private UserRepository userRepository;

	public boolean canManage(Long cellId, UserDetails connectedUser) {

		// Cell cell = cellRepository.findById(cellId).orElse(null);

		User user = userRepository.findByUsername(connectedUser.getUsername()).get();

		// return cell != null && user.getCells().contains(cell);

		return user.getCells().stream().anyMatch(cell -> cell.getId().equals(cellId));

	}
}

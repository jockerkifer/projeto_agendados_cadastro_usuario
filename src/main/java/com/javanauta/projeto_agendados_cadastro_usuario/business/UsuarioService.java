package com.javanauta.projeto_agendados_cadastro_usuario.business;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import com.javanauta.projeto_agendados_cadastro_usuario.business.Converter.UsuarioConverter;
import com.javanauta.projeto_agendados_cadastro_usuario.business.DTO.UsuarioDTO;
import com.javanauta.projeto_agendados_cadastro_usuario.infrastructure.entity.Usuario;
import com.javanauta.projeto_agendados_cadastro_usuario.infrastructure.exception.ConflictException;
import com.javanauta.projeto_agendados_cadastro_usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	private final UsuarioConverter usuarioConverter;
	
	
	public UsuarioDTO salvarDados(UsuarioDTO usuarioDTO) {
		emailEcontrado(usuarioDTO.getEmail());
		Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
		usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
		return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
	}
	
	public void emailEcontrado(String email) {
		try {
			boolean existe = buscarEmail(email);
			if(existe) {
				throw new ConflictException("Email já encontrado!!! "+email);
			}
			
		}catch(ConflictException e ) {
			throw new ConflictException("Email já encontrado!!! "+e.getCause());
			
		}
	}
	
	public boolean buscarEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}
	
	public Usuario listarUsuarioEmail(String email) {
		return usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceAccessException("Email Encontrado!!! "+email));
	}
	
	public void deletarUsuario(String email) {
		usuarioRepository.deleteByEmail(email);
	}
		
	

}

package com.javanauta.projeto_agendados_cadastro_usuario.business;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import com.javanauta.projeto_agendados_cadastro_usuario.business.Converter.UsuarioConverter;
import com.javanauta.projeto_agendados_cadastro_usuario.business.DTO.UsuarioDTO;
import com.javanauta.projeto_agendados_cadastro_usuario.infrastructure.entity.Usuario;
import com.javanauta.projeto_agendados_cadastro_usuario.infrastructure.exception.ConflictException;
import com.javanauta.projeto_agendados_cadastro_usuario.infrastructure.exception.ResourceNotFoundException;
import com.javanauta.projeto_agendados_cadastro_usuario.infrastructure.repository.UsuarioRepository;
import com.javanauta.projeto_agendados_cadastro_usuario.infrastructure.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	private final UsuarioConverter usuarioConverter;
	private final JwtUtil jwtUtil;
	
	
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
	
	public UsuarioDTO atualizaDadosUsuario(String token ,UsuarioDTO usuarioDTO) {
//		Buscamos o email do usuário através do token (tirar a obrigatoriedade do email)
		String email = jwtUtil.extractUsername(token.substring(7));
//		Criptografia de senha
		usuarioDTO.setSenha(usuarioDTO.getSenha() != null ? passwordEncoder.encode(usuarioDTO.getSenha()) : null);
//		Buscamos os dados do usuário no banco de dados.
		Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email já Encontrado!!!"));
//		Mesclamos os dados que recebemos na requisição DTO com os dados do banco de dados
		usuario = usuarioConverter.atualizaDadosUsuario(usuarioDTO, usuario);
//		Salvou os dados do usuário convertido e depois pegou o retorno e converteu para usuarioDTO
		return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
	}
		
	

}

package com.javanauta.projeto_agendados_cadastro_usuario.business.Converter;

import java.util.List;

import org.springframework.stereotype.Component;
import com.javanauta.projeto_agendados_cadastro_usuario.business.DTO.EnderecoDTO;
import com.javanauta.projeto_agendados_cadastro_usuario.business.DTO.TelefoneDTO;
import com.javanauta.projeto_agendados_cadastro_usuario.business.DTO.UsuarioDTO;
import com.javanauta.projeto_agendados_cadastro_usuario.infrastructure.entity.Endereco;
import com.javanauta.projeto_agendados_cadastro_usuario.infrastructure.entity.Telefone;
import com.javanauta.projeto_agendados_cadastro_usuario.infrastructure.entity.Usuario;

@Component
public class UsuarioConverter {
	
	public Usuario paraUsuario(UsuarioDTO usuarioDTO) {
		return Usuario.builder()
		.nome(usuarioDTO.getNome())
		.email(usuarioDTO.getEmail())
		.senha(usuarioDTO.getSenha())
		.enderecos(paraListarEndereco(usuarioDTO.getEnderecos()))
		.telefones(paraListarTelefone(usuarioDTO.getTelefones()))
		.build();
	}
	
	public List<Endereco> paraListarEndereco(List<EnderecoDTO> enderecoDTO){
		return enderecoDTO.stream().map(this::paraEndereco).toList();
		
	}
	
	public Endereco paraEndereco(EnderecoDTO enderecoDTO) {
		return Endereco.builder()
		.rua(enderecoDTO.getRua())
		.cep(enderecoDTO.getCep())
		.estado(enderecoDTO.getEstado())
		.cidade(enderecoDTO.getCidade())
		.build();
	}
	
	public List<Telefone> paraListarTelefone(List<TelefoneDTO> telefoneDTO){
		return telefoneDTO.stream().map(this::paraTelefone).toList();
	}
	
	public Telefone paraTelefone(TelefoneDTO telefoneDTO) {
		return Telefone.builder()
				.ddd(telefoneDTO.getDdd())
				.numero(telefoneDTO.getNumero())
				.build();
	}
	
//	Transformar DTO para Entity
	
	public UsuarioDTO paraUsuarioDTO(Usuario usuario) {
		return UsuarioDTO.builder()
		.nome(usuario.getNome())
		.email(usuario.getEmail())
		.senha(usuario.getSenha())
		.enderecos(paraListarEnderecoDTO(usuario.getEnderecos()))
		.telefones(paraListarTelefoneDTO(usuario.getTelefones()))
		.build();
	}
	
	public List<EnderecoDTO> paraListarEnderecoDTO(List<Endereco> endereco){
		return endereco.stream().map(this::paraEnderecoDTO).toList();
		
	}
	
	public EnderecoDTO paraEnderecoDTO(Endereco endereco) {
		return EnderecoDTO.builder()
		.rua(endereco.getRua())
		.cep(endereco.getCep())
		.estado(endereco.getEstado())
		.cidade(endereco.getCidade())
		.build();
	}
	
	public List<TelefoneDTO> paraListarTelefoneDTO(List<Telefone> telefone){
		return telefone.stream().map(this::paraTelefoneDTO).toList();
	}
	
	public TelefoneDTO paraTelefoneDTO(Telefone telefone) {
		return TelefoneDTO.builder()
				.ddd(telefone.getDdd())
				.numero(telefone.getNumero())
				.build();
	}
}

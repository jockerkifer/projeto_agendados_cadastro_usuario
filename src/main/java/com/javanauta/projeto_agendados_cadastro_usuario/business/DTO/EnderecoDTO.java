package com.javanauta.projeto_agendados_cadastro_usuario.business.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTO {
	
	private String rua;
	private String cep;
	private String estado;
	private String cidade;

}

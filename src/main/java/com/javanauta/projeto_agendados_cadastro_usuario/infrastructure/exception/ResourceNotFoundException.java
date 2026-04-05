package com.javanauta.projeto_agendados_cadastro_usuario.infrastructure.exception;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public ResourceNotFoundException(String mensagem,Throwable throwable) {
		super(mensagem,throwable);
	}

}

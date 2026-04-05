package com.javanauta.projeto_agendados_cadastro_usuario.infrastructure.exception;

@SuppressWarnings("serial")
public class ConflictException extends RuntimeException {
	
	public ConflictException(String mensagem) {
		super(mensagem);
	}
	
	public ConflictException(String mensagem,Throwable throwable) {
		super(mensagem,throwable);
	}

}

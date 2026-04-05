package com.javanauta.projeto_agendados_cadastro_usuario.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.javanauta.projeto_agendados_cadastro_usuario.infrastructure.entity.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}

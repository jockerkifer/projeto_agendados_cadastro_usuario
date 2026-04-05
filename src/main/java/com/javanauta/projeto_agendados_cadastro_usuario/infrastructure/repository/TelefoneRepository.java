package com.javanauta.projeto_agendados_cadastro_usuario.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.javanauta.projeto_agendados_cadastro_usuario.infrastructure.entity.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}

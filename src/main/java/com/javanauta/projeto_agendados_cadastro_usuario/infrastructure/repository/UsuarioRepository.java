package com.javanauta.projeto_agendados_cadastro_usuario.infrastructure.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.javanauta.projeto_agendados_cadastro_usuario.infrastructure.entity.Usuario;
import jakarta.transaction.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	boolean existsByEmail(String email);
	Optional<Usuario> findByEmail(String email);
	@Transactional
	void deleteByEmail(String email);

}

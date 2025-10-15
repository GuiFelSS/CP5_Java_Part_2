package br.com.fiap.cp5_Brinquedos_esportivos.repository;

import br.com.fiap.cp5_Brinquedos_esportivos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
}

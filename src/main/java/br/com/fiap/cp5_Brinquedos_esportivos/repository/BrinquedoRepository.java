package br.com.fiap.cp5_Brinquedos_esportivos.repository;

import br.com.fiap.cp5_Brinquedos_esportivos.model.Brinquedo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrinquedoRepository extends JpaRepository<Brinquedo, Long> {
}

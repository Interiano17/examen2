package unah.lenguajes.examen2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unah.lenguajes.examen2.modelos.Prestamo;

@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo, Long> {
    
}

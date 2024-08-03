package unah.lenguajes.examen2.modelos;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="cliente")
@Data
public class Cliente {
    
    @Id
    @Column(name="dni")
    private String dni;

    private String nombre;

    private String apellido;

    private String telefono;

    @OneToMany(mappedBy = "prestamoCliente", cascade = CascadeType.ALL)
    private List<Prestamo> prestamosCliente;
}

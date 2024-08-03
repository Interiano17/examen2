package unah.lenguajes.examen2.modelos;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="prestamos")
@Data
public class Prestamo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigoPrestamo")
    private long codigoPrestamo;

    @Column(name="fechaApertura")
    private LocalDate fechaApertura;

    private double monto;

    private double cuota;

    private long plazo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="dni")
    private Cliente prestamoCliente;

    @OneToMany(mappedBy = "cuotaPrestamo", cascade = CascadeType.ALL)
    private List<Cuota> cuotasPrestamo;
}

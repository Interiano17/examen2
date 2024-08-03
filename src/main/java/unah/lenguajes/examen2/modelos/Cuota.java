package unah.lenguajes.examen2.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="cuotas")
@Data
public class Cuota {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigoCuota;

    private long mes;

    //private long codigoPrestamo;

    private double interes;
    
    private double capital;

    private double saldo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="codigoPrestamo")
    private Prestamo cuotaPrestamo;
}

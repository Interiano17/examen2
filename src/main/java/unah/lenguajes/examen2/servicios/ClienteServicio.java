package unah.lenguajes.examen2.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unah.lenguajes.examen2.modelos.Cliente;
import unah.lenguajes.examen2.modelos.Cuota;
import unah.lenguajes.examen2.modelos.Prestamo;
import unah.lenguajes.examen2.repositorios.ClienteRepositorio;
import unah.lenguajes.examen2.repositorios.CuotaRepositorio;
import unah.lenguajes.examen2.repositorios.PrestamoRepositorio;

@Service
public class ClienteServicio {
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private CuotaRepositorio cuotaRepositorio;

    @Autowired
    private PrestamoRepositorio prestamoRepositorio;

    public Cliente crearCliente(Cliente nuevoCliente){
        // Revisar existencia de cliente
        if(!this.clienteRepositorio.existsById(nuevoCliente.getDni())){
            // Asignarles el cliente padre a cada uno de los prestamos del JSON
            
            if(nuevoCliente.getPrestamosCliente() != null){
                List<Prestamo> prestamos = nuevoCliente.getPrestamosCliente();
                for(Prestamo prestamo : prestamos){
                    prestamo.setPrestamoCliente(nuevoCliente);
                    //Calcular cuota
                    long n = prestamo.getPlazo() * 12;
                    double numeradorCuota = prestamo.getMonto() * (0.09/12) * (Math.pow((1+(0.09/12)), n));
                    double denominadorCuota = (Math.pow((1+(0.09/12)), n)) - 1;

                    double cuotaCalculada = numeradorCuota/denominadorCuota;

                    prestamo.setCuota(cuotaCalculada);

                    List<Cuota> cuotas = new ArrayList<>();
                    long periodo = prestamo.getPlazo() * 12;
                    for(int i = 0; i < (int)periodo; i++){
                        
                        if(i == 0){
                            Cuota nuevaCuota = new Cuota();
                            nuevaCuota.setCuotaPrestamo(prestamo);
                            nuevaCuota.setMes(0);
                            nuevaCuota.setCapital(0);
                            nuevaCuota.setInteres(0);
                            nuevaCuota.setSaldo(prestamo.getMonto());
                            
                            cuotas.add(nuevaCuota);
                            
                        }else{
                            Cuota nuevaCuota2 = new Cuota();
                            nuevaCuota2.setCuotaPrestamo(prestamo);
                            nuevaCuota2.setMes(i);
                            nuevaCuota2.setInteres(cuotas.get(i-1).getSaldo() * (0.09/12));
                            nuevaCuota2.setCapital(cuotaCalculada - (cuotas.get(i-1).getSaldo() * (0.09/12)));
                            nuevaCuota2.setSaldo(cuotas.get(i-1).getSaldo() - (cuotaCalculada - (cuotas.get(i-1).getSaldo() * (0.09/12))));
                            
                            cuotas.add(nuevaCuota2);
                            
                        }
                    }
                    prestamo.setCuotasPrestamo(cuotas);
                }
                
            }
            return this.clienteRepositorio.save(nuevoCliente);
        }
        return null;
    }

    public List<Cliente> obtenerClientes(){
        return this.clienteRepositorio.findAll();
    }

    public Prestamo buscarPrestamo(long codigoPrestamo){
        return this.prestamoRepositorio.findById(codigoPrestamo);
    }
}

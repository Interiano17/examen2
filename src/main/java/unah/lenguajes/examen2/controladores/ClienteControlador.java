package unah.lenguajes.examen2.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unah.lenguajes.examen2.modelos.Cliente;
import unah.lenguajes.examen2.modelos.Prestamo;
import unah.lenguajes.examen2.servicios.ClienteServicio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/cliente")
public class ClienteControlador {
    
    @Autowired
    private ClienteServicio clienteServicio;

    @PostMapping("/crear")
    public Cliente crearCliente(@RequestBody Cliente nuevoCliente) {
        return this.clienteServicio.crearCliente(nuevoCliente);
    }

    @GetMapping("/obtener/todos")
    public List<Cliente> obtenerClientes() {
        return this.clienteServicio.obtenerClientes();
    }

    @GetMapping("/obtener/{codigoPrestamo}")
    public Prestamo buscarPrestamo(@PathVariable(name="codigoPrestamo") long codigoPrestamo) {
        return this.clienteServicio.buscarPrestamo(codigoPrestamo);
    }
    
    
}

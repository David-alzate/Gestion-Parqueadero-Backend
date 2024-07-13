package co.com.park.gp.controller.clientes;

import co.com.park.gp.business.facade.impl.clientes.cliente.ConsultarClienteFacade;
import co.com.park.gp.business.facade.impl.clientes.cliente.EliminarClienteFacade;
import co.com.park.gp.business.facade.impl.clientes.cliente.ModificarClienteFacade;
import co.com.park.gp.business.facade.impl.clientes.cliente.RegistrarClienteFacade;
import co.com.park.gp.controller.response.clientes.ClienteResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.dto.clientes.ClienteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/clientes/")
public class ClienteController {

    @GetMapping
    public ResponseEntity<ClienteResponse> consultar() {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var clienteResponse = new ClienteResponse();

        try {
            var clienteDto = ClienteDTO.build();
            var facade = new ConsultarClienteFacade();

            clienteResponse.setDatos(facade.execute(clienteDto));
            clienteResponse.getMensajes().add("Cliente Consultado exitosamente");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            clienteResponse.getMensajes().add(excepcion.getMensajeUsuario());

        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "se ha presentado un problema tratando de consultar el cliente";
            clienteResponse.getMensajes().add(mensajeUsuario);
        }

        return new ResponseEntity<>(clienteResponse, httpStatusCode);
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> crear(@RequestBody ClienteDTO cliente) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var clienteResponse = new ClienteResponse();

        try {
            var facade = new RegistrarClienteFacade();
            facade.execute(cliente);
            clienteResponse.getMensajes().add("Cliente creado exitosamente");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            clienteResponse.getMensajes().add(excepcion.getMensajeUsuario());
        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "se ha presentado un problema tratando de registar el nuevo cliente";
            clienteResponse.getMensajes().add(mensajeUsuario);
        }

        return new ResponseEntity<>(clienteResponse, httpStatusCode);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> modificar(@PathVariable UUID id, @RequestBody ClienteDTO clienteDTO) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var clienteResponse = new ClienteResponse();

        try {
            clienteDTO.setId(id);
            var facade = new ModificarClienteFacade();

            facade.execute(clienteDTO);
            clienteResponse.getMensajes().add("Cliente modificado existosamente ");
        } catch (final GPException exception) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            clienteResponse.getMensajes().add(exception.getMensajeUsuario());
        } catch (final Exception exception) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la informacion del Cliente";
            clienteResponse.getMensajes().add(mensajeUsuario);
        }
        return new ResponseEntity<>(clienteResponse, httpStatusCode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResponse> eliminar(@PathVariable UUID id) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var clienteResponse = new ClienteResponse();

        try {
            var facade = new EliminarClienteFacade();
            facade.execute(id);
            clienteResponse.getMensajes().add("Cliente eliminado existosamente ");
        } catch (final GPException exception) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            clienteResponse.getMensajes().add(exception.getMensajeUsuario());
        } catch (final Exception exception) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la informacion del Cliente";
            clienteResponse.getMensajes().add(mensajeUsuario);

        }
        return new ResponseEntity<>(clienteResponse, httpStatusCode);
    }
}

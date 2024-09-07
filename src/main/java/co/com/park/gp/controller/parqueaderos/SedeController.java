package co.com.park.gp.controller.parqueaderos;

import co.com.park.gp.business.facade.impl.parqueaderos.sede.EliminarSedeFacade;
import co.com.park.gp.business.facade.impl.parqueaderos.sede.ModificarSedeFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.com.park.gp.business.facade.impl.parqueaderos.sede.ConsultarSedeFacade;
import co.com.park.gp.business.facade.impl.parqueaderos.sede.RegistrarSedeFacade;
import co.com.park.gp.controller.response.parqueaderos.SedeResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.dto.parqueaderos.SedeDTO;

import java.util.UUID;

@RestController
@RequestMapping("/sedes/")
public final class SedeController {

    @GetMapping
    public ResponseEntity<SedeResponse> consultar() {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var sedeResponse = new SedeResponse();

        try {
            var sedeDto = SedeDTO.build();
            var facade = new ConsultarSedeFacade();

            sedeResponse.setDatos(facade.execute(sedeDto));
            sedeResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00043));

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            sedeResponse.getMensajes().add(excepcion.getMensajeUsuario());

        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00031);
            sedeResponse.getMensajes().add(mensajeUsuario);
        }

        return new ResponseEntity<>(sedeResponse, httpStatusCode);
    }

    @PostMapping
    public ResponseEntity<SedeResponse> crear(@RequestBody SedeDTO sede) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var sedeResponse = new SedeResponse();

        try {
            var facade = new RegistrarSedeFacade();
            facade.execute(sede);
            sedeResponse.getMensajes().add("Sede creada exitosamente");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            sedeResponse.getMensajes().add(excepcion.getMensajeUsuario());
        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "se ha presentado un prblema tratando de registar la nueva Sede";
            sedeResponse.getMensajes().add(mensajeUsuario);
        }

        return new ResponseEntity<>(sedeResponse, httpStatusCode);

    }

    @PutMapping("/{id}")
    public ResponseEntity<SedeResponse> modificar(@PathVariable UUID id, @RequestBody SedeDTO sedeDTO) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var sedeResponse = new SedeResponse();

        try {
            sedeDTO.setId(id);
            var facade = new ModificarSedeFacade();

            facade.execute(sedeDTO);
            sedeResponse.getMensajes().add("Sede modificada existosamente ");
        } catch (final GPException exception) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            sedeResponse.getMensajes().add(exception.getMensajeUsuario());
        } catch (final Exception exception) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la informacion de la sede";
            sedeResponse.getMensajes().add(mensajeUsuario);
        }
        return new ResponseEntity<>(sedeResponse, httpStatusCode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SedeResponse> eliminar(@PathVariable UUID id) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var sedeResponse = new SedeResponse();

        try {
            var facade = new EliminarSedeFacade();
            facade.execute(id);
            sedeResponse.getMensajes().add("Sede eliminada existosamente ");
        } catch (final GPException exception) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            sedeResponse.getMensajes().add(exception.getMensajeUsuario());
        } catch (final Exception exception) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la informacion de la sede";
            sedeResponse.getMensajes().add(mensajeUsuario);

        }
        return new ResponseEntity<>(sedeResponse, httpStatusCode);
    }

}

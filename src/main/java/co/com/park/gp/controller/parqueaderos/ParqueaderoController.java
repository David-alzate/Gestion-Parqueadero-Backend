package co.com.park.gp.controller.parqueaderos;

import co.com.park.gp.business.facade.impl.parqueaderos.parqueadero.EliminarParqueaderoFacade;
import co.com.park.gp.business.facade.impl.parqueaderos.parqueadero.ModificarParqueaderoFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.com.park.gp.business.facade.impl.parqueaderos.parqueadero.ConsultarParqueaderosFacade;
import co.com.park.gp.business.facade.impl.parqueaderos.parqueadero.RegistrarParqueaderoFacade;
import co.com.park.gp.controller.response.parqueaderos.ParqueaderoResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.dto.parqueaderos.ParqueaderoDTO;

import java.util.UUID;

@RestController
@RequestMapping("/parqueaderos/")
public final class ParqueaderoController {

    @GetMapping
    public ResponseEntity<ParqueaderoResponse> consultar() {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var parqueaderoResponse = new ParqueaderoResponse();

        try {
            var parqueaderoDto = ParqueaderoDTO.build();
            var facade = new ConsultarParqueaderosFacade();

            parqueaderoResponse.setDatos(facade.execute(parqueaderoDto));
            parqueaderoResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00042));

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            parqueaderoResponse.getMensajes().add(excepcion.getMensajeUsuario());

        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00029);
            parqueaderoResponse.getMensajes().add(mensajeUsuario);

        }

        return new ResponseEntity<>(parqueaderoResponse, httpStatusCode);
    }

    @PostMapping
    public ResponseEntity<ParqueaderoResponse> crear(@RequestBody ParqueaderoDTO parqueadero) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var parqueaderoResponse = new ParqueaderoResponse();

        try {
            var facade = new RegistrarParqueaderoFacade();
            facade.execute(parqueadero);
            parqueaderoResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00073));

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            parqueaderoResponse.getMensajes().add(excepcion.getMensajeUsuario());

        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00074);
            parqueaderoResponse.getMensajes().add(mensajeUsuario);

        }

        return new ResponseEntity<>(parqueaderoResponse, httpStatusCode);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ParqueaderoResponse> eliminar(@PathVariable UUID id) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var parqueaderoResponse = new ParqueaderoResponse();

        try {
            var facade = new EliminarParqueaderoFacade();
            facade.execute(id);
            parqueaderoResponse.getMensajes().add("Parqueadero eliminada existosamente ");
        } catch (final GPException exception) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            parqueaderoResponse.getMensajes().add(exception.getMensajeUsuario());
        } catch (final Exception exception) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la informacion del Parqueadero";
            parqueaderoResponse.getMensajes().add(mensajeUsuario);

        }
        return new ResponseEntity<>(parqueaderoResponse, httpStatusCode);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParqueaderoResponse> modificar(@PathVariable UUID id, @RequestBody ParqueaderoDTO parqueaderoDTO) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var parqueaderoResponse = new ParqueaderoResponse();

        try {
            parqueaderoDTO.setId(id);
            var facade = new ModificarParqueaderoFacade();

            facade.execute(parqueaderoDTO);
            parqueaderoResponse.getMensajes().add("Parqueadero modificado existosamente ");
        } catch (final GPException exception) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            parqueaderoResponse.getMensajes().add(exception.getMensajeUsuario());
        } catch (final Exception exception) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la informacion del parqueadero";
            parqueaderoResponse.getMensajes().add(mensajeUsuario);
        }
        return new ResponseEntity<>(parqueaderoResponse, httpStatusCode);
    }
}

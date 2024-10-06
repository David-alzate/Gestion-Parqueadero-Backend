package co.com.park.gp.controller.parqueaderos;

import co.com.park.gp.business.facade.impl.parqueaderos.celdas.ConsultarCeldaFacade;
import co.com.park.gp.business.facade.impl.parqueaderos.celdas.ConsultarCeldasDisponiblesFacade;
import co.com.park.gp.business.facade.impl.parqueaderos.celdas.EliminarCeldaFacade;
import co.com.park.gp.business.facade.impl.parqueaderos.celdas.ModificarCeldaFacade;
import co.com.park.gp.business.facade.impl.parqueaderos.celdas.RegistrarCeldaFacade;
import co.com.park.gp.controller.response.parqueaderos.CeldaResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.dto.parqueaderos.CeldaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/celdas/")
public class CeldaController {

    @GetMapping
    public ResponseEntity<CeldaResponse> consultar() {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var celdaResponse = new CeldaResponse();

        try {
            var celdaDto = CeldaDTO.build();
            var facade = new ConsultarCeldaFacade();

            celdaResponse.setDatos(facade.execute(celdaDto));
            celdaResponse.getMensajes().add("Celdas Consultadas Exitosamente");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            celdaResponse.getMensajes().add(excepcion.getMensajeUsuario());

        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Error al consultar las celdas";
            celdaResponse.getMensajes().add(mensajeUsuario);

        }

        return new ResponseEntity<>(celdaResponse, httpStatusCode);
    }
    
    @GetMapping("/Disponibles")
    public ResponseEntity<CeldaResponse> consultarCeldasDisponibles() {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var celdaResponse = new CeldaResponse();

        try {
            var celdaDto = CeldaDTO.build();
            
            var facade = new ConsultarCeldasDisponiblesFacade();
            var celdasDisponibles = facade.execute(celdaDto);

            celdaResponse.setDatos(celdasDisponibles);
            celdaResponse.getMensajes().add("Celdas Disponibles Consultadas Exitosamente");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            celdaResponse.getMensajes().add(excepcion.getMensajeUsuario());

        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Error al consultar las celdas";
            celdaResponse.getMensajes().add(mensajeUsuario);
        }

        return new ResponseEntity<>(celdaResponse, httpStatusCode);
    }


    @PostMapping
    public ResponseEntity<CeldaResponse> crear(@RequestBody CeldaDTO celda) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var celdaResponse = new CeldaResponse();

        try {
            var facade = new RegistrarCeldaFacade();
            facade.execute(celda);
            celdaResponse.getMensajes().add("Creacion de celda exitosa");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            celdaResponse.getMensajes().add(excepcion.getMensajeUsuario());
        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Se ha presentado un error registrando la celda";
            celdaResponse.getMensajes().add(mensajeUsuario);
        }

        return new ResponseEntity<>(celdaResponse, httpStatusCode);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CeldaResponse> eliminar(@PathVariable UUID id) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var celdaResponse = new CeldaResponse();

        try {
            var facade = new EliminarCeldaFacade();
            facade.execute(id);
            celdaResponse.getMensajes().add("Celda eliminada existosamente ");
        } catch (final GPException exception) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            celdaResponse.getMensajes().add(exception.getMensajeUsuario());
        } catch (final Exception exception) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la informacion de la celda";
            celdaResponse.getMensajes().add(mensajeUsuario);

        }
        return new ResponseEntity<>(celdaResponse, httpStatusCode);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CeldaResponse> modificar(@PathVariable UUID id, @RequestBody CeldaDTO celda) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var celdaResponse = new CeldaResponse();

        try {
            celda.setId(id);
            var facade = new ModificarCeldaFacade();

            facade.execute(celda);
            celdaResponse.getMensajes().add("Celda modificado existosamente ");
        } catch (final GPException exception) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            celdaResponse.getMensajes().add(exception.getMensajeUsuario());
        } catch (final Exception exception) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la informacion de la celda";
            celdaResponse.getMensajes().add(mensajeUsuario);
        }
        return new ResponseEntity<>(celdaResponse, httpStatusCode);
    }


}

package co.com.park.gp.controller.tarifas;

import co.com.park.gp.business.facade.impl.tarifas.tarifa.ConsultarTarifaFacade;
import co.com.park.gp.business.facade.impl.tarifas.tarifa.EliminarTarifaFacade;
import co.com.park.gp.business.facade.impl.tarifas.tarifa.ModificarTarifaFacade;
import co.com.park.gp.business.facade.impl.tarifas.tarifa.RegistrarTarifaFacade;
import co.com.park.gp.controller.response.tarifa.TarifaResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.dto.tarifas.TarifaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tarifas/")
public class TarifaController {

    @GetMapping("/dummy/")
    public TarifaDTO dummy() {
        return TarifaDTO.build();
    }

    @GetMapping
    public ResponseEntity<TarifaResponse> consultar() {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var tarifaResponse = new TarifaResponse();

        try {
            var tarifaDTO = TarifaDTO.build();
            var facade = new ConsultarTarifaFacade();

            tarifaResponse.setDatos(facade.execute(tarifaDTO));
            tarifaResponse.getMensajes().add("Tarifas consultadas exitosamente");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            tarifaResponse.getMensajes().add(excepcion.getMensajeUsuario());

        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Se ha presentado un problema al consultar la información de las tarifas";
            tarifaResponse.getMensajes().add(mensajeUsuario);
        }

        return new ResponseEntity<>(tarifaResponse, httpStatusCode);
    }

    @PostMapping
    public ResponseEntity<TarifaResponse> crear(@RequestBody TarifaDTO tarifa) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var tarifaResponse = new TarifaResponse();

        try {
            var facade = new RegistrarTarifaFacade();
            facade.execute(tarifa);
            tarifaResponse.getMensajes().add("Tarifa creada exitosamente");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            tarifaResponse.getMensajes().add(excepcion.getMensajeUsuario());

        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "se ha presentado un prblema tratando de registar la nueva Tarifa";
            tarifaResponse.getMensajes().add(mensajeUsuario);
        }

        return new ResponseEntity<>(tarifaResponse, httpStatusCode);

    }

    @PutMapping("/{id}")
    public ResponseEntity<TarifaResponse> modificar(@PathVariable UUID id, @RequestBody TarifaDTO tarifaDTO) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var tarifaResponse = new TarifaResponse();

        try {
            tarifaDTO.setId(id);
            var facade = new ModificarTarifaFacade();

            facade.execute(tarifaDTO);
            tarifaResponse.getMensajes().add("Tarifa modificada existosamente ");
        } catch (final GPException exception) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            tarifaResponse.getMensajes().add(exception.getMensajeUsuario());
        } catch (final Exception exception) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la informacion de la Tarifa";
            tarifaResponse.getMensajes().add(mensajeUsuario);
        }
        return new ResponseEntity<>(tarifaResponse, httpStatusCode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TarifaResponse> eliminar(@PathVariable UUID id) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var tarifaResponse = new TarifaResponse();

        try {
            var facade = new EliminarTarifaFacade();
            facade.execute(id);
            tarifaResponse.getMensajes().add("Tarifa eliminada existosamente ");
        } catch (final GPException exception) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            tarifaResponse.getMensajes().add(exception.getMensajeUsuario());
        } catch (final Exception exception) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la informacion de la Tarifa";
            tarifaResponse.getMensajes().add(mensajeUsuario);

        }
        return new ResponseEntity<>(tarifaResponse, httpStatusCode);
    }


}

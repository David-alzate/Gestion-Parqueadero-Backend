package co.com.park.gp.controller.tarifas;

import co.com.park.gp.business.facade.impl.tarifas.estado.ConsultarEstadoFacade;
import co.com.park.gp.controller.response.tarifa.EstadoResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.dto.tarifas.EstadoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estados/")
public class EstadoController {

    @GetMapping
    public ResponseEntity<EstadoResponse> consultar() {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var estadoResponse = new EstadoResponse();

        try {
            var estadoDto = EstadoDTO.build();
            var facade = new ConsultarEstadoFacade();

            estadoResponse.setDatos(facade.execute(estadoDto));
            estadoResponse.getMensajes().add("Estados consultadas exitosamente");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            estadoResponse.getMensajes().add(excepcion.getMensajeUsuario());

        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "se ha presentado un error tratando de consultar los Estados";
            estadoResponse.getMensajes().add(mensajeUsuario);

        }

        return new ResponseEntity<>(estadoResponse, httpStatusCode);
    }
}

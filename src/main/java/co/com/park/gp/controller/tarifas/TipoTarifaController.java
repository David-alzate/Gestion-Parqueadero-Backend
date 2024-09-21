package co.com.park.gp.controller.tarifas;

import co.com.park.gp.business.facade.impl.tarifas.tipotarifa.ConsultarTipoTarifaFacade;
import co.com.park.gp.controller.response.tarifa.TipoTarifaResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.dto.tarifas.TipoTarifaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipoTarifas/")
public class TipoTarifaController {

    @GetMapping
    public ResponseEntity<TipoTarifaResponse> consultar() {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var tipoTarifaResponse = new TipoTarifaResponse();

        try {
            var tipoTarifaDto = TipoTarifaDTO.build();
            var facade = new ConsultarTipoTarifaFacade();

            tipoTarifaResponse.setDatos(facade.execute(tipoTarifaDto));
            tipoTarifaResponse.getMensajes().add("Tipo Tarifas consultadas exitosamente");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            tipoTarifaResponse.getMensajes().add(excepcion.getMensajeUsuario());

        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "se ha presentado un error tratando de consultar los tipos de tarifa";
            tipoTarifaResponse.getMensajes().add(mensajeUsuario);

        }

        return new ResponseEntity<>(tipoTarifaResponse, httpStatusCode);
    }
}

package co.com.park.gp.controller.planes;

import co.com.park.gp.business.facade.impl.planes.tipoplan.ConsultarTipoPlanFacade;
import co.com.park.gp.controller.response.planes.TipoPlanResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.dto.planes.TipoPlanDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipoPlanes/")
public class TipoPlanController {

    @GetMapping
    public ResponseEntity<TipoPlanResponse> consultar(){

        var httpStatusCode = HttpStatus.ACCEPTED;
        var tipoPlanResponse = new TipoPlanResponse();

        try {
            var tipoPlanDto = TipoPlanDTO.build();
            var facade = new ConsultarTipoPlanFacade();

            tipoPlanResponse.setDatos(facade.execute(tipoPlanDto));
            tipoPlanResponse.getMensajes().add("tipo Planes consultadas exitosamente");

        }catch(final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            tipoPlanResponse.getMensajes().add(excepcion.getMensajeUsuario());

        }catch(final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la información de los tipos de sede.";
            tipoPlanResponse.getMensajes().add(mensajeUsuario);

        }

        return new ResponseEntity<>(tipoPlanResponse,httpStatusCode);
    }
}

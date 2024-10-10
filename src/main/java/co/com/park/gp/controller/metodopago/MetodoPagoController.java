package co.com.park.gp.controller.metodopago;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.park.gp.business.facade.impl.facturacion.metodopago.ConsultarMetodoPagoFacade;
import co.com.park.gp.controller.response.metodopago.MetodoPagoResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.dto.facturacion.MetodoPagoDTO;

@RestController
@RequestMapping("/metodoPago/")
public class MetodoPagoController {
	
    @GetMapping
    public ResponseEntity<MetodoPagoResponse> consultar() {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var metodoPagoResponse = new MetodoPagoResponse();

        try {
            var metodoPagoDto = MetodoPagoDTO.build();
            var facade = new ConsultarMetodoPagoFacade();

            metodoPagoResponse.setDatos(facade.execute(metodoPagoDto));
            metodoPagoResponse.getMensajes().add("Metodos de Pago consultados Exitosamente");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            metodoPagoResponse.getMensajes().add(excepcion.getMensajeUsuario());

        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Error al consultar las celdas";
            metodoPagoResponse.getMensajes().add(mensajeUsuario);

        }

        return new ResponseEntity<>(metodoPagoResponse, httpStatusCode);
    }

}

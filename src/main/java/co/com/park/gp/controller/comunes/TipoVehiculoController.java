package co.com.park.gp.controller.comunes;

import co.com.park.gp.business.facade.impl.comunes.tipovehiculo.ConsutarTipoVehiculoFacade;
import co.com.park.gp.controller.response.comunes.TipoVehiculoResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.dto.comunes.TipoVehiculoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipoVehiculos/")
public class TipoVehiculoController {

    @GetMapping
    public ResponseEntity<TipoVehiculoResponse> consultar() {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var tipoVehiculoResponse = new TipoVehiculoResponse();

        try {
            var tipoVehiculoDto = TipoVehiculoDTO.build();
            var facade = new ConsutarTipoVehiculoFacade();

            tipoVehiculoResponse.setDatos(facade.execute(tipoVehiculoDto));
            tipoVehiculoResponse.getMensajes().add("Tipo Vehiculos consultadas exitosamente");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            tipoVehiculoResponse.getMensajes().add(excepcion.getMensajeUsuario());

        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "se ha presentado un error tratando de consultar los tipos de Vehiculos";
            tipoVehiculoResponse.getMensajes().add(mensajeUsuario);

        }

        return new ResponseEntity<>(tipoVehiculoResponse, httpStatusCode);
    }
}

package co.com.park.gp.controller.sesionesparqueo;

import co.com.park.gp.business.facade.impl.sesionesparqueo.ConsultarSesionParqueoFacade;
import co.com.park.gp.business.facade.impl.sesionesparqueo.IngresarVehiculoFacade;
import co.com.park.gp.business.facade.impl.sesionesparqueo.SalidaVehiculoFacade;
import co.com.park.gp.controller.response.sesionesparqueo.SesionParqueoResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.dto.sesionesparqueo.SesionParqueoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sesionesParqueo/")
public class SesionParqueoController {

    @GetMapping
    public ResponseEntity<SesionParqueoResponse> consultar() {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var sesionParqueoResponse = new SesionParqueoResponse();

        try {
            var sesionParqueoDto = SesionParqueoDTO.build();
            var facade = new ConsultarSesionParqueoFacade();

            sesionParqueoResponse.setDatos(facade.execute(sesionParqueoDto));
            sesionParqueoResponse.getMensajes().add("Sesiones de parqueo Consultadas exitosamente");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            sesionParqueoResponse.getMensajes().add(excepcion.getMensajeUsuario());

        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "se ha presentado un problema tratando de consultar las sesiones de parqueo";
            sesionParqueoResponse.getMensajes().add(mensajeUsuario);
        }

        return new ResponseEntity<>(sesionParqueoResponse, httpStatusCode);
    }

    @PostMapping("/ingresoVehiculo")
    public ResponseEntity<SesionParqueoResponse> ingresarVehiculo(@RequestBody SesionParqueoDTO sesion) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var sesionParqueoResponse = new SesionParqueoResponse();

        try {
            var facade = new IngresarVehiculoFacade();
            facade.execute(sesion);
            sesionParqueoResponse.getMensajes().add("ingreso de vehiculo exitoso");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            sesionParqueoResponse.getMensajes().add(excepcion.getMensajeUsuario());
        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "se ha presentado un problema tratando de ingresar el vehiculo";
            sesionParqueoResponse.getMensajes().add(mensajeUsuario);
        }

        return new ResponseEntity<>(sesionParqueoResponse, httpStatusCode);
    }

    @PostMapping("/salidaVehiculo")
    public ResponseEntity<SesionParqueoResponse> salidaVehiculo(@RequestBody SesionParqueoDTO sesion) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var sesionParqueoResponse = new SesionParqueoResponse();

        try {
            var facade = new SalidaVehiculoFacade();
            facade.execute(sesion);
            sesionParqueoResponse.getMensajes().add("Salida de vehiculo exitosa");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            sesionParqueoResponse.getMensajes().add(excepcion.getMensajeUsuario());
        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "se ha presentado un problema tratando de realizar la salida del vehiculo";
            sesionParqueoResponse.getMensajes().add(mensajeUsuario);
        }

        return new ResponseEntity<>(sesionParqueoResponse, httpStatusCode);
    }
}

package co.com.park.gp.controller.vehiculos;

import co.com.park.gp.business.facade.impl.vehiculos.vehiculo.ConsultarVehiculoFacade;
import co.com.park.gp.business.facade.impl.vehiculos.vehiculo.EliminarVehiculoFacade;
import co.com.park.gp.business.facade.impl.vehiculos.vehiculo.ModificarVehiculoFacade;
import co.com.park.gp.business.facade.impl.vehiculos.vehiculo.RegistrarVehiculoFacade;
import co.com.park.gp.controller.response.vehiculos.VehiculoResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.dto.vehiculos.VehiculoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/vehiculos/")
public class VehiculoController {

    @GetMapping
    public ResponseEntity<VehiculoResponse> consultar() {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var vehiculoResponse = new VehiculoResponse();

        try {
            var vehiculoDto = VehiculoDTO.build();
            var facade = new ConsultarVehiculoFacade();

            vehiculoResponse.setDatos(facade.execute(vehiculoDto));
            vehiculoResponse.getMensajes().add("Vehiculo Consultado exitosamente");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            vehiculoResponse.getMensajes().add(excepcion.getMensajeUsuario());

        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "se ha presentado un prblema tratando de Consultar el nuevo Vehiculo";
            vehiculoResponse.getMensajes().add(mensajeUsuario);
        }

        return new ResponseEntity<>(vehiculoResponse, httpStatusCode);
    }

    @PostMapping
    public ResponseEntity<VehiculoResponse> crear(@RequestBody VehiculoDTO vehiculo) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var vehiculoResponse = new VehiculoResponse();

        try {
            var facade = new RegistrarVehiculoFacade();
            facade.execute(vehiculo);
            vehiculoResponse.getMensajes().add("Vehiculo creado exitosamente");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            vehiculoResponse.getMensajes().add(excepcion.getMensajeUsuario());
        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "se ha presentado un prblema tratando de registar el nuevo vehiculo";
            vehiculoResponse.getMensajes().add(mensajeUsuario);
        }

        return new ResponseEntity<>(vehiculoResponse, httpStatusCode);

    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculoResponse> modificar(@PathVariable UUID id, @RequestBody VehiculoDTO vehiculoDTO) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var vehiculoResponse = new VehiculoResponse();

        try {
            vehiculoDTO.setId(id);
            var facade = new ModificarVehiculoFacade();

            facade.execute(vehiculoDTO);
            vehiculoResponse.getMensajes().add("Vehiculo modificado existosamente ");
        } catch (final GPException exception) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            vehiculoResponse.getMensajes().add(exception.getMensajeUsuario());
        } catch (final Exception exception) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la informacion del Vehiculo";
            vehiculoResponse.getMensajes().add(mensajeUsuario);
        }
        return new ResponseEntity<>(vehiculoResponse, httpStatusCode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VehiculoResponse> eliminar(@PathVariable UUID id) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var vehiculoResponse = new VehiculoResponse();

        try {
            var facade = new EliminarVehiculoFacade();
            facade.execute(id);
            vehiculoResponse.getMensajes().add("Vehiculo eliminado existosamente ");
        } catch (final GPException exception) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            vehiculoResponse.getMensajes().add(exception.getMensajeUsuario());
        } catch (final Exception exception) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la informacion del Vehiculo";
            vehiculoResponse.getMensajes().add(mensajeUsuario);

        }
        return new ResponseEntity<>(vehiculoResponse, httpStatusCode);
    }
}

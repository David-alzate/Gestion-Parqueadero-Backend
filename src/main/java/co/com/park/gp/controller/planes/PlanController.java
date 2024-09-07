package co.com.park.gp.controller.planes;

import co.com.park.gp.business.facade.impl.planes.plan.*;
import co.com.park.gp.controller.response.planes.PlanResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.dto.planes.PlanDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/planes/")
public class PlanController {

    @GetMapping("/dummy")
    public PlanDTO dummy(){
        return new PlanDTO().build();
    }

    @GetMapping
    public ResponseEntity<PlanResponse> consultar() {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var planResponse = new PlanResponse();

        try {
            var planDto = PlanDTO.build();
            var facade = new ConsultarPlanFacade();

            planResponse.setDatos(facade.execute(planDto));
            planResponse.getMensajes().add("Planes Consultado exitosamente");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            planResponse.getMensajes().add(excepcion.getMensajeUsuario());

        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "se ha presentado un problema tratando de consultar el Plan";
            planResponse.getMensajes().add(mensajeUsuario);
        }

        return new ResponseEntity<>(planResponse, httpStatusCode);
    }

    @GetMapping("/activos")
    public ResponseEntity<PlanResponse> consultarActivos() {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var planResponse = new PlanResponse();

        try {
            var planDto = PlanDTO.build();
            var facade = new ConsultarPlanActivoFacade();

            planResponse.setDatos(facade.execute(planDto));
            planResponse.getMensajes().add("Planes Activos Consultado exitosamente");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            planResponse.getMensajes().add(excepcion.getMensajeUsuario());

        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "se ha presentado un problema tratando de consultar los Planes Activos";
            planResponse.getMensajes().add(mensajeUsuario);
        }

        return new ResponseEntity<>(planResponse, httpStatusCode);
    }

    @PostMapping
    public ResponseEntity<PlanResponse> crear(@RequestBody PlanDTO plan) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var planResponse = new PlanResponse();

        try {
            var facade = new RegistrarPlanFacade();
            facade.execute(plan);
            planResponse.getMensajes().add("Plan creado exitosamente");

        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            planResponse.getMensajes().add(excepcion.getMensajeUsuario());
        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "se ha presentado un problema tratando de registar el nuevo Plan";
            planResponse.getMensajes().add(mensajeUsuario);
        }

        return new ResponseEntity<>(planResponse, httpStatusCode);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanResponse> modificar(@PathVariable UUID id, @RequestBody PlanDTO plan) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var planResponse = new PlanResponse();

        try {
            plan.setId(id);
            var facade = new ModificarPlanFacade();

            facade.execute(plan);
            planResponse.getMensajes().add("Plan modificado existosamente ");
        } catch (final GPException exception) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            planResponse.getMensajes().add(exception.getMensajeUsuario());
        } catch (final Exception exception) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la informacion del Plan";
            planResponse.getMensajes().add(mensajeUsuario);
        }
        return new ResponseEntity<>(planResponse, httpStatusCode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlanResponse> eliminar(@PathVariable UUID id) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var planResponse = new PlanResponse();

        try {
            var facade = new EliminarPlanFacade();
            facade.execute(id);
            planResponse.getMensajes().add("Plan eliminado existosamente ");
        } catch (final GPException exception) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            planResponse.getMensajes().add(exception.getMensajeUsuario());
        } catch (final Exception exception) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la informacion del Plan";
            planResponse.getMensajes().add(mensajeUsuario);

        }
        return new ResponseEntity<>(planResponse, httpStatusCode);
    }
}

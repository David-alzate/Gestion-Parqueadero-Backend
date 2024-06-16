package co.com.park.gp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.com.park.gp.business.facade.impl.empleado.ConsultarEmpleadoFacade;
import co.com.park.gp.business.facade.impl.empleado.RegistrarEmpleadoFacade;
import co.com.park.gp.controller.response.EmpleadoResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.dto.EmpleadoDTO;

@RestController
@RequestMapping("/empleados/")
public class EmpleadoController {

	@GetMapping
	public ResponseEntity<EmpleadoResponse> consultar() {

		var httpStatusCode = HttpStatus.ACCEPTED;
		var empleadoResponse = new EmpleadoResponse();

		try {
			var empleadoDto = EmpleadoDTO.build();
			var facade = new ConsultarEmpleadoFacade();

			empleadoResponse.setDatos(facade.execute(empleadoDto));
			empleadoResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00090));

		} catch (final GPException excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			empleadoResponse.getMensajes().add(excepcion.getMensajeUsuario());

		} catch (final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00085);
			empleadoResponse.getMensajes().add(mensajeUsuario);
		}

		return new ResponseEntity<>(empleadoResponse, httpStatusCode);
	}

	@PostMapping
	public ResponseEntity<EmpleadoResponse> crear(@RequestBody EmpleadoDTO empleado) {

		var httpStatusCode = HttpStatus.ACCEPTED;
		var empleadoResponse = new EmpleadoResponse();

		try {
			var facade = new RegistrarEmpleadoFacade();
			facade.execute(empleado);
			empleadoResponse.getMensajes().add("Empleado creado exitosamente");

		} catch (final GPException excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			empleadoResponse.getMensajes().add(excepcion.getMensajeUsuario());
		} catch (final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = "se ha presentado un prblema tratando de registar el nuevo empleado";
			empleadoResponse.getMensajes().add(mensajeUsuario);
		}

		return new ResponseEntity<>(empleadoResponse, httpStatusCode);

	}

}

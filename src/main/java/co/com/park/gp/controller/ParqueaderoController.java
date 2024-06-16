package co.com.park.gp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.park.gp.business.facade.impl.parqueadero.ConsultarParqueaderosFacade;
import co.com.park.gp.business.facade.impl.parqueadero.RegistrarParqueaderoFacade;
import co.com.park.gp.controller.response.ParqueaderoResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.dto.ParqueaderoDTO;

@RestController
@RequestMapping("/parqueaderos/")
public final class ParqueaderoController {

	@GetMapping
	public ResponseEntity<ParqueaderoResponse> consultar() {

		var httpStatusCode = HttpStatus.ACCEPTED;
		var parqueaderoResponse = new ParqueaderoResponse();

		try {
			var parqueaderoDto = ParqueaderoDTO.build();
			var facade = new ConsultarParqueaderosFacade();

			parqueaderoResponse.setDatos(facade.execute(parqueaderoDto));
			parqueaderoResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00042));

		} catch (final GPException excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			parqueaderoResponse.getMensajes().add(excepcion.getMensajeUsuario());

		} catch (final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00029);
			parqueaderoResponse.getMensajes().add(mensajeUsuario);

		}

		return new ResponseEntity<>(parqueaderoResponse, httpStatusCode);
	}

	@PostMapping
	public ResponseEntity<ParqueaderoResponse> crear(@RequestBody ParqueaderoDTO parqueadero) {

		var httpStatusCode = HttpStatus.ACCEPTED;
		var parqueaderoResponse = new ParqueaderoResponse();

		try {
			var facade = new RegistrarParqueaderoFacade();
			facade.execute(parqueadero);
			parqueaderoResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00073));

		} catch (final GPException excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			parqueaderoResponse.getMensajes().add(excepcion.getMensajeUsuario());

		} catch (final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00074);
			parqueaderoResponse.getMensajes().add(mensajeUsuario);

		}

		return new ResponseEntity<>(parqueaderoResponse, httpStatusCode);

	}
}

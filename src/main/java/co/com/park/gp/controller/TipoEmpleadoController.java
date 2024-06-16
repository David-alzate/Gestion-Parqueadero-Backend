package co.com.park.gp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.park.gp.business.facade.impl.tipoempleado.ConsultarTipoEmpleadoFacade;
import co.com.park.gp.controller.response.TipoEmpleadoResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.dto.TipoEmpleadoDTO;

@RestController
@RequestMapping("/tipoEmpleados/")
public class TipoEmpleadoController {

	@GetMapping
	public ResponseEntity<TipoEmpleadoResponse> consultar() {

		var httpStatusCode = HttpStatus.ACCEPTED;
		var tipoEmpleadoResponse = new TipoEmpleadoResponse();

		try {
			var tipoEmpleadoDto = TipoEmpleadoDTO.build();
			var facade = new ConsultarTipoEmpleadoFacade();

			tipoEmpleadoResponse.setDatos(facade.execute(tipoEmpleadoDto));
			tipoEmpleadoResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00095));

		} catch (final GPException excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			tipoEmpleadoResponse.getMensajes().add(excepcion.getMensajeUsuario());

		} catch (final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00096);
			tipoEmpleadoResponse.getMensajes().add(mensajeUsuario);

		}

		return new ResponseEntity<>(tipoEmpleadoResponse, httpStatusCode);
	}

}

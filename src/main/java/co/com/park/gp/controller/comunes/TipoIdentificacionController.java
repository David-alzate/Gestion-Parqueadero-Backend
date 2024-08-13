package co.com.park.gp.controller.comunes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.park.gp.business.facade.impl.comunes.tipoidentificacion.ConsultarTipoIdentificacionFacade;
import co.com.park.gp.controller.response.comunes.TipoIdentificacionResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.dto.comunes.TipoIdentificacionDTO;

@RestController
@RequestMapping("/tipoIdentificaciones/")
public final class TipoIdentificacionController {

	@GetMapping
	public ResponseEntity<TipoIdentificacionResponse> consultar() {

		var httpStatusCode = HttpStatus.ACCEPTED;
		var tipoIdentificacionResponse = new TipoIdentificacionResponse();

		try {
			var tipoIdentificacionDto = TipoIdentificacionDTO.build();
			var facade = new ConsultarTipoIdentificacionFacade();

			tipoIdentificacionResponse.setDatos(facade.execute(tipoIdentificacionDto));
			tipoIdentificacionResponse.getMensajes()
					.add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00097));

		} catch (final GPException excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			tipoIdentificacionResponse.getMensajes().add(excepcion.getMensajeUsuario());

		} catch (final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00098);
			tipoIdentificacionResponse.getMensajes().add(mensajeUsuario);

		}

		return new ResponseEntity<>(tipoIdentificacionResponse, httpStatusCode);
	}

}

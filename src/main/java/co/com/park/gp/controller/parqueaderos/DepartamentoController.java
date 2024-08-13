package co.com.park.gp.controller.parqueaderos;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.park.gp.business.facade.impl.parqueaderos.departamento.ConsultarDepartamentosFacade;
import co.com.park.gp.controller.response.parqueaderos.DepartamentoResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.dto.parqueaderos.DepartamentoDTO;
import co.com.park.gp.dto.parqueaderos.PaisDTO;

@RestController
@RequestMapping("/departamentos/")
public final class DepartamentoController {
	
	@GetMapping ("{id}")
	public ResponseEntity<DepartamentoResponse> consultar(@PathVariable("id") UUID idPais){
		
		var httpStatusCode = HttpStatus.ACCEPTED;
		var departamentoResponse = new DepartamentoResponse();
		
		try {
			var departamentoDto = DepartamentoDTO.build().setPais(PaisDTO.build().setId(idPais));
			var facade = new ConsultarDepartamentosFacade();
			
			departamentoResponse.setDatos(facade.execute(departamentoDto));
			departamentoResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00040));
			
		}catch(final GPException excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			departamentoResponse.getMensajes().add(excepcion.getMensajeUsuario());

		}catch(final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00025);
			departamentoResponse.getMensajes().add(mensajeUsuario);
			
		}
		
		return new ResponseEntity<>(departamentoResponse,httpStatusCode);
	}
}

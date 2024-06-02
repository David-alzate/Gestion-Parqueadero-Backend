package co.com.park.gp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.park.gp.business.facade.impl.pais.ConsultarPaisesFacade;
import co.com.park.gp.controller.response.PaisResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.com.park.gp.dto.PaisDTO;

@RestController
@RequestMapping("/paises/")
public class PaisController {
	
	@GetMapping
	public ResponseEntity<PaisResponse> consultar(){
		
		var httpStatusCode = HttpStatus.ACCEPTED;
		var paisResponse = new PaisResponse();
		
		try {
			var paisDto = PaisDTO.build();
			var facade = new ConsultarPaisesFacade();
			
			paisResponse.setDatos(facade.execute(paisDto));
			paisResponse.getMensajes().add("paises consultados exitosamente");
			
		}catch(final GPException excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			paisResponse.getMensajes().add(excepcion.getMensajeUsuario());

		}catch(final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00027);
			paisResponse.getMensajes().add(mensajeUsuario);
			
		}
		
		return new ResponseEntity<>(paisResponse,httpStatusCode);
	}

}

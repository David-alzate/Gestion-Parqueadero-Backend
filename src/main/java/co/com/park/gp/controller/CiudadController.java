package co.com.park.gp.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.park.gp.controller.response.CiudadResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.com.park.gp.dto.CiudadDTO;
import co.com.park.gp.dto.DepartamentoDTO;
import co.com.park.gp.business.facade.impl.ciudad.ConsultarCiudadesFacade;

@RestController
@RequestMapping("/ciudades/")
public final class CiudadController {
	
	
	@GetMapping ("{id}")
	public ResponseEntity<CiudadResponse> consultar(@PathVariable("id") UUID idDepartamento){
		
		var httpStatusCode = HttpStatus.ACCEPTED;
		var ciudadResponse = new CiudadResponse();
		
		try {
			var ciudadDto = CiudadDTO.build().setDepartamento(DepartamentoDTO.build().setId(idDepartamento));
			var facade = new ConsultarCiudadesFacade();
			
			ciudadResponse.setDatos(facade.execute(ciudadDto));
			ciudadResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00039));
			
		}catch(final GPException excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			ciudadResponse.getMensajes().add(excepcion.getMensajeUsuario());
			excepcion.printStackTrace();
		}catch(final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00023);
			ciudadResponse.getMensajes().add(mensajeUsuario);
			
			excepcion.printStackTrace();
		}
		
		return new ResponseEntity<>(ciudadResponse,httpStatusCode);
	}
}

package co.com.park.gp.controller.response.comunes;

import java.util.ArrayList;

import co.com.park.gp.controller.response.Response;
import co.com.park.gp.dto.comunes.TipoIdentificacionDTO;

public class TipoIdentificacionResponse extends Response<TipoIdentificacionDTO> {
	
	public TipoIdentificacionResponse() {
		setMensajes(new ArrayList<>());
		setDatos(new ArrayList<>());
	}

}

package co.com.park.gp.controller.response;

import java.util.ArrayList;

import co.com.park.gp.dto.TipoIdentificacionDTO;

public class TipoIdentificacionResponse extends Response<TipoIdentificacionDTO>{
	
	public TipoIdentificacionResponse() {
		setMensajes(new ArrayList<>());
		setDatos(new ArrayList<>());
	}

}

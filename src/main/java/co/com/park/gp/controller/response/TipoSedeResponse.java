package co.com.park.gp.controller.response;

import java.util.ArrayList;

import co.com.park.gp.dto.TipoSedeDTO;

public class TipoSedeResponse extends Response<TipoSedeDTO> {
	
	public TipoSedeResponse() {
		setMensajes(new ArrayList<>());
		setDatos(new ArrayList<>());
	}

}

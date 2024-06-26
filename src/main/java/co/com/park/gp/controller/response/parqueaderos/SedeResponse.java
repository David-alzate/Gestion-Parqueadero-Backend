package co.com.park.gp.controller.response.parqueaderos;

import java.util.ArrayList;

import co.com.park.gp.controller.response.Response;
import co.com.park.gp.dto.parqueaderos.SedeDTO;

public class SedeResponse extends Response<SedeDTO> {
	
	public SedeResponse() {
		setMensajes(new ArrayList<>());
		setDatos(new ArrayList<>());
	}

}

package co.com.park.gp.controller.response.parqueaderos;

import java.util.ArrayList;

import co.com.park.gp.controller.response.Response;
import co.com.park.gp.dto.parqueaderos.PaisDTO;

public class PaisResponse extends Response<PaisDTO> {
	
	public  PaisResponse() {
		setMensajes(new ArrayList<>());
		setDatos(new ArrayList<>());
	}

}

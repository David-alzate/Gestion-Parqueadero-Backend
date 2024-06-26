package co.com.park.gp.controller.response.parqueaderos;

import java.util.ArrayList;

import co.com.park.gp.controller.response.Response;
import co.com.park.gp.dto.parqueaderos.ParqueaderoDTO;

public class ParqueaderoResponse extends Response<ParqueaderoDTO> {
	
	public ParqueaderoResponse(){
		setMensajes(new ArrayList<>());
		setDatos(new ArrayList<>());
	}

}

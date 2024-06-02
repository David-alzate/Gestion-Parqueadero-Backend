package co.com.park.gp.controller.response;

import java.util.ArrayList;

import co.com.park.gp.dto.SedeDTO;

public class SedeResponse extends Response<SedeDTO>{
	
	public SedeResponse() {
		setMensajes(new ArrayList<>());
		setDatos(new ArrayList<>());
	}

}

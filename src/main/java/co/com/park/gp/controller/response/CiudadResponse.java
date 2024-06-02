package co.com.park.gp.controller.response;

import java.util.ArrayList;

import co.com.park.gp.dto.CiudadDTO;

public class CiudadResponse extends Response<CiudadDTO> {
	
	public CiudadResponse() {
		setMensajes(new ArrayList<>());
		setDatos(new ArrayList<>());
	}

}

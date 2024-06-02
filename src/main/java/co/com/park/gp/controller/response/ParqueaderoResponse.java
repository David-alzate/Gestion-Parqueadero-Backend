package co.com.park.gp.controller.response;

import java.util.ArrayList;

import co.com.park.gp.dto.ParqueaderoDTO;

public class ParqueaderoResponse extends Response<ParqueaderoDTO>{
	
	public ParqueaderoResponse(){
		setMensajes(new ArrayList<>());
		setDatos(new ArrayList<>());
	}

}

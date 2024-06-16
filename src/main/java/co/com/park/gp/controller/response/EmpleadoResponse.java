package co.com.park.gp.controller.response;

import java.util.ArrayList;

import co.com.park.gp.dto.EmpleadoDTO;

public class EmpleadoResponse extends Response<EmpleadoDTO> {

	public EmpleadoResponse() {
		setMensajes(new ArrayList<>());
		setDatos(new ArrayList<>());
	}

}

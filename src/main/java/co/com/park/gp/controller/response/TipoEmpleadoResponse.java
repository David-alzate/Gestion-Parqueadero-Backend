package co.com.park.gp.controller.response;

import java.util.ArrayList;

import co.com.park.gp.dto.TipoEmpleadoDTO;

public class TipoEmpleadoResponse extends Response<TipoEmpleadoDTO>{
	
	public TipoEmpleadoResponse() {
		setMensajes(new ArrayList<>());
		setDatos(new ArrayList<>());
	}

}

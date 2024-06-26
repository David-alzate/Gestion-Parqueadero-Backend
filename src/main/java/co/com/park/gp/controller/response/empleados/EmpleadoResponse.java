package co.com.park.gp.controller.response.empleados;

import java.util.ArrayList;

import co.com.park.gp.controller.response.Response;
import co.com.park.gp.dto.empleados.EmpleadoDTO;

public class EmpleadoResponse extends Response<EmpleadoDTO> {

	public EmpleadoResponse() {
		setMensajes(new ArrayList<>());
		setDatos(new ArrayList<>());
	}

}

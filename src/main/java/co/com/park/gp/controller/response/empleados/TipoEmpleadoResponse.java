package co.com.park.gp.controller.response.empleados;

import java.util.ArrayList;

import co.com.park.gp.controller.response.Response;
import co.com.park.gp.dto.empleados.TipoEmpleadoDTO;

public class TipoEmpleadoResponse extends Response<TipoEmpleadoDTO> {
	
	public TipoEmpleadoResponse() {
		setMensajes(new ArrayList<>());
		setDatos(new ArrayList<>());
	}

}

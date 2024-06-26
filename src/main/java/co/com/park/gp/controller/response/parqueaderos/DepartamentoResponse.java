package co.com.park.gp.controller.response.parqueaderos;

import java.util.ArrayList;

import co.com.park.gp.controller.response.Response;
import co.com.park.gp.dto.parqueaderos.DepartamentoDTO;

public class DepartamentoResponse extends Response<DepartamentoDTO> {

	public DepartamentoResponse() {
		setMensajes(new ArrayList<>());
		setDatos(new ArrayList<>());
	}
	
	

}

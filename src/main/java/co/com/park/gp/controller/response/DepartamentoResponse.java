package co.com.park.gp.controller.response;

import java.util.ArrayList;

import co.com.park.gp.dto.DepartamentoDTO;

public class DepartamentoResponse extends Response<DepartamentoDTO>{

	public DepartamentoResponse() {
		setMensajes(new ArrayList<>());
		setDatos(new ArrayList<>());
	}
	
	

}

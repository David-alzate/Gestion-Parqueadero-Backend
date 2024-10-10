package co.com.park.gp.controller.response.metodopago;

import java.util.ArrayList;

import co.com.park.gp.controller.response.Response;
import co.com.park.gp.dto.facturacion.MetodoPagoDTO;

public class MetodoPagoResponse extends Response<MetodoPagoDTO>{
	
	public MetodoPagoResponse() {
		setMensajes(new ArrayList<>());
		setDatos(new ArrayList<>());
	}

}

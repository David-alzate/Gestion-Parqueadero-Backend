package co.com.park.gp.controller.response;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {
	
	private List<String> mensajes = new ArrayList<>();
	private List<T> datos;
	
	
	public final List<String> getMensajes() {
		return mensajes;
	}
	public final void setMensajes(List<String> mensajes) {
		this.mensajes = mensajes;
	}
	public final List<T> getDatos() {
		return datos;
	}
	public final void setDatos(List<T> datos) {
		this.datos = datos;
	}
	
	

}

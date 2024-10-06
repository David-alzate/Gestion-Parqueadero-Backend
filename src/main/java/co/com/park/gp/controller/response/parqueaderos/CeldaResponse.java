package co.com.park.gp.controller.response.parqueaderos;

import java.util.ArrayList;
import java.util.List;

public class CeldaResponse {

    private Object datos; // Cambiado a Object para soportar múltiples tipos
    private List<String> mensajes;

    public CeldaResponse() {
        mensajes = new ArrayList<>();
    }

    public Object getDatos() {
        return datos;
    }

    public void setDatos(Object datos) {
        this.datos = datos;
    }

    public List<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }
}

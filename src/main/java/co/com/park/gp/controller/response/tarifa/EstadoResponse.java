package co.com.park.gp.controller.response.tarifa;

import co.com.park.gp.controller.response.Response;
import co.com.park.gp.dto.tarifas.EstadoDTO;

import java.util.ArrayList;

public class EstadoResponse extends Response<EstadoDTO> {

    public EstadoResponse() {
        setMensajes(new ArrayList<>());
        setDatos(new ArrayList<>());
    }
}

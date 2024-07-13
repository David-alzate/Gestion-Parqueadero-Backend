package co.com.park.gp.controller.response.vehiculos;

import co.com.park.gp.controller.response.Response;
import co.com.park.gp.dto.vehiculos.VehiculoDTO;

import java.util.ArrayList;

public class VehiculoResponse extends Response<VehiculoDTO> {

    public VehiculoResponse() {
        setMensajes(new ArrayList<>());
        setDatos(new ArrayList<>());
    }
}

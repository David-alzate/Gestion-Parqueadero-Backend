package co.com.park.gp.controller.response.sesionesparqueo;

import co.com.park.gp.controller.response.Response;
import co.com.park.gp.dto.sesionesparqueo.SesionParqueoDTO;

import java.util.ArrayList;

public class SesionParqueoResponse extends Response<SesionParqueoDTO> {

    public SesionParqueoResponse() {
        setMensajes(new ArrayList<>());
        setDatos(new ArrayList<>());
    }
}

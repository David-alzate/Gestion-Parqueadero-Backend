package co.com.park.gp.controller.response.planes;

import co.com.park.gp.controller.response.Response;
import co.com.park.gp.dto.planes.TipoPlanDTO;

import java.util.ArrayList;

public class TipoPlanResponse extends Response<TipoPlanDTO> {

    public TipoPlanResponse() {
        setMensajes(new ArrayList<>());
        setDatos(new ArrayList<>());
    }
}

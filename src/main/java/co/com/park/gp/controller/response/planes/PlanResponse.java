package co.com.park.gp.controller.response.planes;

import co.com.park.gp.controller.response.Response;
import co.com.park.gp.dto.planes.PlanDTO;

import java.util.ArrayList;

public class PlanResponse extends Response<PlanDTO> {

    public PlanResponse() {
        setMensajes(new ArrayList<>());
        setDatos(new ArrayList<>());
    }
}

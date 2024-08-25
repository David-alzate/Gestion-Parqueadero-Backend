package co.com.park.gp.controller.response.parqueaderos;

import co.com.park.gp.controller.response.Response;
import co.com.park.gp.dto.parqueaderos.CeldaDTO;

import java.util.ArrayList;

public class CeldaResponse extends Response<CeldaDTO> {

    public CeldaResponse() {
        setMensajes(new ArrayList<>());
        setDatos(new ArrayList<>());
    }
}

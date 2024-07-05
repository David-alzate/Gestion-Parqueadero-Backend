package co.com.park.gp.controller.response.tarifa;

import co.com.park.gp.controller.response.Response;
import co.com.park.gp.dto.tarifas.TarifaDTO;

import java.util.ArrayList;

public class TarifaResponse extends Response<TarifaDTO> {

    public TarifaResponse() {
        setMensajes(new ArrayList<>());
        setDatos(new ArrayList<>());
    }
}

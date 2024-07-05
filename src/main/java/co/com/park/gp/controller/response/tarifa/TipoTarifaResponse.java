package co.com.park.gp.controller.response.tarifa;

import co.com.park.gp.controller.response.Response;
import co.com.park.gp.dto.tarifas.TipoTarifaDTO;

import java.util.ArrayList;

public class TipoTarifaResponse extends Response<TipoTarifaDTO> {

    public TipoTarifaResponse() {
        setMensajes(new ArrayList<>());
        setDatos(new ArrayList<>());
    }
}

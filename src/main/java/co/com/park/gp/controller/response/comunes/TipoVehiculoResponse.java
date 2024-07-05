package co.com.park.gp.controller.response.comunes;

import co.com.park.gp.controller.response.Response;
import co.com.park.gp.dto.comunes.TipoVehiculoDTO;

import java.util.ArrayList;

public class TipoVehiculoResponse extends Response<TipoVehiculoDTO> {

    public TipoVehiculoResponse() {
        setMensajes(new ArrayList<>());
        setDatos(new ArrayList<>());
    }
}

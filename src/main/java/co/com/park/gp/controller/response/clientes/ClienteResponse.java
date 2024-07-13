package co.com.park.gp.controller.response.clientes;

import co.com.park.gp.controller.response.Response;
import co.com.park.gp.dto.clientes.ClienteDTO;

import java.util.ArrayList;

public class ClienteResponse extends Response<ClienteDTO> {

    public ClienteResponse() {
        setMensajes(new ArrayList<>());
        setDatos(new ArrayList<>());
    }
}

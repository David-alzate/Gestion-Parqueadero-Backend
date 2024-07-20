package co.com.park.gp.controller.response.login;

import java.util.ArrayList;

import co.com.park.gp.controller.response.Response;
import co.com.park.gp.dto.login.LoginDTO;

public class LoginResonse extends Response<LoginDTO> {

    private boolean success;
    private String tipoEmpleado;

    public LoginResonse() {
        setMensajes(new ArrayList<>());
        setDatos(new ArrayList<>());
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
}
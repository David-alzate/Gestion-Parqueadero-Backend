package co.com.park.gp.controller.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.com.park.gp.business.facade.impl.login.IniciarSesionFacade;
import co.com.park.gp.controller.response.login.LoginResonse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.dto.login.LoginDTO;

@RestController
@RequestMapping("/login/")
public final class LoginController {

    @PostMapping
    public ResponseEntity<LoginResonse> crear(@RequestBody LoginDTO data) {
        var httpStatusCode = HttpStatus.ACCEPTED;
        var loginResonse = new LoginResonse();

        try {
            var facade = new IniciarSesionFacade();
            LoginResonse response = facade.execute(data);
            loginResonse.setSuccess(response.isSuccess());
            loginResonse.setTipoEmpleado(response.getTipoEmpleado());
            loginResonse.getMensajes().addAll(response.getMensajes());
        } catch (final GPException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            loginResonse.setSuccess(false);
            loginResonse.getMensajes().add(excepcion.getMensajeUsuario());
			excepcion.printStackTrace();
        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            loginResonse.setSuccess(false);
            var mensajeUsuario = "Se ha presentado un problema tratando de iniciar sesión";
            loginResonse.getMensajes().add(mensajeUsuario);
			excepcion.printStackTrace();

        }

        return new ResponseEntity<>(loginResonse, httpStatusCode);
    }


}

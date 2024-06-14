package co.com.park.gp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.com.park.gp.business.facade.impl.login.IniciarSesionFacade;
import co.com.park.gp.controller.response.LoginResonse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.dto.LoginDTO;


@RestController
@RequestMapping("/login/")
public class LoginController {
	
	@PostMapping
    public ResponseEntity<LoginResonse> crear(@RequestBody LoginDTO data) {
        var httpStatusCode = HttpStatus.ACCEPTED;
        var loginResonse = new LoginResonse();

        try {
            var facade = new IniciarSesionFacade();
            boolean loginSuccess = facade.execute(data);
            loginResonse.setSuccess(loginSuccess);
            if (loginSuccess) {
                loginResonse.getMensajes().add("Inicio de sesión exitoso");
            } else {
                httpStatusCode = HttpStatus.UNAUTHORIZED;
                loginResonse.getMensajes().add("Credenciales incorrectas");
            }
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

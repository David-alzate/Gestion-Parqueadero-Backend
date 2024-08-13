package co.com.park.gp.business.facade.impl.login;

import co.com.park.gp.business.assembler.dto.impl.login.LoginAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.login.IniciarSesion;
import co.com.park.gp.controller.response.login.LoginResonse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.login.LoginDTO;

public class IniciarSesionFacade implements FacadeWhitReturn<LoginDTO, LoginResonse> {

    private final DAOFactory daoFactory;

    public IniciarSesionFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public LoginResonse execute(LoginDTO dto) {
        daoFactory.iniciarTransaccion();
        try {
            var useCase = new IniciarSesion(daoFactory);
            var loginDomain = LoginAssemblerDTO.getInstance().toDomain(dto);

            var result = useCase.execute(loginDomain);

            var loginResonse = new LoginResonse();
            loginResonse.setSuccess(true);
            loginResonse.setTipoEmpleado(result.getTipoEmpleado().getNombre()); // Ajustado para incluir el nombre
            loginResonse.getMensajes().add("Inicio de sesión exitoso");

            return loginResonse;
        } catch (final GPException exception) {
            throw exception;
        } catch (final Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00031);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00032);

            var loginResonse = new LoginResonse();
            loginResonse.setSuccess(false);
            loginResonse.setTipoEmpleado(null);
            loginResonse.getMensajes().add(mensajeUsuario);

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}


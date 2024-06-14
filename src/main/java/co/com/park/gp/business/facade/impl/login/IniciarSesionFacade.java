package co.com.park.gp.business.facade.impl.login;

import co.com.park.gp.business.assembler.dto.impl.LoginAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.login.IniciarSesion;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.LoginDTO;

public class IniciarSesionFacade implements FacadeWhitReturn<LoginDTO, Boolean> {

	private DAOFactory daoFactory;

	public IniciarSesionFacade() {
		daoFactory = DAOFactory.getFactory();
	}

	@Override
	public Boolean execute(LoginDTO dto) {
		daoFactory.iniciarTransaccion();
		try {

			var useCase = new IniciarSesion(daoFactory);
			var loginDomain = LoginAssemblerDTO.getInstance().toDomain(dto);

			return useCase.execute(loginDomain);
		} catch (final GPException exception) {
			throw exception;
		} catch (final Exception exception) {

			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00031);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00032);

			throw new BusinessGPException(mensajeTecnico, mensajeUsuario, exception);

		} finally {
			daoFactory.cerrarConexion();
		}
	}

}

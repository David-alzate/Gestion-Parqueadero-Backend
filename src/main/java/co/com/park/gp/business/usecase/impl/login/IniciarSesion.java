package co.com.park.gp.business.usecase.impl.login;

import co.com.park.gp.business.domain.LoginDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

public class IniciarSesion implements UseCaseWithReturn<LoginDomain, Boolean>{
	
	private DAOFactory factory;
	
	public IniciarSesion(final DAOFactory factory) {
		if (ObjectHelper.getObjectHelper().isNull(factory)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00078);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00079);
			throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
		}

		this.factory = factory;
	}

	@Override
	public Boolean execute(LoginDomain data) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

package co.com.park.gp.business.usecase.impl.login;

import co.com.park.gp.business.domain.LoginDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.EmpleadoEntity;

public class IniciarSesion implements UseCaseWithReturn<LoginDomain, Boolean> {

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
	    validarCorreoElectronio(data.getCorreoElectronico());
	    validarPassword(data.getPassword());
	    validarUsuario(data.getCorreoElectronico(), data.getPassword());
	    return true;
	}

	private void validarUsuario(final String correoElectronico, final String password) {
		var empleadoEntity = EmpleadoEntity.build().setCorreoElectronico(correoElectronico)
				.setPassword(password);
		
		var resultados = factory.getEmpleadoDAO().consultar(empleadoEntity);
		
		if (resultados.isEmpty()) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00080);
			throw new BusinessGPException(mensajeUsuario);
		}
	}
	
	private void validarCorreoElectronio(final String correoElectronico) {
		if (TextHelper.isNullOrEmpty(correoElectronico)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00081);
			throw new BusinessGPException(mensajeUsuario);
		}
	}
	
	private void validarPassword(final String password) {
		if (TextHelper.isNullOrEmpty(password)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00082);
			throw new BusinessGPException(mensajeUsuario);
		}
	}

}

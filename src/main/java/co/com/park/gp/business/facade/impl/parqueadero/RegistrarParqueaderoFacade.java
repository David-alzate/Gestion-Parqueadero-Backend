package co.com.park.gp.business.facade.impl.parqueadero;

import co.com.park.gp.business.assembler.dto.impl.ParqueaderoAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitoutReturn;
import co.com.park.gp.business.usecase.impl.parqueadero.RegistrarParqueadero;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.ParqueaderoDTO;

public class RegistrarParqueaderoFacade implements FacadeWhitoutReturn<ParqueaderoDTO> {

	private DAOFactory daoFactory;

	public RegistrarParqueaderoFacade() {
		daoFactory = DAOFactory.getFactory();
	}

	@Override
	public void execute(ParqueaderoDTO dto) {
		daoFactory.iniciarTransaccion();

		try {
			var useCase = new RegistrarParqueadero(daoFactory);
			var parqueaderoDomain = ParqueaderoAssemblerDTO.getInstance().toDomain(dto);

			useCase.execute(parqueaderoDomain);

			daoFactory.confirmarTransaccion();
		} catch (GPException excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();

			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00071);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00072);

			throw new BusinessGPException(mensajeTecnico, mensajeUsuario, excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}

	}

}

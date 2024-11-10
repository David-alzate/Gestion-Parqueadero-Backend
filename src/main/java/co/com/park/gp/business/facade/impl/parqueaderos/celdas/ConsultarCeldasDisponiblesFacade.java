package co.com.park.gp.business.facade.impl.parqueaderos.celdas;

import co.com.park.gp.business.assembler.dto.impl.parqueaderos.CeldaAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.parqueaderos.celdas.ConsultarCeldasDisponibles;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.parqueaderos.CeldaDTO;

public class ConsultarCeldasDisponiblesFacade implements FacadeWhitReturn<CeldaDTO, Integer> {

	private final DAOFactory daoFactory;

	public ConsultarCeldasDisponiblesFacade() {
		daoFactory = DAOFactory.getFactory();
	}

	@Override
	public Integer execute(CeldaDTO dto) {
		try {

			var useCase = new ConsultarCeldasDisponibles(daoFactory);
			var celdaDomain = CeldaAssemblerDTO.getInstance().toDomain(dto);

			return useCase.execute(celdaDomain);

		} catch (final GPException exception) {
			throw exception;
		} catch (final Exception exception) {
			var mensajeUsuario = "Se ha presentado un problema al consultar la información de las celdas";
			var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el select en la tabla \"celda\" de la base de datos.";

			throw new BusinessGPException(mensajeTecnico, mensajeUsuario, exception);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

}

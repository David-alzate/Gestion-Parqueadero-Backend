package co.com.park.gp.business.usecase.impl.parqueaderos.celdas;

import co.com.park.gp.business.assembler.entity.impl.parqueaderos.CeldaAssemblerEntity;
import co.com.park.gp.business.domain.parqueaderos.CeldaDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

public class ConsultarCeldasDisponibles implements UseCaseWithReturn<CeldaDomain, Integer>{
	
	private final DAOFactory factory;

    public ConsultarCeldasDisponibles(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la consulta de las celdas Disponibles...";
            var mensajeTecnico = "El dao factory para consultar las celdas Disponibles llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

	@Override
	public Integer execute(CeldaDomain data) {
		var celdaEntityFilter = CeldaAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getCeldaDao().celdasDisponibles(celdaEntityFilter.getId(), celdaEntityFilter.getTipoVehiculo().getId());
        return resultadosEntity;
	}

}

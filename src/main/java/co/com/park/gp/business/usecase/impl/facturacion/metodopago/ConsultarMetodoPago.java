package co.com.park.gp.business.usecase.impl.facturacion.metodopago;

import java.util.List;

import co.com.park.gp.business.assembler.entity.impl.facturacion.MetodoPagoAssemblerEntity;
import co.com.park.gp.business.domain.facturacion.MetodoPagoDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

public class ConsultarMetodoPago implements UseCaseWithReturn<MetodoPagoDomain, List<MetodoPagoDomain>>{
	
	private final DAOFactory factory;

    public ConsultarMetodoPago(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la consulta del Metodo de pago...";
            var mensajeTecnico = "El dao factory para consultar el metodo de pago llego nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

	@Override
	public List<MetodoPagoDomain> execute(MetodoPagoDomain data) {
		var metodoPagoEntityFilter = MetodoPagoAssemblerEntity.getInstance().toEntity(data);
		var resultadosEntity = factory.getMetodoPagoDao().consultar(metodoPagoEntityFilter);
		return MetodoPagoAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
	}

}

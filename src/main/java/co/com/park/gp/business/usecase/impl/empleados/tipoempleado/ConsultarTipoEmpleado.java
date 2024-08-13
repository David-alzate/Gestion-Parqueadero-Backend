package co.com.park.gp.business.usecase.impl.empleados.tipoempleado;

import java.util.List;

import co.com.park.gp.business.assembler.entity.impl.empleados.TipoEmpleadoAssemblerEntity;
import co.com.park.gp.business.domain.empleados.TipoEmpleadoDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

public class ConsultarTipoEmpleado implements UseCaseWithReturn<TipoEmpleadoDomain, List<TipoEmpleadoDomain>> {

    private final DAOFactory factory;

    public ConsultarTipoEmpleado(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00037);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00038);
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public List<TipoEmpleadoDomain> execute(TipoEmpleadoDomain data) {
        var tipoEmpleadoEntityFilter = TipoEmpleadoAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getTipoEmpleadoDAO().consultar(tipoEmpleadoEntityFilter);

        return TipoEmpleadoAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
    }

}

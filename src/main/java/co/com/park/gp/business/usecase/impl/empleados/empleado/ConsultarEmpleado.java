package co.com.park.gp.business.usecase.impl.empleados.empleado;

import java.util.List;

import co.com.park.gp.business.assembler.entity.impl.empleados.EmpleadoAssemblerEntity;
import co.com.park.gp.business.domain.empleados.EmpleadoDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

public class ConsultarEmpleado implements UseCaseWithReturn<EmpleadoDomain, List<EmpleadoDomain>> {

    private final DAOFactory factory;

    public ConsultarEmpleado(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00035);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00036);
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public List<EmpleadoDomain> execute(EmpleadoDomain data) {

        var empleadoEntityFilter = EmpleadoAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getEmpleadoDAO().consultar(empleadoEntityFilter);

        return EmpleadoAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
    }

}

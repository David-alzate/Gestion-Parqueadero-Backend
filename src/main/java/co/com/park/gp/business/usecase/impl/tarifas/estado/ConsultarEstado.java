package co.com.park.gp.business.usecase.impl.tarifas.estado;

import co.com.park.gp.business.assembler.entity.impl.tarifas.EstadoAssemblerEntity;
import co.com.park.gp.business.domain.tarifas.EstadoDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

import java.util.List;

public class ConsultarEstado implements UseCaseWithReturn<EstadoDomain, List<EstadoDomain>> {

    private final DAOFactory factory;

    public ConsultarEstado(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la consulta de los Estados...";
            var mensajeTecnico = "El dao factory para consultar los Estados llego nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public List<EstadoDomain> execute(EstadoDomain data) {
        var estadoEntityFilter = EstadoAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getEstadoDAO().consultar(estadoEntityFilter);

        return EstadoAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
    }
}

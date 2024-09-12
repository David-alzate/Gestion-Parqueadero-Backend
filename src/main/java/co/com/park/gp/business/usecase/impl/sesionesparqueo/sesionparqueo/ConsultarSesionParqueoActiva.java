package co.com.park.gp.business.usecase.impl.sesionesparqueo.sesionparqueo;

import co.com.park.gp.business.assembler.entity.impl.sesionparqueo.SesionParqueoAssemblerEntity;
import co.com.park.gp.business.domain.sesionparqueo.SesionParqueoDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.enums.EstadoEnum;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.tarifas.EstadoEntity;

import java.util.List;

public class ConsultarSesionParqueoActiva implements UseCaseWithReturn<SesionParqueoDomain, List<SesionParqueoDomain>> {

    private final DAOFactory factory;

    public ConsultarSesionParqueoActiva(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la Consulta de las sesines de parqueo Activas...";
            var mensajeTecnico = "El dao factory para consultar las sesiones de parqueo Activas llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public List<SesionParqueoDomain> execute(SesionParqueoDomain data) {
        var estadoActivo = factory.getEstadoDAO().consultarPorDescripcion(EstadoEnum.ACTIVO.getNombre());

        var sesionParqueoFilter = SesionParqueoAssemblerEntity.getInstance().toEntity(data);
        var sesionActiva = sesionParqueoFilter.setEstado(EstadoEntity.build().setId(estadoActivo.getId()));
        var resultadosEntity = factory.getSesionParqueoDAO().consultar(sesionActiva);

        return SesionParqueoAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
    }
}

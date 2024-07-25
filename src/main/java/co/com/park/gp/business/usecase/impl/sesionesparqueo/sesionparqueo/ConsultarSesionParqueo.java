package co.com.park.gp.business.usecase.impl.sesionesparqueo.sesionparqueo;

import co.com.park.gp.business.assembler.entity.impl.sesionparqueo.SesionParqueoAssemblerEntity;
import co.com.park.gp.business.domain.sesionparqueo.SesionParqueoDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

import java.util.List;

public class ConsultarSesionParqueo implements UseCaseWithReturn<SesionParqueoDomain, List<SesionParqueoDomain>> {

    private final DAOFactory factory;

    public ConsultarSesionParqueo(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la Consulta de las sesines de parqueo...";
            var mensajeTecnico = "El dao factory para consultar las sesiones de parqueo llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public List<SesionParqueoDomain> execute(SesionParqueoDomain data) {
        var sesionParqueoFilter = SesionParqueoAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getSesionParqueoDAO().consultar(sesionParqueoFilter);

        return SesionParqueoAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
    }
}

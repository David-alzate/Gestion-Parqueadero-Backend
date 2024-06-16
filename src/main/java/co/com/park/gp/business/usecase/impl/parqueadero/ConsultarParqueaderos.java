package co.com.park.gp.business.usecase.impl.parqueadero;

import java.util.List;

import co.com.park.gp.business.assembler.entity.impl.ParqueaderoAssemblerEntity;
import co.com.park.gp.business.domain.ParqueaderoDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

public class ConsultarParqueaderos implements UseCaseWithReturn<ParqueaderoDomain, List<ParqueaderoDomain>> {

    private final DAOFactory factory;

    public ConsultarParqueaderos(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la consulta de los parqueaderos...";
            var mensajeTecnico = "El dao factory para consultar el parqueadero llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public List<ParqueaderoDomain> execute(final ParqueaderoDomain data) {
        var parqueaderoEntityFilter =
                ParqueaderoAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getParqueaderoDAO().consultar(parqueaderoEntityFilter);

        return ParqueaderoAssemblerEntity.getInstance().
                toDomainCollection(resultadosEntity);
    }
}
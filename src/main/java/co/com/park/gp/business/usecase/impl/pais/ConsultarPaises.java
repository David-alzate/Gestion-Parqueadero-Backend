package co.com.park.gp.business.usecase.impl.pais;

import java.util.List;

import co.com.park.gp.business.assembler.entity.impl.PaisAssemblerEntity;
import co.com.park.gp.business.domain.PaisDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

public class ConsultarPaises implements UseCaseWithReturn<PaisDomain, List<PaisDomain>> {

    private final DAOFactory factory;

    public ConsultarPaises(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la consulta de los países...";
            var mensajeTecnico = "El dao factory para consultar el país llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public List<PaisDomain> execute(final PaisDomain data) {
        var paisEntityFilter =
                PaisAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getPaisDAO().consultar(paisEntityFilter);

        return PaisAssemblerEntity.getInstance().
                toDomainCollection(resultadosEntity);
    }
}


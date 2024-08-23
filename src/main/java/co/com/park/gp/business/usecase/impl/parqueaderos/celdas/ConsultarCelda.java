package co.com.park.gp.business.usecase.impl.parqueaderos.celdas;

import co.com.park.gp.business.assembler.entity.impl.parqueaderos.CeldaAssemblerEntity;
import co.com.park.gp.business.domain.parqueaderos.CeldaDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

import java.util.List;

public class ConsultarCelda implements UseCaseWithReturn<CeldaDomain, List<CeldaDomain>> {

    private final DAOFactory factory;

    public ConsultarCelda(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la consulta de las celdas...";
            var mensajeTecnico = "El dao factory para consultar las celdas llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public List<CeldaDomain> execute(CeldaDomain data) {
        var celdaEntityFilter = CeldaAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getCeldaDao().consultar(celdaEntityFilter);

        return CeldaAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
    }
}

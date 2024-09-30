package co.com.park.gp.business.usecase.impl.tarifas.tarifa;

import co.com.park.gp.business.assembler.entity.impl.tarifas.TarifaAssemblerEntity;
import co.com.park.gp.business.domain.tarifas.TarifaDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.enums.EstadoEnum;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.tarifas.EstadoEntity;

import java.util.List;

public class ConsultarTarifasActivas implements UseCaseWithReturn<TarifaDomain, List<TarifaDomain>> {

    private final DAOFactory factory;

    public ConsultarTarifasActivas(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la Consulta de las tarifas Activas...";
            var mensajeTecnico = "El dao factory para consultar las tarifas Activas llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public List<TarifaDomain> execute(TarifaDomain data) {
        var tarifaFilter = TarifaAssemblerEntity.getInstance().toEntity(data);
        var tarifaActiva = tarifaFilter.setEstado(EstadoEntity.build().setId(EstadoEnum.ACTIVO.getId(factory)));
        var resultadosEntity = factory.getTarifaDAO().consultar(tarifaActiva);
        return TarifaAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
    }
}

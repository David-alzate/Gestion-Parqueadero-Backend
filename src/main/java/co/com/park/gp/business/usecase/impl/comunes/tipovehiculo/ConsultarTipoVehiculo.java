package co.com.park.gp.business.usecase.impl.comunes.tipovehiculo;

import co.com.park.gp.business.assembler.entity.impl.comunes.TipoVehiculoAssemblerEntity;
import co.com.park.gp.business.domain.comunes.TipoVehiculoDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

import java.util.List;

public class ConsultarTipoVehiculo implements UseCaseWithReturn<TipoVehiculoDomain, List<TipoVehiculoDomain>> {

    private final DAOFactory factory;

    public ConsultarTipoVehiculo(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la consulta del tipo Vehiculo...";
            var mensajeTecnico = "El dao factory para consultar los Tipo de Vehiculo llego nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public List<TipoVehiculoDomain> execute(TipoVehiculoDomain data) {
        var tipoVehiculoEntityFilter = TipoVehiculoAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getTipoVehiculoDAO().consultar(tipoVehiculoEntityFilter);

        return TipoVehiculoAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
    }
}

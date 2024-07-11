package co.com.park.gp.business.usecase.impl.vehiculos.vehiculo;

import co.com.park.gp.business.assembler.entity.impl.vehiculos.VehiculoAssemblerEntity;
import co.com.park.gp.business.domain.vehiculos.VehiculoDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

import java.util.List;

public class ConsultarVehiculo implements UseCaseWithReturn<VehiculoDomain, List<VehiculoDomain>> {

    private final DAOFactory factory;

    public ConsultarVehiculo(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la consulta de los vehiculos...";
            var mensajeTecnico = "El dao factory para consultar el vehiculo llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public List<VehiculoDomain> execute(VehiculoDomain data) {
        var vehiculoEntityFilter = VehiculoAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getVehiculoDAO().consultar(vehiculoEntityFilter);

        return VehiculoAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
    }
}

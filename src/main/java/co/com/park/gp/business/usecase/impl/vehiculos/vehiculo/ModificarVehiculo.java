package co.com.park.gp.business.usecase.impl.vehiculos.vehiculo;

import co.com.park.gp.business.assembler.entity.impl.comunes.TipoVehiculoAssemblerEntity;
import co.com.park.gp.business.domain.vehiculos.VehiculoDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.vehiculos.VehiculoEntity;

public class ModificarVehiculo implements UseCaseWithoutReturn<VehiculoDomain> {

    private final DAOFactory factory;

    public ModificarVehiculo(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la modificacion de los vehiculos...";
            var mensajeTecnico = "El dao factory para modificar el vehiculo llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }


    @Override
    public void execute(VehiculoDomain data) {

        var vehiculoEntity = VehiculoEntity.build().setId(data.getId())
                .setTipoVehiculo(TipoVehiculoAssemblerEntity.getInstance().toEntity(data.getTipoVehiculo()))
                .setPlaca(data.getPlaca());

        factory.getVehiculoDAO().modificar(vehiculoEntity);

    }
}

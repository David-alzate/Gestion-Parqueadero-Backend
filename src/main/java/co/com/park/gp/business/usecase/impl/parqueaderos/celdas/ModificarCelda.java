package co.com.park.gp.business.usecase.impl.parqueaderos.celdas;

import co.com.park.gp.business.assembler.entity.impl.comunes.TipoVehiculoAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.parqueaderos.SedeAssemblerEntity;
import co.com.park.gp.business.domain.parqueaderos.CeldaDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.parqueaderos.CeldaEntity;

public class ModificarCelda implements UseCaseWithoutReturn<CeldaDomain> {

    private final DAOFactory factory;

    public ModificarCelda(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar una modificacion de la celda...";
            var mensajeTecnico = "El DAO factory para modificar la celda llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public void execute(CeldaDomain data) {

        var celdaEntity = CeldaEntity.build().setId(data.getId()).setSede(SedeAssemblerEntity.getInstance().toEntity(data.getSede()))
                .setTipoVehiculo(TipoVehiculoAssemblerEntity.getInstance().toEntity(data.getTipoVehiculo()))
                .setCantidadCeldas(data.getCantidadCeldas());

        factory.getCeldaDao().modificar(celdaEntity);

    }
}

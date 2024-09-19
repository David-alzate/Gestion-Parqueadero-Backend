package co.com.park.gp.business.usecase.impl.parqueaderos.celdas;

import co.com.park.gp.business.assembler.entity.impl.comunes.TipoVehiculoAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.parqueaderos.SedeAssemblerEntity;
import co.com.park.gp.business.domain.parqueaderos.CeldaDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.comunes.TipoVehiculoEntity;
import co.com.park.gp.entity.parqueaderos.CeldaEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;

import java.util.UUID;

public class RegistrarCelda implements UseCaseWithoutReturn<CeldaDomain> {

    private final DAOFactory factory;

    public RegistrarCelda(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar una creacion de celda...";
            var mensajeTecnico = "El DAO factory para crear la celda llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }


    @Override
    public void execute(CeldaDomain data) {

        verificarCeldaExiste(data.getSede().getId(), data.getTipoVehiculo().getId());
        
        var celdaEntity = CeldaEntity.build().setId(generarIdentificadorCelda()).setSede(SedeAssemblerEntity.getInstance().toEntity(data.getSede()))
                .setTipoVehiculo(TipoVehiculoAssemblerEntity.getInstance().toEntity(data.getTipoVehiculo()))
                .setCantidadCeldas(data.getCantidadCeldas());

        factory.getCeldaDao().crear(celdaEntity);
    }

    private final UUID generarIdentificadorCelda() {
        UUID id = UUIDHelper.generate();
        boolean existeId = true;

        while (existeId) {
            id = UUIDHelper.generate();
            var celdaEntity = CeldaEntity.build().setId(id);
            var resultados = factory.getCeldaDao().consultar(celdaEntity);
            existeId = !resultados.isEmpty();
        }
        return id;
    }

    private void verificarCeldaExiste(UUID idSede, UUID idTipoVehiculo){
        var celdaEntity = CeldaEntity.build().setSede(SedeEntity.build().setId(idSede)).setTipoVehiculo(TipoVehiculoEntity.build().setId(idTipoVehiculo));

        var resultadosEntity = factory.getCeldaDao().consultar(celdaEntity);

        if(!resultadosEntity.isEmpty()){
            var mensajeUsuario = "La cantidad de celdas para esta sede y tipo de vehiculo ya fue asignada";
            throw new BusinessGPException(mensajeUsuario);
        }
    }
}

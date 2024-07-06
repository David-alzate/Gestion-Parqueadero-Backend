package co.com.park.gp.business.usecase.impl.tarifas.tarifa;

import co.com.park.gp.business.assembler.entity.impl.comunes.TipoVehiculoAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.parqueaderos.SedeAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.tarifas.EstadoAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.tarifas.TipoTarifaAssemblerEntity;
import co.com.park.gp.business.domain.tarifas.TarifaDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.tarifas.TarifaEntity;

import java.util.UUID;

public class RegistrarTarifa implements UseCaseWithoutReturn<TarifaDomain> {

    private final DAOFactory factory;

    public RegistrarTarifa(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar un registro de la tarifa...";
            var mensajeTecnico = "El DAO factory para crear la tarifa lleno nulo..";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public void execute(TarifaDomain data) {

        var tarifaEntity = TarifaEntity.build().setId(generarIdentificadorTarifa())
                .setSede(SedeAssemblerEntity.getInstance().toEntity(data.getSede()))
                .setTipoVehiculo(TipoVehiculoAssemblerEntity.getInstance().toEntity(data.getTipoVehiculo()))
                .setTipoTarifa(TipoTarifaAssemblerEntity.getInstance().toEntity(data.getTipoTarifa()))
                .setTarifa(data.getTarifa())
                .setEstado(EstadoAssemblerEntity.getInstance().toEntity(data.getEstado()))
                .setFechaInicioVigencia(data.getFechaInicioVigencia())
                .setFechaFinVigencia(data.getFechaFinVigencia());

        factory.getTarifaDAO().crear(tarifaEntity);
    }

    private final UUID generarIdentificadorTarifa() {
        UUID id = UUIDHelper.generate();
        boolean existeId = true;

        while (existeId) {
            id = UUIDHelper.generate();
            var tarifaEntity = TarifaEntity.build().setId(id);
            var resultados = factory.getTarifaDAO().consultar(tarifaEntity);
            existeId = !resultados.isEmpty();
        }
        return id;
    }
}

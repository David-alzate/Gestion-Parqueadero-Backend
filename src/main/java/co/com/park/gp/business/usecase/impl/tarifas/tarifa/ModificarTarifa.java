package co.com.park.gp.business.usecase.impl.tarifas.tarifa;

import co.com.park.gp.business.assembler.entity.impl.comunes.TipoVehiculoAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.parqueaderos.SedeAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.tarifas.EstadoAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.tarifas.TipoTarifaAssemblerEntity;
import co.com.park.gp.business.domain.tarifas.TarifaDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.comunes.TipoVehiculoEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;
import co.com.park.gp.entity.tarifas.TarifaEntity;
import co.com.park.gp.entity.tarifas.TipoTarifaEntity;

import java.util.UUID;

public class ModificarTarifa implements UseCaseWithoutReturn<TarifaDomain> {

    private final DAOFactory factory;

    public ModificarTarifa(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar una modificacion de la tarifa...";
            var mensajeTecnico = "El DAO factory para modificar la tarifa llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public void execute(TarifaDomain data) {

        tarifaExiste(data.getSede().getId(), data.getTipoVehiculo().getId(), data.getTipoTarifa().getId(), data.getEstado().getEstado(), data.getId());

        var tarifaEntity = TarifaEntity.build().setId(data.getId())
                .setSede(SedeAssemblerEntity.getInstance().toEntity(data.getSede()))
                .setTipoVehiculo(TipoVehiculoAssemblerEntity.getInstance().toEntity(data.getTipoVehiculo()))
                .setTipoTarifa(TipoTarifaAssemblerEntity.getInstance().toEntity(data.getTipoTarifa()))
                .setTarifa(data.getTarifa())
                .setEstado(EstadoAssemblerEntity.getInstance().toEntity(data.getEstado()))
                .setFechaInicioVigencia(data.getFechaInicioVigencia())
                .setFechaFinVigencia(data.getFechaFinVigencia());

        factory.getTarifaDAO().modificar(tarifaEntity);
    }

    private void tarifaExiste(final UUID idSede, final UUID idTipoVehiculo, final UUID idTipoTarifa, final String estado, final UUID idTarifaActual) {
        var tarifaEntity = TarifaEntity.build()
                .setSede(SedeEntity.build().setId(idSede))
                .setTipoVehiculo(TipoVehiculoEntity.build().setId(idTipoVehiculo))
                .setTipoTarifa(TipoTarifaEntity.build().setId(idTipoTarifa));

        var resultados = factory.getTarifaDAO().consultar(tarifaEntity);

        boolean existeDuplicado = resultados.stream()
                .anyMatch(tarifa -> !tarifa.getId().equals(idTarifaActual) && tarifa.getEstado().getEstado().equals(estado));

        if (existeDuplicado && estado.equals("Activa")) {
            var mensajeUsuario = "Ya existe una tarifa activa para esa sede con este tipo de vehículo";
            throw new BusinessGPException(mensajeUsuario);
        }
    }
}

package co.com.park.gp.business.usecase.impl.sesionesparqueo.sesionparqueo;

import co.com.park.gp.business.assembler.entity.impl.tarifas.EstadoAssemblerEntity;
import co.com.park.gp.business.domain.sesionparqueo.SesionParqueoDomain;
import co.com.park.gp.business.domain.tarifas.EstadoDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.sesionesparqueo.SesionParqueoEntity;
import co.com.park.gp.entity.tarifas.EstadoEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class SalidaVehiculo implements UseCaseWithoutReturn<SesionParqueoDomain> {

    private final DAOFactory factory;

    public SalidaVehiculo(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la salida del vehiculo...";
            var mensajeTecnico = "El dao factory para la salida del vehiculo llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public void execute(SesionParqueoDomain data) {

        var idSesion = SesionParqueoEntity.build().setPlaca(data.getPlaca()).setEstado(EstadoEntity.build()
                .setId(UUIDHelper.convertToUUID("22f1f1ea-e5a6-4a57-9912-ada1b7372657")));

        var resultados = factory.getSesionParqueoDAO().consultar(idSesion);
        UUID id = null;
        id = resultados.getFirst().getId();
        var sesionParqueoEntity = SesionParqueoEntity.build().setId(id)
                .setFechaHoraSalida(LocalDateTime.now().withSecond(0).withNano(0))
                .setEstado(EstadoEntity.build().setId(UUIDHelper.convertToUUID("b266b1d6-e5e7-438d-ada6-e269fa896b94")));

        factory.getSesionParqueoDAO().salidaVehiculo(sesionParqueoEntity);

    }
}

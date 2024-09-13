package co.com.park.gp.business.usecase.impl.sesionesparqueo.sesionparqueo;

import co.com.park.gp.business.domain.sesionparqueo.SesionParqueoDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.enums.EstadoEnum;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
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

        validarSesionExiste(data.getPlaca());

        var estadoActivo = factory.getEstadoDAO().consultarPorDescripcion(EstadoEnum.INACTIVO.getNombre());

        var sesionParqueoEntity = SesionParqueoEntity.build().setId(idSesionParqueo(data.getPlaca()))
                .setFechaHoraSalida(LocalDateTime.now().withSecond(0).withNano(0))
                .setEstado(EstadoEntity.build().setId(estadoActivo.getId()));

        factory.getSesionParqueoDAO().salidaVehiculo(sesionParqueoEntity);

    }

    private UUID idSesionParqueo(String placa) {

        var estadoActivo = factory.getEstadoDAO().consultarPorDescripcion(EstadoEnum.ACTIVO.getNombre());

        var idSesion = SesionParqueoEntity.build().setPlaca(placa.toUpperCase()).setEstado(EstadoEntity.build()
                .setId(estadoActivo.getId()));

        var resultados = factory.getSesionParqueoDAO().consultar(idSesion);
        return resultados.getFirst().getId();
    }

    private void validarSesionExiste(String placa){

        var estadoActivo = factory.getEstadoDAO().consultarPorDescripcion(EstadoEnum.ACTIVO.getNombre());

        var idSesion = SesionParqueoEntity.build().setPlaca(placa.toUpperCase()).setEstado(EstadoEntity.build()
                .setId(estadoActivo.getId()));

        var resultados = factory.getSesionParqueoDAO().consultar(idSesion);

        if (resultados.isEmpty()){
            var mensajeUsuario = "El vehiculo no cuenta con una sesion de parqueo Activa";
            throw new BusinessGPException(mensajeUsuario);
        }

    }
}

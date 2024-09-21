package co.com.park.gp.business.usecase.impl.sesionesparqueo.sesionparqueo;

import co.com.park.gp.business.domain.sesionparqueo.SesionParqueoDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.enums.EstadoEnum;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.comunes.TipoVehiculoEntity;
import co.com.park.gp.entity.facturacion.FacturacionEntitiy;
import co.com.park.gp.entity.facturacion.MetodoPagoEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;
import co.com.park.gp.entity.sesionesparqueo.SesionParqueoEntity;
import co.com.park.gp.entity.tarifas.EstadoEntity;
import co.com.park.gp.entity.tarifas.TarifaEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
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

        var sesionParqueoEntity = SesionParqueoEntity.build().setId(idSesionParqueo(data.getPlaca()).getId())
                .setFechaHoraSalida(LocalDateTime.now().withSecond(0).withNano(0))
                .setEstado(EstadoEntity.build().setId(estadoActivo.getId()));


        // Obtener Datos para la facturacion
        BigDecimal tarifa = BigDecimal.valueOf(objetenerTarifa(idSesionParqueo(data.getPlaca()).getSede().getId(), idSesionParqueo(data.getPlaca()).getTipoVehiculo().getId()).getTarifa());
        var fechaHoraIngreso = idSesionParqueo(data.getPlaca()).getFechaHoraIngreso();
        var duracionSesion = Duration.between(fechaHoraIngreso, LocalDateTime.now().withSecond(0).withNano(0));
        long minutosTotales = duracionSesion.toMinutes();
        BigDecimal horasProporcionales = BigDecimal.valueOf(minutosTotales).divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);
        BigDecimal valorPagar = tarifa.multiply(horasProporcionales);

        var facturacionEntity = FacturacionEntitiy.build().setId(generarIdentificadorFactura())
                .setSesionParqueo(SesionParqueoEntity.build().setId(idSesionParqueo(data.getPlaca()).getId()))
                .setTarifa(TarifaEntity.build().setId(objetenerTarifa(data.getSede().getId(), data.getTipoVehiculo().getId()).getId()))
                .setMetodoPago(MetodoPagoEntity.build().setId(UUIDHelper.convertToUUID("f47ac10b-58cc-4372-a567-0e02b2c3d479")))
                .setDuracion(duracionSesion)
                .setValorPagar(valorPagar);

        factory.getSesionParqueoDAO().salidaVehiculo(sesionParqueoEntity);
        factory.getFacturacionDao().crear(facturacionEntity);
    }


    private SesionParqueoEntity idSesionParqueo(String placa) {

        var estadoActivo = factory.getEstadoDAO().consultarPorDescripcion(EstadoEnum.ACTIVO.getNombre());

        var idSesion = SesionParqueoEntity.build().setPlaca(placa.toUpperCase()).setEstado(EstadoEntity.build()
                .setId(estadoActivo.getId()));

        var resultados = factory.getSesionParqueoDAO().consultar(idSesion);
        return resultados.get(0);
    }

    private TarifaEntity objetenerTarifa(UUID idSede, UUID idTipoVehiculo) {

        var tarifa = TarifaEntity.build().setSede(SedeEntity.build().setId(idSede))
                .setTipoVehiculo(TipoVehiculoEntity.build().setId(idTipoVehiculo));

        var resultados = factory.getTarifaDAO().consultar(tarifa);
        return resultados.get(0);
    }

    private UUID generarIdentificadorFactura() {
        UUID id = UUIDHelper.generate();
        boolean existeId = true;

        while (existeId) {
            id = UUIDHelper.generate();
            var facturaEntity = FacturacionEntitiy.build().setId(id);
            var resultados = factory.getFacturacionDao().consultar(facturaEntity);
            existeId = !resultados.isEmpty();
        }
        return id;
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

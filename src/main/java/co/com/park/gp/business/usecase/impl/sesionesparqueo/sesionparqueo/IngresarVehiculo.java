package co.com.park.gp.business.usecase.impl.sesionesparqueo.sesionparqueo;

import co.com.park.gp.business.assembler.entity.impl.empleados.EmpleadoAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.parqueaderos.SedeAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.tarifas.EstadoAssemblerEntity;
import co.com.park.gp.business.domain.sesionparqueo.SesionParqueoDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.sesionesparqueo.SesionParqueoEntity;
import co.com.park.gp.entity.tarifas.EstadoEntity;

import java.util.UUID;

public class IngresarVehiculo implements UseCaseWithoutReturn<SesionParqueoDomain> {

    private final DAOFactory factory;

    public IngresarVehiculo(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo el Ingreso del vehiculo...";
            var mensajeTecnico = "El dao factory para el Ingreso del vehiculo llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public void execute(SesionParqueoDomain data) {

        validarMismoVehiculoEstadoActivo(data.getPlaca());

        var sesionParqueoEnity = SesionParqueoEntity.build().setId(generarIdentificadorSesionParqueo())
                .setSede(SedeAssemblerEntity.getInstance().toEntity(data.getSede()))
                .setEmpleado(EmpleadoAssemblerEntity.getInstance().toEntity(data.getEmpleado()))
                .setPlaca(data.getPlaca())
                .setEstado(EstadoAssemblerEntity.getInstance().toEntity(data.getEstado()))
                .setFechaHoraIngreso(data.getFechaHoraIngreso());

        factory.getSesionParqueoDAO().ingresarVehiculo(sesionParqueoEnity);

    }

    private UUID generarIdentificadorSesionParqueo() {
        UUID id = UUIDHelper.generate();
        boolean existeId = true;

        while (existeId) {
            id = UUIDHelper.generate();
            var sesionParqueoEntity = SesionParqueoEntity.build().setId(id);
            var resultados = factory.getSesionParqueoDAO().consultar(sesionParqueoEntity);
            existeId = !resultados.isEmpty();
        }
        return id;
    }

    private void validarMismoVehiculoEstadoActivo(String placa){
        var sesionParqueoEntity = SesionParqueoEntity.build().setPlaca(placa)
                .setEstado(EstadoEntity.build().setId(UUIDHelper.convertToUUID("22f1f1ea-e5a6-4a57-9912-ada1b7372657")));

        var resultados = factory.getSesionParqueoDAO().consultar(sesionParqueoEntity);

        if (!resultados.isEmpty()){
            var mensajeUsuario = "El vehiculo ya cuenta con una sesion de parqueo Activa";
            throw new BusinessGPException(mensajeUsuario);
        }
    }
}

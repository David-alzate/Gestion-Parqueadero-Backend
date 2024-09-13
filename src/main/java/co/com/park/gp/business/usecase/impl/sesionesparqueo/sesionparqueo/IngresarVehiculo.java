package co.com.park.gp.business.usecase.impl.sesionesparqueo.sesionparqueo;

import co.com.park.gp.business.assembler.entity.impl.comunes.TipoVehiculoAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.empleados.EmpleadoAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.parqueaderos.SedeAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.tarifas.EstadoAssemblerEntity;
import co.com.park.gp.business.domain.sesionparqueo.SesionParqueoDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.enums.EstadoEnum;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.comunes.TipoVehiculoEntity;
import co.com.park.gp.entity.parqueaderos.CeldaEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;
import co.com.park.gp.entity.planes.PlanEntity;
import co.com.park.gp.entity.sesionesparqueo.SesionParqueoEntity;
import co.com.park.gp.entity.tarifas.EstadoEntity;
import co.com.park.gp.entity.vehiculos.VehiculoEntity;

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

        validarConfiguracionSedeCelda(data.getSede().getId(), data.getTipoVehiculo().getId());
        validarVehiculoConPlan(data.getPlaca(), data.getSede().getId());
        validarMismoVehiculoEstadoActivo(data.getPlaca());

        var sesionParqueoEnity = SesionParqueoEntity.build().setId(generarIdentificadorSesionParqueo())
                .setSede(SedeAssemblerEntity.getInstance().toEntity(data.getSede()))
                .setEmpleado(EmpleadoAssemblerEntity.getInstance().toEntity(data.getEmpleado()))
                .setPlaca(data.getPlaca()).setTipoVehiculo(TipoVehiculoAssemblerEntity.getInstance().toEntity(data.getTipoVehiculo()))
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
        var estadoActivo = factory.getEstadoDAO().consultarPorDescripcion(EstadoEnum.ACTIVO.getNombre());

        var sesionParqueoEntity = SesionParqueoEntity.build()
                .setPlaca(placa)
                .setEstado(EstadoEntity.build().setId(estadoActivo.getId()));

        var resultados = factory.getSesionParqueoDAO().consultar(sesionParqueoEntity);

        if (!resultados.isEmpty()){
            var mensajeUsuario = "El vehiculo ya cuenta con una sesion de parqueo Activa";
            throw new BusinessGPException(mensajeUsuario);
        }
    }


    private void validarVehiculoConPlan(String placa, UUID idSede) {
        var planEntity = PlanEntity.build().setVehiculo(VehiculoEntity.build().setPlaca(placa.toUpperCase())).setSede(SedeEntity.build().setId(idSede));

        var resultados = factory.getPlanDAO().consultar(planEntity);

        if(!resultados.isEmpty()){
            var mensajeUsuario = "El vehiculo Cuenta con un plan activo detro de la sede";
            throw new BusinessGPException(mensajeUsuario);
        }
    }

    private void validarConfiguracionSedeCelda(UUID idSede, UUID idTipoVehiculo) {
        var celdaEntity = CeldaEntity.build().setSede(SedeEntity.build().setId(idSede)).setTipoVehiculo(TipoVehiculoEntity.build().setId(idTipoVehiculo));

        var resultados = factory.getCeldaDao().consultar(celdaEntity);

        if(resultados.isEmpty()){
            var mensajeUsuario = "Antes de ingresar el vehiculo, configure la cantidad de celdas para este sede y este tipo de vehiculo";
            throw new BusinessGPException(mensajeUsuario);
        }

    }
}

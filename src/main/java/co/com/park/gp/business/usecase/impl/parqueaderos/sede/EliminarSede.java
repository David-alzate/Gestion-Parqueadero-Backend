package co.com.park.gp.business.usecase.impl.parqueaderos.sede;

import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.empleados.EmpleadoEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;
import co.com.park.gp.entity.tarifas.TarifaEntity;

import java.util.UUID;

public class EliminarSede implements UseCaseWithoutReturn<UUID> {

    private final DAOFactory factory;

    public EliminarSede(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar una Eliminacion de la sede...";
            var mensajeTecnico = "El DAO factory para eliminar la sede llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public void execute(UUID id) {

        validarSedeAsignadoEmpleado(id);
        validarSedeAsignadoTarifa(id);

        factory.getSedeDAO().eliminar(id);

    }

    void validarSedeAsignadoTarifa(UUID id) {
        var tarifaEntity = TarifaEntity.build().setSede(SedeEntity.build().setId(id));
        var resultados = factory.getTarifaDAO().consultar(tarifaEntity);

        if (!resultados.isEmpty()) {
            var mensajeUsuario = "La Sede esta asignado a una Tarifa";
            throw new BusinessGPException(mensajeUsuario);
        }
    }

    void validarSedeAsignadoEmpleado(UUID id) {
        var empleadoEntity = EmpleadoEntity.build().setSede(SedeEntity.build().setId(id));
        var resultados = factory.getEmpleadoDAO().consultar(empleadoEntity);

        if (!resultados.isEmpty()) {
            var mensajeUsuario = "La Sede esta asignado a un Empleado";
            throw new BusinessGPException(mensajeUsuario);
        }
    }
}

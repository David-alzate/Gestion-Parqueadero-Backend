package co.com.park.gp.business.usecase.impl.empleados.empleado;

import co.com.park.gp.business.assembler.entity.impl.empleados.EmpleadoAssemblerEntity;
import co.com.park.gp.business.domain.empleados.EmpleadoDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.enums.TipoEmpleadoEnum;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.empleados.TipoEmpleadoEntity;

import java.util.List;

public class ConsultarEmpleadoPorRolEmpleado implements UseCaseWithReturn<EmpleadoDomain, List<EmpleadoDomain>> {

    private final DAOFactory factory;

    public ConsultarEmpleadoPorRolEmpleado(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la Consulta de los empleados con rol de empleado...";
            var mensajeTecnico = "El dao factory para consultar los empleados con rol de empleado llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public List<EmpleadoDomain> execute(EmpleadoDomain data) {
        var empleadoFilter = EmpleadoAssemblerEntity.getInstance().toEntity(data);
        var empleado = empleadoFilter.setTipoEmpleado(TipoEmpleadoEntity.build().setNombre(TipoEmpleadoEnum.EMPLEADO.getRol()));
        var resultadosEntity = factory.getEmpleadoDAO().consultar(empleado);

        return EmpleadoAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
    }
}

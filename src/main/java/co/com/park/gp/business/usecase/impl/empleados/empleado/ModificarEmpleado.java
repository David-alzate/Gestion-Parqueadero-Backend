package co.com.park.gp.business.usecase.impl.empleados.empleado;

import co.com.park.gp.business.assembler.entity.impl.parqueaderos.SedeAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.empleados.TipoEmpleadoAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.comunes.TipoIdentificacionAssemblerEntity;
import co.com.park.gp.business.domain.empleados.EmpleadoDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.empleados.EmpleadoEntity;

public class ModificarEmpleado implements UseCaseWithoutReturn<EmpleadoDomain> {

    private final DAOFactory factory;

    public ModificarEmpleado(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00082);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00083);
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public void execute(EmpleadoDomain data) {

        var empleadoEntity = EmpleadoEntity.build().setId(data.getId())
                .setTipoIdentificacion(
                        TipoIdentificacionAssemblerEntity.getInstance().toEntity(data.getTipoIdentificacion()))
                .setNumeroIdentificacion(data.getNumeroIdentificacion()).setNombre(data.getNombre())
                .setApellido(data.getApellido()).setCorreoElectronico(data.getCorreoElectronico())
                .setTipoEmpleado(TipoEmpleadoAssemblerEntity.getInstance().toEntity(data.getTipoEmpleado()))
                .setSede(SedeAssemblerEntity.getInstance().toEntity(data.getSede())).setPassword(data.getPassword());

        factory.getEmpleadoDAO().modificar(empleadoEntity);
    }
}

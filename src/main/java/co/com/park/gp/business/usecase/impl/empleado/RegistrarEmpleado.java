package co.com.park.gp.business.usecase.impl.empleado;

import java.util.UUID;

import co.com.park.gp.business.assembler.entity.impl.SedeAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.TipoEmpleadoAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.TipoIdentificacionAssemblerEntity;
import co.com.park.gp.business.domain.EmpleadoDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.EmpleadoEntity;

public class RegistrarEmpleado implements UseCaseWithoutReturn<EmpleadoDomain> {

    private final DAOFactory factory;

    public RegistrarEmpleado(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00082);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00083);
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public void execute(EmpleadoDomain data) {

        var empleadoEntity = EmpleadoEntity.build().setId(generarIdentificadorEmpleado())
                .setTipoIdentificacion(
                        TipoIdentificacionAssemblerEntity.getInstance().toEntity(data.getTipoIdentificacion()))
                .setNumeroIdentificacion(data.getNumeroIdentificacion()).setNombre(data.getNombre())
                .setApellido(data.getApellido()).setCorreoElectronico(data.getCorreoElectronico())
                .setTipoEmpleado(TipoEmpleadoAssemblerEntity.getInstance().toEntity(data.getTipoEmpleado()))
                .setSede(SedeAssemblerEntity.getInstance().toEntity(data.getSede())).setPassword(data.getPassword());

        factory.getEmpleadoDAO().crear(empleadoEntity);

    }

    private final UUID generarIdentificadorEmpleado() {
        UUID id = UUIDHelper.generate();
        boolean existeId = true;

        while (existeId) {
            id = UUIDHelper.generate();
            var empleadoEntity = EmpleadoEntity.build().setId(id);
            var resultados = factory.getEmpleadoDAO().consultar(empleadoEntity);
            existeId = !resultados.isEmpty();
        }
        return id;
    }

}

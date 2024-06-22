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
import co.com.park.gp.crosscutting.helpers.TextHelper;
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

        validarNombre(data.getNombre());
        validarApellido(data.getApellido());
        validarNumeroIdentificacion(data.getNumeroIdentificacion());
        validarEmail(data.getCorreoElectronico());

        var empleadoEntity = EmpleadoEntity.build().setId(generarIdentificadorEmpleado())
                .setTipoIdentificacion(
                        TipoIdentificacionAssemblerEntity.getInstance().toEntity(data.getTipoIdentificacion()))
                .setNumeroIdentificacion(data.getNumeroIdentificacion()).setNombre(data.getNombre())
                .setApellido(data.getApellido()).setCorreoElectronico(data.getCorreoElectronico())
                .setTipoEmpleado(TipoEmpleadoAssemblerEntity.getInstance().toEntity(data.getTipoEmpleado()))
                .setSede(SedeAssemblerEntity.getInstance().toEntity(data.getSede())).setPassword(data.getPassword());

        factory.getEmpleadoDAO().crear(empleadoEntity);

    }

    private UUID generarIdentificadorEmpleado() {
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

    private void validarNombre(String nombre) {
        if (TextHelper.isNullOrEmpty(nombre)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00099);
            throw new BusinessGPException(mensajeUsuario);
        }

        if (nombre.length() < 2) {
            var mensajeUsuario = TextHelper.reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00100), nombre);
            throw new BusinessGPException(mensajeUsuario);
        }

        if (nombre.length() > 40) {
            var mensajeUsuario = TextHelper.reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00101), nombre);
            throw new BusinessGPException(mensajeUsuario);
        }
    }

    private void validarApellido(String apellido) {
        if (TextHelper.isNullOrEmpty(apellido)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00102);
            throw new BusinessGPException(mensajeUsuario);
        }

        if (apellido.length() < 2) {
            var mensajeUsuario = TextHelper.reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00103), apellido);
            throw new BusinessGPException(mensajeUsuario);
        }

        if (apellido.length() > 40) {
            var mensajeUsuario = TextHelper.reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00104), apellido);
            throw new BusinessGPException(mensajeUsuario);
        }
    }

    private void validarNumeroIdentificacion(Long numeroIdentificacion) {
        if (numeroIdentificacion == 0) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00105);
            throw new BusinessGPException(mensajeUsuario);
        }
    }

    private void validarEmail(String correoElectronico) {

        if (TextHelper.isNullOrEmpty(correoElectronico)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00106);
            throw new BusinessGPException(mensajeUsuario);
        }

        if (!(TextHelper.emailValido(correoElectronico))) {
            var mensajeUsuario = TextHelper.reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00107), correoElectronico);
            throw new BusinessGPException(mensajeUsuario);
        }
    }

}

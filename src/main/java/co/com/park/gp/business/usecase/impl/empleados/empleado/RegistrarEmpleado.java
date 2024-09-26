package co.com.park.gp.business.usecase.impl.empleados.empleado;

import java.util.UUID;

import co.com.park.gp.business.assembler.entity.impl.parqueaderos.SedeAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.empleados.TipoEmpleadoAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.comunes.TipoIdentificacionAssemblerEntity;
import co.com.park.gp.business.domain.empleados.EmpleadoDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.enums.EstadoEnum;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.empleados.EmpleadoEntity;
import co.com.park.gp.entity.empleados.TipoEmpleadoEntity;
import co.com.park.gp.entity.tarifas.EstadoEntity;

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
        validarMismoNumeroIdentificacionMismoTipoEmpleado(data.getNumeroIdentificacion(), data.getTipoEmpleado().getNombre());
        validarMismoCorreoMismoTipoEmpleado(data.getCorreoElectronico(), data.getTipoEmpleado().getNombre());
        
        var estadoActivo = factory.getEstadoDAO().consultarPorDescripcion(EstadoEnum.ACTIVO.getNombre());

        var empleadoEntity = EmpleadoEntity.build().setId(generarIdentificadorEmpleado())
                .setTipoIdentificacion(
                        TipoIdentificacionAssemblerEntity.getInstance().toEntity(data.getTipoIdentificacion()))
                .setNumeroIdentificacion(data.getNumeroIdentificacion()).setNombre(data.getNombre())
                .setApellido(data.getApellido()).setCorreoElectronico(data.getCorreoElectronico())
                .setTipoEmpleado(TipoEmpleadoAssemblerEntity.getInstance().toEntity(data.getTipoEmpleado()))
                .setSede(SedeAssemblerEntity.getInstance().toEntity(data.getSede())).setEstado(EstadoEntity.build().setId(estadoActivo.getId()))
                .setPassword(TextHelper.hashPassword(data.getPassword()));

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

    private void validarMismoNumeroIdentificacionMismoTipoEmpleado(Long numeroIdentificacion, String tipoEmpleado) {
        var empleadoEntity = EmpleadoEntity.build().setNumeroIdentificacion(numeroIdentificacion).setTipoEmpleado(TipoEmpleadoEntity.build().setNombre(tipoEmpleado));
        var resultados = factory.getEmpleadoDAO().consultar(empleadoEntity);

        if (!resultados.isEmpty()) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00108);
            throw new BusinessGPException(mensajeUsuario);
        }
    }

    private void validarMismoCorreoMismoTipoEmpleado(String correoElectronico, String tipoEmpleado) {
        var empleadoEntity = EmpleadoEntity.build().setCorreoElectronico(correoElectronico).setTipoEmpleado(TipoEmpleadoEntity.build().setNombre(tipoEmpleado));
        var resultados = factory.getEmpleadoDAO().consultar(empleadoEntity);

        if (!resultados.isEmpty()) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00109);
            throw new BusinessGPException(mensajeUsuario);
        }
    }

}

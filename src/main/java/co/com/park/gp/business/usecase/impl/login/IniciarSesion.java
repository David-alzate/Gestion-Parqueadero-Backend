package co.com.park.gp.business.usecase.impl.login;

import co.com.park.gp.business.assembler.entity.impl.empleados.TipoEmpleadoAssemblerEntity;
import co.com.park.gp.business.domain.login.LoginDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.empleados.EmpleadoEntity;
import co.com.park.gp.entity.empleados.TipoEmpleadoEntity;

public class IniciarSesion implements UseCaseWithReturn<LoginDomain, LoginDomain> {

    private final DAOFactory factory;

    public IniciarSesion(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00078);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00079);
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public LoginDomain execute(LoginDomain data) {
        validarPassword(data.getPassword());
        validarNumeroIdentificacion(data.getNumeroIdentificacion());

        var empleado = validarUsuario(data.getNumeroIdentificacion(), data.getPassword(), data.getTipoEmpleado().getNombre());
        
        var tipoEmpleado = TipoEmpleadoAssemblerEntity.getInstance().toDomain(empleado.getTipoEmpleado());
        data.setTipoEmpleado(tipoEmpleado);
        
        return data;
    }

    private EmpleadoEntity validarUsuario(final Long numeroIdentificacion, final String password, String tipoEmpleado) {
        var empleadoEntity = EmpleadoEntity.build()
            .setNumeroIdentificacion(numeroIdentificacion)
            .setTipoEmpleado(TipoEmpleadoEntity.build().setNombre(tipoEmpleado));
        var resultados = factory.getEmpleadoDAO().consultar(empleadoEntity);

        if (resultados.isEmpty()) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00080);
            throw new BusinessGPException(mensajeUsuario);
        }

        var empleado = resultados.getFirst();
        var storedPassword = empleado.getPassword();

        boolean esHash = storedPassword.length() > 30;

        if (esHash) {
            if (!TextHelper.checkPassword(password, storedPassword)) {
                var mensajeUsuario = "Contraseña incorrecta";
                throw new BusinessGPException(mensajeUsuario);
            }
        } else {
            if (!password.equals(storedPassword)) {
                var mensajeUsuario = "Contraseña incorrecta";
                throw new BusinessGPException(mensajeUsuario);
            }
        }

        return empleado;
    }

    private void validarNumeroIdentificacion(final Long numeroIdentificacion){
        if (numeroIdentificacion == 0){
            var mensajeUsuario = "El numero de identificacion no puede estar vacio";
            throw new BusinessGPException(mensajeUsuario);
        }
    }

    private void validarPassword(final String password){
        if (TextHelper.isNullOrEmpty(password)){
            var mensajeUsuario = "La contraseña no puede estar vacio";
            throw new BusinessGPException(mensajeUsuario);
        }
    }
}
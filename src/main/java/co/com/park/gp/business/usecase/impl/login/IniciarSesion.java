package co.com.park.gp.business.usecase.impl.login;

import co.com.park.gp.business.domain.login.LoginDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.empleados.EmpleadoEntity;
import co.com.park.gp.entity.empleados.TipoEmpleadoEntity;

public class IniciarSesion implements UseCaseWithReturn<LoginDomain, Boolean> {

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
    public Boolean execute(LoginDomain data) {
        validarPassword(data.getPassword());
        validarNumeroIdentificacion(data.getNumeroIdentificacion());
        validarUsuario(data.getNumeroIdentificacion(), data.getPassword(), data.getTipoEmpleado().getNombre());
        return true;
    }

    private void validarUsuario(final Long numeroIdentificacion, final String password, String tipoEmpleado) {
        var empleadoEntity = EmpleadoEntity.build().setNumeroIdentificacion(numeroIdentificacion)
                .setPassword(password).setTipoEmpleado(TipoEmpleadoEntity.build().setNombre(tipoEmpleado));

        var resultados = factory.getEmpleadoDAO().consultar(empleadoEntity);

        if (resultados.isEmpty()) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00080);
            throw new BusinessGPException(mensajeUsuario);
        }
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

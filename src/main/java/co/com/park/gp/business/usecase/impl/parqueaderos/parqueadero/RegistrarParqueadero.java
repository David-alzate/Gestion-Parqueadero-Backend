package co.com.park.gp.business.usecase.impl.parqueaderos.parqueadero;

import java.util.UUID;

import co.com.park.gp.business.domain.parqueaderos.ParqueaderoDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.parqueaderos.ParqueaderoEntity;

public final class RegistrarParqueadero implements UseCaseWithoutReturn<ParqueaderoDomain> {

    private final DAOFactory factory;

    public RegistrarParqueadero(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00069);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00070);
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public void execute(final ParqueaderoDomain data) {

        validarParqueadero(data.getNombre());

        var parqueaderoEntity = ParqueaderoEntity.build().setId(generarIdentificadorSede()).setNombre(data.getNombre());

        factory.getParqueaderoDAO().crear(parqueaderoEntity);

    }

    private final UUID generarIdentificadorSede() {
        UUID id = UUIDHelper.generate();
        boolean existeId = true;

        while (existeId) {
            id = UUIDHelper.generate();
            var parqueaderoEntity = ParqueaderoEntity.build().setId(id);
            var resultados = factory.getParqueaderoDAO().consultar(parqueaderoEntity);
            existeId = !resultados.isEmpty();
        }
        return id;
    }

    private void validarParqueadero(final String nombreParqueadero) {

        if (TextHelper.isNullOrEmpty(nombreParqueadero)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessGPException(mensajeUsuario);
        }

        if (nombreParqueadero.length() < 2) {
            var mensajeUsuario = TextHelper.reemplazarParametro(
                    MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00076), nombreParqueadero);
            throw new BusinessGPException(mensajeUsuario);
        }

        if (nombreParqueadero.length() > 40) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00077);
            throw new BusinessGPException(mensajeUsuario);
        }

    }

}

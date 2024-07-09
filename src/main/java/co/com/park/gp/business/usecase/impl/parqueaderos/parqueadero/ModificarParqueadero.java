package co.com.park.gp.business.usecase.impl.parqueaderos.parqueadero;

import co.com.park.gp.business.domain.parqueaderos.ParqueaderoDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.parqueaderos.ParqueaderoEntity;

import java.util.UUID;

public class ModificarParqueadero implements UseCaseWithoutReturn<ParqueaderoDomain> {

    private final DAOFactory factory;

    public ModificarParqueadero(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar una modificacion del parqueadero...";
            var mensajeTecnico = "El DAO factory para modificar el parqueadero llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public void execute(ParqueaderoDomain data) {
        validarParqueadero(data.getNombre());
        validarParqueaderoExiste(data.getNombre(), data.getId());

        var parqueaderoEntity = ParqueaderoEntity.build()
                .setId(data.getId())
                .setNombre(data.getNombre());

        factory.getParqueaderoDAO().modificar(parqueaderoEntity);

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

    private void validarParqueaderoExiste(final String nombreParqueadero, final UUID idParqueaderoActual) {
        var parqueaderoEntity = ParqueaderoEntity.build().setNombre(nombreParqueadero);
        var resultados = factory.getParqueaderoDAO().consultar(parqueaderoEntity);

        boolean existeDuplicado = resultados.stream()
                .anyMatch(parqueadero -> !parqueadero.getId().equals(idParqueaderoActual));

        if (existeDuplicado) {
            var mensajeUsuario = TextHelper.reemplazarParametro(
                    MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00110), nombreParqueadero);
            throw new BusinessGPException(mensajeUsuario);
        }
    }
}

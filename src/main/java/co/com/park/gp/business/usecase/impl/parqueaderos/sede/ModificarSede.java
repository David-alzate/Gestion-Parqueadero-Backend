package co.com.park.gp.business.usecase.impl.parqueaderos.sede;

import co.com.park.gp.business.assembler.entity.impl.parqueaderos.*;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.parqueaderos.ParqueaderoEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;

import java.sql.ResultSet;
import java.util.UUID;

public class ModificarSede implements UseCaseWithoutReturn<SedeDomain> {

    private final DAOFactory factory;

    public ModificarSede(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar una modificacion de la sede...";
            var mensajeTecnico = "El DAO factory para modificar la sede llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public void execute(SedeDomain data) {

        validarSede(data.getNombre());
        validarFormatoCorreo(data.getCorreoElectronico());
        validarDireccion(data.getDireccion());
        validarCantidadCeldas(data.getCeldasCarro(), data.getCeldasMoto(), data.getCeldascamion());
        validarSedeMismoNombreMismoParqueadero(data.getNombre(), data.getParqueadero().getId(), data.getId());
        validarSedeMismaDireccionMismoParqueadero(data.getDireccion(), data.getParqueadero().getId(), data.getId());
        validarMismoCorreo(data.getCorreoElectronico(), data.getId());

        var sedeEntity = SedeEntity.build().setId(data.getId())
                .setParqueadero(ParqueaderoAssemblerEntity.getInstance().toEntity(data.getParqueadero()))
                .setNombre(data.getNombre()).setCiudad(CiudadAssemblerEntity.getInstance().toEntity(data.getCiudad()))
                .setDireccion(data.getDireccion()).setCorreoElectronico(data.getCorreoElectronico())
                .setCeldasCarro(data.getCeldasCarro()).setCeldasMoto(data.getCeldasMoto())
                .setCeldascamion(data.getCeldascamion())
                .setTipoSede(TipoSedeAssemblerEntity.getInstance().toEntity(data.getTipoSede()))
                .setPais(PaisAssemblerEntity.getInstance().toEntity(data.getPais()))
                .setDepartamento(DepartamentoAssemblerEntity.getInstance().toEntity(data.getDepartamento()));

        factory.getSedeDAO().modificar(sedeEntity);

    }

    private void validarSede(final String nombreSede) {
        if (TextHelper.isNullOrEmpty(nombreSede)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00057);
            throw new BusinessGPException(mensajeUsuario);
        }

        if (nombreSede.length() < 2) {
            var mensajeUsuario = TextHelper
                    .reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00065), nombreSede);
            throw new BusinessGPException(mensajeUsuario);
        }

        if (nombreSede.length() > 40) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00066);
            throw new BusinessGPException(mensajeUsuario);
        }

    }

    private void validarFormatoCorreo(final String correo) {
        if (TextHelper.isNullOrEmpty(correo)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00058);
            throw new BusinessGPException(mensajeUsuario);
        }

        if (!(TextHelper.emailValido(correo))) {
            var mensajeUsuario = TextHelper
                    .reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00059), correo);
            throw new BusinessGPException(mensajeUsuario);
        }
    }

    private void validarDireccion(final String direccion) {
        if (TextHelper.isNullOrEmpty(direccion)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00061);
            throw new BusinessGPException(mensajeUsuario);
        }

        if (direccion.length() < 5) {
            var mensajeUsuario = TextHelper
                    .reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00067), direccion);
            throw new BusinessGPException(mensajeUsuario);
        }

        if (direccion.length() > 100) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00068);
            throw new BusinessGPException(mensajeUsuario);
        }
    }

    private void validarCantidadCeldas(final int celdasCarro, final int celdasMoto, final int celdasCamion) {
        if (celdasCarro < 0) {
            var mensajeUsuario = TextHelper.reemplazarParametro(
                    MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00063), "celdasCarro");
            throw new BusinessGPException(mensajeUsuario);
        }

        if (celdasMoto < 0) {
            var mensajeUsuario = TextHelper.reemplazarParametro(
                    MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00063), "celdasMoto");
            throw new BusinessGPException(mensajeUsuario);
        }

        if (celdasCamion < 0) {
            var mensajeUsuario = TextHelper.reemplazarParametro(
                    MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00063), "celdasCamion");
            throw new BusinessGPException(mensajeUsuario);
        }

        if (celdasCarro + celdasMoto + celdasCamion == 0) {
            var mensajeUsuario = TextHelper.reemplazarParametro(
                    MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00064), "celdasCarro", "celdasMoto",
                    "celdasCamion");
            throw new BusinessGPException(mensajeUsuario);
        }
    }

    private void validarSedeMismoNombreMismoParqueadero(final String nombreSede, final UUID idParqueadero, final UUID idSedeActual) {
        var sedeEntity = SedeEntity.build()
                .setNombre(nombreSede)
                .setParqueadero(ParqueaderoEntity.build().setId(idParqueadero));

        var resultados = factory.getSedeDAO().consultar(sedeEntity);

        boolean existeDuplicado = resultados.stream()
                .anyMatch(sede -> !sede.getId().equals(idSedeActual));

        if (existeDuplicado) {
            var mensajeUsuario = TextHelper
                    .reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00056), nombreSede);
            throw new BusinessGPException(mensajeUsuario);
        }
    }

    private void validarSedeMismaDireccionMismoParqueadero(final String direccion, final UUID idparqueadero, final UUID idSedeActual) {
        var sedeEntity = SedeEntity.build().setDireccion(direccion)
                .setParqueadero(ParqueaderoEntity.build().setId(idparqueadero));

        var resultados = factory.getSedeDAO().consultar(sedeEntity);

        boolean existeDuplicado = resultados.stream()
                .anyMatch(sede -> !sede.getId().equals(idSedeActual));

        if (existeDuplicado) {
            var mensajeUsuario = TextHelper
                    .reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00062), direccion);
            throw new BusinessGPException(mensajeUsuario);
        }
    }

    private void validarMismoCorreo(final String correo, final UUID idSedeActual) {
        var sedeEntity = SedeEntity.build().setCorreoElectronico(correo);

        var resultados = factory.getSedeDAO().consultar(sedeEntity);

        boolean existeDuplicado = resultados.stream()
                .anyMatch(sede -> !sede.getId().equals(idSedeActual));

        if (existeDuplicado) {
            var mensajeUsuario = TextHelper
                    .reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00060), correo);
            throw new BusinessGPException(mensajeUsuario);
        }
    }
}

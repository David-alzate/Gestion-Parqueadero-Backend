package co.com.park.gp.business.usecase.impl.parqueaderos.sede;

import java.util.UUID;

import co.com.park.gp.business.assembler.entity.impl.parqueaderos.CiudadAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.parqueaderos.DepartamentoAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.parqueaderos.PaisAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.parqueaderos.ParqueaderoAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.parqueaderos.TipoSedeAssemblerEntity;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.parqueaderos.ParqueaderoEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;

public final class RegistrarSede implements UseCaseWithoutReturn<SedeDomain> {

    private final DAOFactory factory;

    public RegistrarSede(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00045);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00046);
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public void execute(final SedeDomain data) {

        validarSede(data.getNombre());

        validarFormatoCorreo(data.getCorreoElectronico());

        validarDireccion(data.getDireccion());

        validarSedeMismoNombreMismoParqueaero(data.getNombre(), data.getParqueadero().getId());

        validarMismoCorreo(data.getCorreoElectronico());

        validarSedeMismaDireccionMismoParqueadero(data.getDireccion(), data.getParqueadero().getId());

        var sedeEntity = SedeEntity.build().setId(generarIdentificadorSede())
                .setParqueadero(ParqueaderoAssemblerEntity.getInstance().toEntity(data.getParqueadero()))
                .setNombre(data.getNombre()).setCiudad(CiudadAssemblerEntity.getInstance().toEntity(data.getCiudad()))
                .setDireccion(data.getDireccion()).setCorreoElectronico(data.getCorreoElectronico())
                .setTipoSede(TipoSedeAssemblerEntity.getInstance().toEntity(data.getTipoSede()))
                .setPais(PaisAssemblerEntity.getInstance().toEntity(data.getPais()))
                .setDepartamento(DepartamentoAssemblerEntity.getInstance().toEntity(data.getDepartamento()));

        factory.getSedeDAO().crear(sedeEntity);
    }

    private final UUID generarIdentificadorSede() {
        UUID id = UUIDHelper.generate();
        boolean existeId = true;

        while (existeId) {
            id = UUIDHelper.generate();
            var sedeEntity = SedeEntity.build().setId(id);
            var resultados = factory.getSedeDAO().consultar(sedeEntity);
            existeId = !resultados.isEmpty();
        }
        return id;
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

    private void validarSedeMismoNombreMismoParqueaero(final String nombreSede, final UUID idParqueadero) {
        var sedeEntity = SedeEntity.build().setNombre(nombreSede)
                .setParqueadero(ParqueaderoEntity.build().setId(idParqueadero));

        var resultados = factory.getSedeDAO().consultar(sedeEntity);

        if (!resultados.isEmpty()) {
            var mensajeUsuario = TextHelper
                    .reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00056), nombreSede);
            throw new BusinessGPException(mensajeUsuario);
        }

    }

    private void validarSedeMismaDireccionMismoParqueadero(final String direccion, final UUID idparqueadero) {
        var sedeEntity = SedeEntity.build().setDireccion(direccion)
                .setParqueadero(ParqueaderoEntity.build().setId(idparqueadero));

        var resultados = factory.getSedeDAO().consultar(sedeEntity);

        if (!resultados.isEmpty()) {
            var mensajeUsuario = TextHelper
                    .reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00062), direccion);
            throw new BusinessGPException(mensajeUsuario);
        }
    }

    private void validarMismoCorreo(final String correo) {
        var sedeEntity = SedeEntity.build().setCorreoElectronico(correo);

        var resultados = factory.getSedeDAO().consultar(sedeEntity);

        if (!resultados.isEmpty()) {
            var mensajeUsuario = TextHelper
                    .reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00060), correo);
            throw new BusinessGPException(mensajeUsuario);
        }
    }
}
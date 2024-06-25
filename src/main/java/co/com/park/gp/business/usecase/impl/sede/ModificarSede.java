package co.com.park.gp.business.usecase.impl.sede;

import co.com.park.gp.business.assembler.entity.impl.*;
import co.com.park.gp.business.domain.SedeDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.SedeEntity;

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
}

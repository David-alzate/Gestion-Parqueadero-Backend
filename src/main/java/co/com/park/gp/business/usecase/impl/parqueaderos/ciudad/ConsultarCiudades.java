package co.com.park.gp.business.usecase.impl.parqueaderos.ciudad;

import java.util.List;

import co.com.park.gp.business.assembler.entity.impl.parqueaderos.CiudadAssemblerEntity;
import co.com.park.gp.business.domain.parqueaderos.CiudadDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

public class ConsultarCiudades implements UseCaseWithReturn<CiudadDomain, List<CiudadDomain>> {

    private final DAOFactory factory;

    public ConsultarCiudades(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la consulta de las ciudades...";
            var mensajeTecnico = "El dao factory para consultar la ciudad llego nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public List<CiudadDomain> execute(final CiudadDomain data) {
        var ciudadEntityFilter =
                CiudadAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getCiudadDAO().consultar(ciudadEntityFilter);


        return CiudadAssemblerEntity.getInstance().
                toDomainCollection(resultadosEntity);
    }

}

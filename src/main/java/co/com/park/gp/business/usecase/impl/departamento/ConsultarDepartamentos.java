package co.com.park.gp.business.usecase.impl.departamento;

import java.util.List;

import co.com.park.gp.business.assembler.entity.impl.DepartamentoAssemblerEntity;
import co.com.park.gp.business.domain.DepartamentoDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

public class ConsultarDepartamentos implements UseCaseWithReturn<DepartamentoDomain, List<DepartamentoDomain>> {

    private final DAOFactory factory;

    public ConsultarDepartamentos(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la consulta de los departamentos...";
            var mensajeTecnico = "El dao factory para consultar el departamento llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public List<DepartamentoDomain> execute(final DepartamentoDomain data) {
        var departamentoEntityFilter =
                DepartamentoAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getDepartamentoDAO().consultar(departamentoEntityFilter);

        return DepartamentoAssemblerEntity.getInstance().
                toDomainCollection(resultadosEntity);
    }
}


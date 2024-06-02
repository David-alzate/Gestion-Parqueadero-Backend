package co.com.park.gp.business.facade.impl.tiposede;

import java.util.List;

import co.com.park.gp.business.assembler.dto.impl.TipoSedeAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.tiposede.ConsultarTipoSede;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.TipoSedeDTO;

public class ConsultarTipoSedeFacade implements FacadeWhitReturn<TipoSedeDTO, List<TipoSedeDTO>> {

    private DAOFactory daoFactory;

    public ConsultarTipoSedeFacade() {
        this.daoFactory = DAOFactory.getFactory();
    }

    @Override
    public List<TipoSedeDTO> execute(TipoSedeDTO dto) {
        try {
            var useCase = new ConsultarTipoSede(daoFactory);
            var tipoSedeDomain = TipoSedeAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = useCase.execute(tipoSedeDomain);
            return TipoSedeAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);
        } catch (GPException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema al consultar la información de los tipos de sede.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de consultar los tipos de sede.";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}

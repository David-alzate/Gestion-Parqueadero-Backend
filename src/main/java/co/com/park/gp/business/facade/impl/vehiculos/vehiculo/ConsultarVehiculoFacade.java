package co.com.park.gp.business.facade.impl.vehiculos.vehiculo;

import co.com.park.gp.business.assembler.dto.impl.vehiculos.VehiculoAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.vehiculos.vehiculo.ConsultarVehiculo;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.vehiculos.VehiculoDTO;

import java.util.List;

public class ConsultarVehiculoFacade implements FacadeWhitReturn<VehiculoDTO, List<VehiculoDTO>> {

    private final DAOFactory daoFactory;

    public ConsultarVehiculoFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public List<VehiculoDTO> execute(VehiculoDTO dto) {
        try {
            var useCase = new ConsultarVehiculo(daoFactory);
            var vehiculoDomain = VehiculoAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = useCase.execute(vehiculoDomain);

            return VehiculoAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);

        } catch (final GPException exception) {
            throw exception;
        } catch (final Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema al consultar la información de los vehiculos";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el insert del vehiculo en la tabla \"vehiculo\" de la base de datos.";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}

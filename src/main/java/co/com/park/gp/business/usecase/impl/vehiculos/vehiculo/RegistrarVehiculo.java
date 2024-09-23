package co.com.park.gp.business.usecase.impl.vehiculos.vehiculo;

import co.com.park.gp.business.assembler.entity.impl.comunes.TipoVehiculoAssemblerEntity;
import co.com.park.gp.business.domain.vehiculos.VehiculoDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.enums.VehiculoEnum;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.vehiculos.VehiculoEntity;

import java.util.UUID;

public class RegistrarVehiculo implements UseCaseWithoutReturn<VehiculoDomain> {

    private final DAOFactory factory;

    public RegistrarVehiculo(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo el registro de los vehiculos...";
            var mensajeTecnico = "El dao factory para registrar el vehiculo llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }


    @Override
    public void execute(VehiculoDomain data) {
    	
    	if (data.getTipoVehiculo().getTipoVehiculo().equals(VehiculoEnum.CARRO.getNombre()) || data.getTipoVehiculo().getTipoVehiculo().equals(VehiculoEnum.CAMION.getNombre())){
            validarFormatoPlacaCarro(data.getPlaca().toUpperCase());
        }

        if (data.getTipoVehiculo().getTipoVehiculo().equals(VehiculoEnum.MOTO.getNombre())){
            validarFormatoPlacaMoto(data.getPlaca().toUpperCase());
        }

        var vehiculoEntity = VehiculoEntity.build().setId(generarIdentificadorVehiculo())
                .setTipoVehiculo(TipoVehiculoAssemblerEntity.getInstance().toEntity(data.getTipoVehiculo()))
                .setPlaca(data.getPlaca());

        factory.getVehiculoDAO().crear(vehiculoEntity);

    }

    private UUID generarIdentificadorVehiculo() {
        UUID id = UUIDHelper.generate();
        boolean existeId = true;

        while (existeId) {
            id = UUIDHelper.generate();
            var vehiculoEntity = VehiculoEntity.build().setId(id);
            var resultados = factory.getVehiculoDAO().consultar(vehiculoEntity);
            existeId = !resultados.isEmpty();
        }
        return id;
    }
    
    private void validarFormatoPlacaCarro(String placa){
        if(!TextHelper.placaCarroValida(placa)){
            var mensajeUsuario = "El formato de la placa para este tipo de vehiculo es incorrecta";
            throw new BusinessGPException(mensajeUsuario);
        }
    }
    

    private void validarFormatoPlacaMoto(String placa){
        if(!TextHelper.placaMotoValida(placa)){
            var mensajeUsuario = "El formato de la placa para este tipo de vehiculo es incorrecta";
            throw new BusinessGPException(mensajeUsuario);
        }
    }


}

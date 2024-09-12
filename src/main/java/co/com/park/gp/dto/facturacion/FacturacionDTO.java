package co.com.park.gp.dto.facturacion;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.dto.sesionesparqueo.SesionParqueoDTO;
import co.com.park.gp.dto.tarifas.TarifaDTO;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

public final class FacturacionDTO {

    private UUID id;
    private SesionParqueoDTO sesionParqueo;
    private Duration duracion;
    private TarifaDTO tarifa;
    private BigDecimal valorPagar;
    private MetodoPagoDTO metodoPago;

    public FacturacionDTO() {   
        setId(UUIDHelper.getDefault());
        setSesionParqueo(SesionParqueoDTO.build());
        setDuracion(Duration.ZERO);
        setTarifa(TarifaDTO.build());
        setValorPagar(BigDecimal.ZERO);
        setMetodoPago(MetodoPagoDTO.build());
    }

    public FacturacionDTO(UUID id, SesionParqueoDTO sesionParqueo, Duration duracion, TarifaDTO tarifa, BigDecimal valorPagar, MetodoPagoDTO metodoPago) {
        setId(id);
        setSesionParqueo(sesionParqueo);
        setDuracion(duracion);
        setTarifa(tarifa);
        setValorPagar(valorPagar);
        setMetodoPago(metodoPago);
    }

    public static FacturacionDTO build(){
        return new FacturacionDTO();
    }

    public UUID getId() {
        return id;
    }

    public FacturacionDTO setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public SesionParqueoDTO getSesionParqueo() {
        return sesionParqueo;
    }

    public FacturacionDTO setSesionParqueo(SesionParqueoDTO sesionParqueo) {
        this.sesionParqueo = ObjectHelper.getObjectHelper().getDefaultValue(sesionParqueo, SesionParqueoDTO.build());
        return this;
    }

    public Duration getDuracion() {
        return duracion;
    }

    public FacturacionDTO setDuracion(Duration duracion) {
        this.duracion = duracion;
        return this;
    }

    public TarifaDTO getTarifa() {
        return tarifa;
    }

    public FacturacionDTO setTarifa(TarifaDTO tarifa) {
        this.tarifa = ObjectHelper.getObjectHelper().getDefaultValue(tarifa, TarifaDTO.build());
        return this;
    }

    public BigDecimal getValorPagar() {
        return valorPagar;
    }

    public FacturacionDTO setValorPagar(BigDecimal valorPagar) {
        this.valorPagar = valorPagar;
        return this;
    }

    public MetodoPagoDTO getMetodoPago() {
        return metodoPago;
    }

    public FacturacionDTO setMetodoPago(MetodoPagoDTO metodoPago) {
        this.metodoPago = ObjectHelper.getObjectHelper().getDefaultValue(metodoPago, MetodoPagoDTO.build());
        return this;
    }
}

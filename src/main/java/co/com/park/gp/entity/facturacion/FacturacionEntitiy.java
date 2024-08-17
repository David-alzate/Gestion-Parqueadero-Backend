package co.com.park.gp.entity.facturacion;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.dto.facturacion.MetodoPagoDTO;
import co.com.park.gp.dto.sesionesparqueo.SesionParqueoDTO;
import co.com.park.gp.dto.tarifas.TarifaDTO;
import co.com.park.gp.entity.sesionesparqueo.SesionParqueoEntity;
import co.com.park.gp.entity.tarifas.TarifaEntity;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

public final class FacturacionEntitiy {

    private UUID id;
    private SesionParqueoEntity sesionParqueo;
    private Duration duracion;
    private TarifaEntity tarifa;
    private BigDecimal valorPagar;
    private MetodoPagoEntity metodoPago;

    public FacturacionEntitiy() {
        setId(UUIDHelper.getDefault());
        setSesionParqueo(SesionParqueoEntity.build());
        setDuracion(Duration.ZERO);
        setTarifa(TarifaEntity.build());
        setValorPagar(BigDecimal.ZERO);
        setMetodoPago(MetodoPagoEntity.build());
    }

    public FacturacionEntitiy(UUID id, SesionParqueoEntity sesionParqueo, Duration duracion, TarifaEntity tarifa, BigDecimal valorPagar, MetodoPagoEntity metodoPago) {
        setId(id);
        setSesionParqueo(sesionParqueo);
        setDuracion(duracion);
        setTarifa(tarifa);
        setValorPagar(valorPagar);
        setMetodoPago(metodoPago);
    }

    public static FacturacionEntitiy build(){
        return new FacturacionEntitiy();
    }

    public UUID getId() {
        return id;
    }

    public FacturacionEntitiy setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public SesionParqueoEntity getSesionParqueo() {
        return sesionParqueo;
    }

    public FacturacionEntitiy setSesionParqueo(SesionParqueoEntity sesionParqueo) {
        this.sesionParqueo = ObjectHelper.getObjectHelper().getDefaultValue(sesionParqueo, SesionParqueoEntity.build());
        return this;
    }

    public Duration getDuracion() {
        return duracion;
    }

    public FacturacionEntitiy setDuracion(Duration duracion) {
        this.duracion = duracion;
        return this;
    }

    public TarifaEntity getTarifa() {
        return tarifa;
    }

    public FacturacionEntitiy setTarifa(TarifaEntity tarifa) {
        this.tarifa = ObjectHelper.getObjectHelper().getDefaultValue(tarifa, TarifaEntity.build());
        return this;
    }

    public BigDecimal getValorPagar() {
        return valorPagar;
    }

    public FacturacionEntitiy setValorPagar(BigDecimal valorPagar) {
        this.valorPagar = valorPagar;
        return this;
    }

    public MetodoPagoEntity getMetodoPago() {
        return metodoPago;
    }

    public FacturacionEntitiy setMetodoPago(MetodoPagoEntity metodoPago) {
        this.metodoPago = ObjectHelper.getObjectHelper().getDefaultValue(metodoPago, MetodoPagoEntity.build());
        return this;
    }
}

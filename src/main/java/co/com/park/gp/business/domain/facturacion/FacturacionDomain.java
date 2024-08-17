package co.com.park.gp.business.domain.facturacion;

import co.com.park.gp.business.domain.sesionparqueo.SesionParqueoDomain;
import co.com.park.gp.business.domain.tarifas.TarifaDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

public class FacturacionDomain {

    private UUID id;
    private SesionParqueoDomain sesionParqueo;
    private Duration duracion;
    private TarifaDomain tarifa;
    private BigDecimal valorPagar;
    private MetodoPagoDomain metodoPago;

    public FacturacionDomain(UUID id, SesionParqueoDomain sesionParqueo, Duration duracion, TarifaDomain tarifa, BigDecimal valorPagar, MetodoPagoDomain metodoPago) {
        setId(id);
        setSesionParqueo(sesionParqueo);
        setDuracion(duracion);
        setTarifa(tarifa);
        setValorPagar(valorPagar);
        setMetodoPago(metodoPago);
    }

    public static FacturacionDomain build(UUID id, SesionParqueoDomain sesionParqueo, Duration duracion, TarifaDomain tarifa, BigDecimal valorPagar, MetodoPagoDomain metodoPago) {
        return new FacturacionDomain(id, sesionParqueo, duracion, tarifa, valorPagar, metodoPago);
    }

    public static FacturacionDomain build(UUID id) {
        return new FacturacionDomain(id, SesionParqueoDomain.build(), Duration.ZERO, TarifaDomain.build(), BigDecimal.ZERO, MetodoPagoDomain.build());
    }

    public static FacturacionDomain build() {
        return new FacturacionDomain(UUIDHelper.getDefault(), SesionParqueoDomain.build(), Duration.ZERO, TarifaDomain.build(), BigDecimal.ZERO, MetodoPagoDomain.build());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    public SesionParqueoDomain getSesionParqueo() {
        return sesionParqueo;
    }

    public void setSesionParqueo(SesionParqueoDomain sesionParqueo) {
        this.sesionParqueo = ObjectHelper.getObjectHelper().getDefaultValue(sesionParqueo, SesionParqueoDomain.build());
    }

    public Duration getDuracion() {
        return duracion;
    }

    public void setDuracion(Duration duracion) {
        this.duracion = duracion;
    }

    public TarifaDomain getTarifa() {
        return tarifa;
    }

    public void setTarifa(TarifaDomain tarifa) {
        this.tarifa = ObjectHelper.getObjectHelper().getDefaultValue(tarifa, TarifaDomain.build());
    }

    public BigDecimal getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(BigDecimal valorPagar) {
        this.valorPagar = valorPagar;
    }

    public MetodoPagoDomain getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPagoDomain metodoPago) {
        this.metodoPago = ObjectHelper.getObjectHelper().getDefaultValue(metodoPago, MetodoPagoDomain.build());
    }
}

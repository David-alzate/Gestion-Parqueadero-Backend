package co.com.park.gp.business.domain.tarifas;

import co.com.park.gp.business.domain.comunes.TipoVehiculoDomain;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.time.LocalDate;
import java.util.UUID;

public final class TarifaDomain {
    private UUID id;
    private SedeDomain sede;
    private TipoVehiculoDomain tipoVehiculo;
    private TipoTarifaDomain tipoTarifa;
    private Long tarifa;
    private EstadoDomain estado;
    private LocalDate fechaInicioVigencia;
    private LocalDate fechaFinVigencia;

    public TarifaDomain(final UUID id, final SedeDomain sede, final TipoVehiculoDomain tipoVehiculo, final TipoTarifaDomain tipoTarifa,
                        final Long tarifa, final EstadoDomain estado, final LocalDate fechaInicioVigencia, final LocalDate fechaFinVigencia) {
        setId(id);
        setSede(sede);
        setTipoVehiculo(tipoVehiculo);
        setTipoTarifa(tipoTarifa);
        setTarifa(tarifa);
        setEstado(estado);
        setFechaInicioVigencia(fechaInicioVigencia);
        setFechaFinVigencia(fechaFinVigencia);
    }

    public static TarifaDomain build(final UUID id, final SedeDomain sede, final TipoVehiculoDomain tipoVehiculo, final TipoTarifaDomain tipoTarifa,
                                     final Long tarifa, final EstadoDomain estado, final LocalDate fechaInicioVigencia, final LocalDate fechaFinVigencia) {
        return new TarifaDomain(id, sede, tipoVehiculo, tipoTarifa, tarifa, estado, fechaInicioVigencia, fechaFinVigencia);
    }

    public static TarifaDomain build(final UUID id) {
        return new TarifaDomain(id, SedeDomain.build(), TipoVehiculoDomain.build(), TipoTarifaDomain.build(), 0L, EstadoDomain.build(),
                LocalDate.now(), LocalDate.now().plusYears(1));
    }

    public static TarifaDomain build() {
        return new TarifaDomain(UUIDHelper.getDefault(), SedeDomain.build(), TipoVehiculoDomain.build(), TipoTarifaDomain.build(), 0L, EstadoDomain.build(),
                LocalDate.now(), LocalDate.now().plusYears(1));
    }

    private void setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    private void setSede(SedeDomain sede) {
        this.sede = ObjectHelper.getObjectHelper().getDefaultValue(sede, SedeDomain.build());
    }

    private void setTipoVehiculo(TipoVehiculoDomain tipoVehiculo) {
        this.tipoVehiculo = ObjectHelper.getObjectHelper().getDefaultValue(tipoVehiculo, TipoVehiculoDomain.build());
    }

    private void setTipoTarifa(TipoTarifaDomain tipoTarifa) {
        this.tipoTarifa = ObjectHelper.getObjectHelper().getDefaultValue(tipoTarifa, TipoTarifaDomain.build());
    }

    private void setTarifa(Long tarifa) {
        this.tarifa = tarifa;
    }

    private void setEstado(EstadoDomain estado) {
        this.estado = ObjectHelper.getObjectHelper().getDefaultValue(estado, EstadoDomain.build());
    }

    private void setFechaInicioVigencia(LocalDate fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    private void setFechaFinVigencia(LocalDate fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public UUID getId() {
        return id;
    }

    public SedeDomain getSede() {
        return sede;
    }

    public TipoVehiculoDomain getTipoVehiculo() {
        return tipoVehiculo;
    }

    public TipoTarifaDomain getTipoTarifa() {
        return tipoTarifa;
    }

    public Long getTarifa() {
        return tarifa;
    }

    public EstadoDomain getEstado() {
        return estado;
    }

    public LocalDate getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public LocalDate getFechaFinVigencia() {
        return fechaFinVigencia;
    }
}

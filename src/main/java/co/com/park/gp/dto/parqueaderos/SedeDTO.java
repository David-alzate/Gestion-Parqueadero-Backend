package co.com.park.gp.dto.parqueaderos;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class SedeDTO {

    private UUID id;
    private ParqueaderoDTO parqueadero;
    private String nombre;
    private CiudadDTO ciudad;
    private String direccion;
    private String correoElectronico;
    private int celdasCarro;
    private int celdasMoto;
    private int celdascamion;
    private TipoSedeDTO tipoSede;
    private PaisDTO pais;
    private DepartamentoDTO departamento;

    public SedeDTO() {
        super();
        setId(UUIDHelper.getDefault());
        setParqueadero(ParqueaderoDTO.build());
        setNombre(TextHelper.EMPTY);
        setCiudad(CiudadDTO.build());
        setDireccion(TextHelper.EMPTY);
        setCorreoElectronico(TextHelper.EMPTY);
        setCeldasCarro(0);
        setCeldasMoto(0);
        setCeldascamion(0);
        setTipoSede(TipoSedeDTO.build());
        setPais(PaisDTO.build());
        setDepartamento(DepartamentoDTO.build());
    }

    public SedeDTO(final UUID id, final ParqueaderoDTO parqueadero, final String nombre, final CiudadDTO ciudad,
                   final String direccion, final String correoElectronico, final int celdasCarro, final int celdasMoto,
                   final int celdascamion, final TipoSedeDTO tipoSede, final PaisDTO pais,
                   final DepartamentoDTO departamento) {
        setId(id);
        setParqueadero(parqueadero);
        setNombre(nombre);
        setCiudad(ciudad);
        setDireccion(direccion);
        setCorreoElectronico(correoElectronico);
        setCeldasCarro(celdasCarro);
        setCeldasMoto(celdasMoto);
        setCeldascamion(celdascamion);
        setTipoSede(tipoSede);
        setPais(pais);
        setDepartamento(departamento);
    }

    public static SedeDTO build() {
        return new SedeDTO();
    }

    public final SedeDTO setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public final SedeDTO setParqueadero(final ParqueaderoDTO parqueadero) {
        this.parqueadero = ObjectHelper.getObjectHelper().getDefaultValue(parqueadero, new ParqueaderoDTO());
        return this;
    }

    public final SedeDTO setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public final SedeDTO setCiudad(final CiudadDTO ciudad) {
        this.ciudad = ObjectHelper.getObjectHelper().getDefaultValue(ciudad, new CiudadDTO());
        return this;
    }

    public final SedeDTO setDireccion(final String direccion) {
        this.direccion = TextHelper.applyTrim(direccion);
        return this;
    }

    public final SedeDTO setCorreoElectronico(final String correoElectronico) {
        this.correoElectronico = TextHelper.applyTrim(correoElectronico);
        return this;
    }

    public final SedeDTO setCeldasCarro(final int celdasCarro) {
        this.celdasCarro = celdasCarro;
        return this;
    }

    public final SedeDTO setCeldasMoto(final int celdasMoto) {
        this.celdasMoto = celdasMoto;
        return this;
    }

    public final SedeDTO setCeldascamion(final int celdascamion) {
        this.celdascamion = celdascamion;
        return this;
    }

    public final SedeDTO setTipoSede(final TipoSedeDTO tipoSede) {
        this.tipoSede = ObjectHelper.getObjectHelper().getDefaultValue(tipoSede, new TipoSedeDTO());
        return this;
    }

    public final SedeDTO setPais(final PaisDTO pais) {
        this.pais = ObjectHelper.getObjectHelper().getDefaultValue(pais, new PaisDTO());
        return this;
    }

    public final SedeDTO setDepartamento(final DepartamentoDTO departamento) {
        this.departamento = ObjectHelper.getObjectHelper().getDefaultValue(departamento, new DepartamentoDTO());
        return this;
    }

    public final UUID getId() {
        return id;
    }

    public final ParqueaderoDTO getParqueadero() {
        return parqueadero;
    }

    public final String getNombre() {
        return nombre;
    }

    public final CiudadDTO getCiudad() {
        return ciudad;
    }

    public final String getDireccion() {
        return direccion;
    }

    public final String getCorreoElectronico() {
        return correoElectronico;
    }

    public final int getCeldasCarro() {
        return celdasCarro;
    }

    public final int getCeldasMoto() {
        return celdasMoto;
    }

    public final int getCeldascamion() {
        return celdascamion;
    }

    public final TipoSedeDTO getTipoSede() {
        return tipoSede;
    }

    public final PaisDTO getPais() {
        return pais;
    }

    public final DepartamentoDTO getDepartamento() {
        return departamento;
    }

}

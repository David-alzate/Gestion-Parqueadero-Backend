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

    public SedeDTO setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public SedeDTO setParqueadero(final ParqueaderoDTO parqueadero) {
        this.parqueadero = ObjectHelper.getObjectHelper().getDefaultValue(parqueadero, new ParqueaderoDTO());
        return this;
    }

    public SedeDTO setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public SedeDTO setCiudad(final CiudadDTO ciudad) {
        this.ciudad = ObjectHelper.getObjectHelper().getDefaultValue(ciudad, new CiudadDTO());
        return this;
    }

    public SedeDTO setDireccion(final String direccion) {
        this.direccion = TextHelper.applyTrim(direccion);
        return this;
    }

    public SedeDTO setCorreoElectronico(final String correoElectronico) {
        this.correoElectronico = TextHelper.applyTrim(correoElectronico);
        return this;
    }

    public SedeDTO setCeldasCarro(final int celdasCarro) {
        this.celdasCarro = celdasCarro;
        return this;
    }

    public SedeDTO setCeldasMoto(final int celdasMoto) {
        this.celdasMoto = celdasMoto;
        return this;
    }

    public SedeDTO setCeldascamion(final int celdascamion) {
        this.celdascamion = celdascamion;
        return this;
    }

    public SedeDTO setTipoSede(final TipoSedeDTO tipoSede) {
        this.tipoSede = ObjectHelper.getObjectHelper().getDefaultValue(tipoSede, new TipoSedeDTO());
        return this;
    }

    public SedeDTO setPais(final PaisDTO pais) {
        this.pais = ObjectHelper.getObjectHelper().getDefaultValue(pais, new PaisDTO());
        return this;
    }

    public SedeDTO setDepartamento(final DepartamentoDTO departamento) {
        this.departamento = ObjectHelper.getObjectHelper().getDefaultValue(departamento, new DepartamentoDTO());
        return this;
    }

    public UUID getId() {
        return id;
    }

    public ParqueaderoDTO getParqueadero() {
        return parqueadero;
    }

    public String getNombre() {
        return nombre;
    }

    public CiudadDTO getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public int getCeldasCarro() {
        return celdasCarro;
    }

    public int getCeldasMoto() {
        return celdasMoto;
    }

    public int getCeldascamion() {
        return celdascamion;
    }

    public TipoSedeDTO getTipoSede() {
        return tipoSede;
    }

    public PaisDTO getPais() {
        return pais;
    }

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

}

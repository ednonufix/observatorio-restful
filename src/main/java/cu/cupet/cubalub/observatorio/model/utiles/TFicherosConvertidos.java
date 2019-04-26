package cu.cupet.cubalub.observatorio.model.utiles;

import java.io.Serializable;
import java.util.Date;

/**
 * Creado a las 23:07 del día 1/11/16.
 *
 * @author Eduardo Noel Núñez <enoel.corebsd@gmail.com>
 */
public class TFicherosConvertidos implements Serializable {

    private static final long serialVersionUID = -8662419454224L;

    private Long id_fichero;
    private String nomb_fichero;
    private String ruta_relativa;
    private Boolean audio_libro;
    private Boolean fichero_pdf;
    private Date fecha_conversion;

    public Long getId_fichero() {
        return id_fichero;
    }

    public void setId_fichero(Long id_fichero) {
        this.id_fichero = id_fichero;
    }

    public String getNomb_fichero() {
        return nomb_fichero;
    }

    public void setNomb_fichero(String nomb_fichero) {
        this.nomb_fichero = nomb_fichero;
    }

    public String getRuta_relativa() {
        return ruta_relativa;
    }

    public void setRuta_relativa(String ruta_relativa) {
        this.ruta_relativa = ruta_relativa;
    }

    public Boolean getAudio_libro() {
        return audio_libro;
    }

    public void setAudio_libro(Boolean audio_libro) {
        this.audio_libro = audio_libro;
    }

    public Boolean getFichero_pdf() {
        return fichero_pdf;
    }

    public void setFichero_pdf(Boolean fichero_pdf) {
        this.fichero_pdf = fichero_pdf;
    }

    public Date getFecha_conversion() {
        return fecha_conversion;
    }

    public void setFecha_conversion(Date fecha_conversion) {
        this.fecha_conversion = fecha_conversion;
    }

    public TFicherosConvertidos() {
    }

}

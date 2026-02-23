package uniandes.dpoo.aerolinea.persistencia;

import java.io.IOException;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;

/**
 * Esta interfaz define las operaciones relacionadas con la persistencia de la aerolínea (salvar y cargar).
 */
public interface IPersistenciaAerolinea
{
    /**
     * Carga la información de la aerolínea desde un archivo.
     * @param archivo La ruta al archivo que contiene la información
     * @param aerolinea La aerolínea donde debe cargarse la información
     * @throws IOException Si hay problemas leyendo el archivo
     * @throws InformacionInconsistenteException Si la información es inconsistente
     */
    void cargarAerolinea( String archivo, Aerolinea aerolinea ) throws IOException, InformacionInconsistenteException;

    /**
     * Salva la información de la aerolínea en un archivo.
     * @param archivo La ruta al archivo donde debe guardarse la información
     * @param aerolinea La aerolínea con la información a guardar
     * @throws IOException Si hay problemas escribiendo el archivo
     */
    void salvarAerolinea( String archivo, Aerolinea aerolinea ) throws IOException;

}

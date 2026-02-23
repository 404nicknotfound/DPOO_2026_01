package uniandes.dpoo.aerolinea.persistencia;

import java.io.IOException;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;

/**
 * Implementación de persistencia de la aerolínea en formato JSON.
 */
public class PersistenciaAerolineaJson implements IPersistenciaAerolinea
{
    @Override
    public void cargarAerolinea( String archivo, Aerolinea aerolinea ) throws IOException, InformacionInconsistenteException
    {
        // TODO: implementar carga desde JSON si se requiere en el taller
    }

    @Override
    public void salvarAerolinea( String archivo, Aerolinea aerolinea ) throws IOException
    {
        // TODO: implementar guardado en JSON si se requiere en el taller
    }

}

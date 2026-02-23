package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Vuelo
{
    private String fecha;
    private Ruta ruta;
    private Avion avion;
    private Map<String, Tiquete> tiquetes;

    public Vuelo(String fecha, Ruta ruta, Avion avion) {
        this.fecha = fecha;
        this.ruta = ruta;
        this.avion = avion;
        this.tiquetes = new HashMap<String, Tiquete>();
    }

    public Ruta getRuta() {
        return this.ruta;
    }
    public String getFecha() {
        return this.fecha;
    }
    public Avion getAvion() {
        return this.avion;
    }

    public Collection<Tiquete> getTiquetes( )
    {
        return tiquetes.values( );
    }

    /**
     * Vende una determinada cantidad de tiquetes para el vuelo y los deja registrados en el mapa de tiquetes.
     * @param cliente El cliente al cual se le venden los tiquetes
     * @param calculadora La calculadora de tarifas que debe usarse para saber el precio por tiquete
     * @param cantidad La cantidad de tiquetes que se quieren comprar
     * @return El valor total de los tiquetes vendidos
     * @throws VueloSobrevendidoException Si no hay suficiente espacio en el vuelo
     */
    public int venderTiquetes( Cliente cliente, CalculadoraTarifas calculadora, int cantidad ) throws VueloSobrevendidoException
    {
        int asientosOcupados = tiquetes.size( );
        if( asientosOcupados + cantidad > avion.getCapacidad( ) )
            throw new VueloSobrevendidoException( this );
        int total = 0;
        for( int i = 0; i < cantidad; i++ )
        {
            int tarifa = calculadora.calcularTarifa( this, cliente );
            Tiquete tiquete = GeneradorTiquetes.generarTiquete( this, cliente, tarifa );
            tiquetes.put( tiquete.getCodigo( ), tiquete );
            total += tarifa;
        }
        return total;
    }

}

package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.LinkedList;
import java.util.List;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;


public abstract class Cliente
{

    private List<Tiquete> tiquetesSinUsar;
    private List<Tiquete> tiquetesUsados;

    public Cliente( ) {
        this.tiquetesSinUsar = new LinkedList<Tiquete>( );
        this.tiquetesUsados = new LinkedList<Tiquete>( );
    }

    public void agregarTiquete( Tiquete tiquete ) {
        this.tiquetesSinUsar.add( tiquete );
    }

    public int calcularValorTotalTiquetes( ) {
        int total = 0;
        for( Tiquete t : tiquetesSinUsar )
            total += t.getTarifa( );
        for( Tiquete t : tiquetesUsados )
            total += t.getTarifa( );
        return total;
    }

    public void usarTiquetes( Vuelo vuelo ) {
        List<Tiquete> aMover = new LinkedList<Tiquete>( );
        for( Tiquete t : tiquetesSinUsar )
        {
            if( t.getVuelo( ).equals( vuelo ) )
            {
                t.marcarComoUsado( );
                aMover.add( t );
            }
        }
        tiquetesSinUsar.removeAll( aMover );
        tiquetesUsados.addAll( aMover );
    }

    public abstract String getTipoCliente( );
    public abstract String getIdentificador( );
}

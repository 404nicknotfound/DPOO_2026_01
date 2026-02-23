package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteNatural;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas
{

    public static int COSTO_POR_KM_NATURAL = 600;
    public static int COSTO_POR_KM_CORPORATIVO = 900;
    public static double DESCUENTO_PEQ = 0.02;
    public static double DESCUENTO_MEDIANAS = 0.1;
    public static double DESCUENTO_GRANDES = 0.2;

    @Override
    protected int calcularCostoBase( Vuelo vuelo, Cliente cliente )
    {
        if( ClienteNatural.NATURAL.equals( cliente.getTipoCliente( ) ) )
            return COSTO_POR_KM_NATURAL * calcularDistanciaVuelo( vuelo.getRuta( ) );
        return COSTO_POR_KM_CORPORATIVO * calcularDistanciaVuelo( vuelo.getRuta( ) );
    }
    @Override
    public double calcularPorcentajeDescuento( Cliente cliente )
    {
        if( !ClienteCorporativo.CORPORATIVO.equals( cliente.getTipoCliente( ) ) )
            return 0;
        int tamano = ( (ClienteCorporativo) cliente ).getTamanoEmpresa( );
        if( tamano == ClienteCorporativo.PEQUENA )
            return DESCUENTO_PEQ;
        if( tamano == ClienteCorporativo.MEDIANA )
            return DESCUENTO_MEDIANAS;
        if( tamano == ClienteCorporativo.GRANDE )
            return DESCUENTO_GRANDES;
        return 0;
    }

}

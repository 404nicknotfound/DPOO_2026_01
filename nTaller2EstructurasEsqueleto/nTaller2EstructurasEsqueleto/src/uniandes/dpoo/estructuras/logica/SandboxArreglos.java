package uniandes.dpoo.estructuras.logica;

import java.util.HashMap;

/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre arreglos de enteros y de cadenas.
 *
 * Todos los métodos deben operar sobre los atributos arregloEnteros y arregloCadenas.
 * 
 * No pueden agregarse nuevos atributos.
 * 
 * Implemente los métodos usando operaciones sobre arreglos (ie., no haga cosas como construir listas para evitar la manipulación de arreglos).
 */
public class SandboxArreglos
{
    /**
     * Un arreglo de enteros para realizar varias de las siguientes operaciones.
     * 
     * Ninguna posición del arreglo puede estar vacía en ningún momento.
     */
    private int[] arregloEnteros;

    /**
     * Un arreglo de cadenas para realizar varias de las siguientes operaciones
     * 
     * Ninguna posición del arreglo puede estar vacía en ningún momento.
     */
    private String[] arregloCadenas;

    /**
     * Crea una nueva instancia de la clase con los dos arreglos inicializados pero vacíos (tamaño 0)
     */
    public SandboxArreglos( )
    {
        arregloEnteros = new int[]{};
        arregloCadenas = new String[]{};
    }

    /**
     * Retorna una copia del arreglo de enteros, es decir un nuevo arreglo del mismo tamaño que contiene copias de los valores del arreglo original
     * @return Una copia del arreglo de enteros
     */
    public int[] getCopiaEnteros( )
    {
    	if (this.arregloEnteros == null) return null;
    	
        int[] arregloCopia = new int[getCantidadEnteros()];
        
        for( int i = 0 ; i<getCantidadEnteros(); i++) {
        	arregloCopia[i] = this.arregloEnteros[i];
        	
        }
        return arregloCopia;
    }

    /**
     * Retorna una copia del arreglo de cadenas, es decir un nuevo arreglo del mismo tamaño que contiene copias de los valores del arreglo original
     * @return Una copia del arreglo de cadenas
     */
    public String[] getCopiaCadenas( )
    {
    	if (this.arregloCadenas == null) return null;
    	
        String[] arregloCopia = new String[getCantidadCadenas()];
        
        for( int i = 0 ; i<getCantidadCadenas(); i++) {
        	arregloCopia[i] = this.arregloCadenas[i];
        	
        }
        return arregloCopia;
    }

    /**
     * Retorna la cantidad de valores en el arreglo de enteros
     * @return
     */
    public int getCantidadEnteros( )
    {
    	return this.arregloEnteros.length;
    }

    /**
     * Retorna la cantidad de valores en el arreglo de cadenas
     * @return
     */
    public int getCantidadCadenas( )
    {
        return this.arregloCadenas.length;
    }

    /**
     * Agrega un nuevo valor al final del arreglo. Es decir que este método siempre debería aumentar en 1 la capacidad del arreglo.
     * 
     * @param entero El valor que se va a agregar.
     */
    public void agregarEntero( int entero )
    {
    	int[] arregloNuevo = new int[getCantidadEnteros()+ 1];
    	
        for( int i = 0 ; i<getCantidadEnteros(); i++) {
        	arregloNuevo[i] = this.arregloEnteros[i];
        	
        }
        
        arregloNuevo[arregloNuevo.length -1] = entero; 
    	this.arregloEnteros = arregloNuevo;
    	
    }

    /**
     * Agrega un nuevo valor al final del arreglo. Es decir que este método siempre debería aumentar en 1 la capacidad del arreglo.
     * 
     * @param cadena La cadena que se va a agregar.
     */
    public void agregarCadena( String cadena )
    {
    	String[] arregloNuevo = new String[getCantidadCadenas() + 1];
    	
    	for (int i = 0 ; i<getCantidadCadenas(); i++) {
    		arregloNuevo[i] = this.arregloCadenas[i];	
    	}
    	arregloNuevo[arregloNuevo.length - 1] = cadena;
    	this.arregloCadenas = arregloNuevo;
    	
    }

    /**
     * Elimina todas las apariciones de un determinado valor dentro del arreglo de enteros
     * @param valor El valor que se va eliminar
     */
    public void eliminarEntero( int valor )
    {
    	int nuevoTotal = 0;
    	for (int i = 0 ; i<getCantidadEnteros() ; i++) {
    		if (this.arregloEnteros[i] != valor) {
    			nuevoTotal++;
    		}
    	}
    	
    	int[] arregloCopia = new int[nuevoTotal];
    	int j = 0;
    	
    	for (int i = 0 ; i<getCantidadEnteros() ; i++) {
    		if (this.arregloEnteros[i] != valor) {
    			arregloCopia[j] = this.arregloEnteros[i];
    			j++;
    		}
    	}
    	
    	this.arregloEnteros = arregloCopia;
    	
    }

    /**
     * Elimina todas las apariciones de un determinado valor dentro del arreglo de cadenas
     * @param cadena La cadena que se va eliminar
     */
    public void eliminarCadena( String cadena )
    {
    	int nuevoTotal = 0;
    	for (int i = 0 ; i<getCantidadCadenas() ; i++) {
    		if (this.arregloCadenas[i] != cadena) {
    			nuevoTotal++;
    		}
    	}
    	
    	String[] arregloCopia = new String[nuevoTotal];
    	int j = 0;
    	
    	for (int i = 0 ; i<getCantidadCadenas() ; i++) {
    		if (this.arregloCadenas[i] != cadena) {
    			arregloCopia[j] = this.arregloCadenas[i];
    			j++;
    		}
    	}
    	
    	this.arregloCadenas = arregloCopia;
    }

    /**
     * Inserta un nuevo entero en el arreglo de enteros.
     * 
     * @param entero El nuevo valor que debe agregarse
     * @param posicion La posición donde debe quedar el nuevo valor en el arreglo aumentado. Si la posición es menor a 0, se inserta el valor en la primera posición. Si la
     *        posición es mayor que el tamaño del arreglo, se inserta el valor en la última posición.
     */
    public void insertarEntero( int entero, int posicion )
    {
    	int posicionInsertar = posicion;
    	
    	if (posicion <= 0) {
    		posicionInsertar = 0;
    	}
    	else if (posicion >= getCantidadEnteros() ){
    		posicionInsertar = getCantidadEnteros();
    	}

    	int[] arregloNuevo = new int[getCantidadEnteros() + 1];
    	for (int i = 0 ; i<arregloNuevo.length ; i++) {
    		if (i < posicionInsertar) {
    			arregloNuevo[i] = this.arregloEnteros[i];
    		}
    		else if (i == posicionInsertar) {
    			arregloNuevo[i] = entero;
    			
    		}
    		else {
    			arregloNuevo[i] = this.arregloEnteros[i-1];
    		}
    	}
    	this.arregloEnteros = arregloNuevo;
    }
    

    /**
     * Elimina un valor del arreglo de enteros dada su posición.
     * @param posicion La posición donde está el elemento que debe ser eliminado. Si el parámetro posicion no corresponde a ninguna posición del arreglo de enteros, el método
     *        no debe hacer nada.
     */
    public void eliminarEnteroPorPosicion( int posicion )
    {
    	
    	if ( 0 <= posicion && posicion < getCantidadEnteros()){
        	int[] arregloNuevo = new int[getCantidadEnteros() -1];
        	
        	for(int i = 0 ; i<arregloNuevo.length ; i++) {
        		if ( i < posicion) {
        			arregloNuevo[i] = this.arregloEnteros[i];
        		}
        		
        		else if ( i >= posicion) {
        			arregloNuevo[i] = this.arregloEnteros[i+1];
        		}
        		
        	}
        	this.arregloEnteros = arregloNuevo;
    	}    	
    }

    /**
     * Reinicia el arreglo de enteros con los valores contenidos en el arreglo del parámetro 'valores' truncados.
     * 
     * Es decir que si el valor fuera 3.67, en el nuevo arreglo de enteros debería quedar el entero 3.
     * @param valores Un arreglo de valores decimales.
     */
    public void reiniciarArregloEnteros( double[] valores )
    {
    	int[] arregloNuevo = new int[valores.length];
    	for (int i = 0 ; i<arregloNuevo.length ; i++ ) {
    		arregloNuevo[i] = (int) valores[i];
    	
    	}
    	
    	this.arregloEnteros = arregloNuevo;

    }

    /**
     * Reinicia el arreglo de cadenas con las representaciones como Strings de los objetos contenidos en el arreglo del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Un arreglo de objetos
     */
    public void reiniciarArregloCadenas( Object[] objetos )
    {
    	String[] arregloNuevo = new String[objetos.length];
    	for (int i = 0 ; i<arregloNuevo.length ; i++){
    		arregloNuevo[i] = objetos[i].toString();	
    		
    	}
    	
    	this.arregloCadenas = arregloNuevo;
   
    }

    /**
     * Modifica el arreglo de enteros para que todos los valores sean positivos.
     * 
     * Es decir que si en una posición había un valor negativo, después de ejecutar el método debe quedar el mismo valor muliplicado por -1.
     */
    public void volverPositivos( )
    {
    	for (int i = 0 ; i<getCantidadEnteros() ; i++) {
    		
    		this.arregloEnteros[i] = Math.abs(this.arregloEnteros[i]);
    	}
    }

    /**
     * Modifica el arreglo de enteros para que todos los valores queden organizados de menor a mayor.
     */
    public void organizarEnteros( )
    {
    	java.util.Arrays.sort(this.arregloEnteros);
    }

    /**
     * Modifica el arreglo de cadenas para que todos los valores queden organizados lexicográficamente.
     */
    public void organizarCadenas( )
    {
    	java.util.Arrays.sort(this.arregloCadenas);
    }

    /**
     * Cuenta cuántas veces aparece el valor recibido por parámetro en el arreglo de enteros
     * @param valor El valor buscado
     * @return La cantidad de veces que aparece el valor
     */
    public int contarApariciones( int valor )
    {
    	int contador = 0;
    	for (int i = 0; i<getCantidadEnteros() ; i++) {
    		if (this.arregloEnteros[i] == valor) {
    			contador ++;	
    		}
    	}
    	return contador;
    }

    /**
     * Cuenta cuántas veces aparece la cadena recibida por parámetro en el arreglo de cadenas.
     * 
     * La búsqueda no debe diferenciar entre mayúsculas y minúsculas.
     * @param cadena La cadena buscada
     * @return La cantidad de veces que aparece la cadena
     */
    public int contarApariciones( String cadena )
    {
    	int contador = 0;
    	for (int i = 0; i<getCantidadCadenas() ; i++) {
    		if (this.arregloCadenas[i].equalsIgnoreCase(cadena)) {
    			contador ++;	
    		}
    	}
    	return contador;
    }

    /**
     * Busca en qué posiciones del arreglo de enteros se encuentra el valor que se recibe en el parámetro
     * @param valor El valor que se debe buscar
     * @return Un arreglo con los números de las posiciones del arreglo de enteros en las que se encuentra el valor buscado. Si el valor no se encuentra, el arreglo retornado
     *         es de tamaño 0.
     */
    public int[] buscarEntero( int valor )
    {
    	int cantidadPos = contarApariciones(valor);
        int[] posiciones = new int[cantidadPos];
        int j = 0;
        if (posiciones.length > 0) {
        	for ( int i = 0 ; i<getCantidadEnteros() ; i++) {
        		if ( this.arregloEnteros[i] == valor) {
        			posiciones[j] = i;
        			j++;
        		}
        	}
        }
        return posiciones;
        
    }

    /**
     * Calcula cuál es el rango de los enteros (el valor mínimo y el máximo).
     * @return Un arreglo con dos posiciones: en la primera posición, debe estar el valor mínimo en el arreglo de enteros; en la segunda posición, debe estar el valor máximo
     *         en el arreglo de enteros. Si el arreglo está vacío, debe retornar un arreglo vacío.
     */
    public int[] calcularRangoEnteros( )
    {
    	int[] maxMin = new int[] {};
    	if (getCantidadEnteros() > 0) {
    		maxMin = new int[2];
    		int min = this.arregloEnteros[0];
    		int max = this.arregloEnteros[0];
    		for (int i = 0 ; i<getCantidadEnteros() ; i++) {
    			if (this.arregloEnteros[i] > max) {
    				max = this.arregloEnteros[i];
    			}
    			if (this.arregloEnteros[i] < min) {
    				min = this.arregloEnteros[i];
    			}
    		
    		}
    		maxMin[0] = min;
    		maxMin[1] = max;
    	}
    		
    	return maxMin;
    }

    /**
     * Calcula un histograma de los valores del arreglo de enteros y lo devuelve como un mapa donde las llaves son los valores del arreglo y los valores son la cantidad de
     * veces que aparece cada uno en el arreglo de enteros.
     * @return Un mapa con el histograma de valores.
     */
    public HashMap<Integer, Integer> calcularHistograma( )
    {
        HashMap<Integer, Integer> histograma = new HashMap<Integer, Integer>();
        for (int i = 0 ; i<getCantidadEnteros() ; i++) {
        	if (!histograma.containsKey(this.arregloEnteros[i])) {
        		histograma.put(this.arregloEnteros[i] , contarApariciones(this.arregloEnteros[i]));
        		
        	}
        	
        	
        }
        return histograma;
        
    }

    /**
     * Cuenta cuántos valores dentro del arreglo de enteros están repetidos.
     * @return La cantidad de enteos diferentes que aparecen más de una vez
     */
    public int contarEnterosRepetidos( )
    {
    	HashMap<Integer, Integer> histograma = new java.util.HashMap<>();
    	for (int i = 0; i < this.arregloEnteros.length; i++) {
    		int valor = this.arregloEnteros[i];
    	    if (!histograma.containsKey(valor)) {
    	    	histograma.put(valor, 1);
    	        } 
    	    else {
    	    	histograma.put(valor, histograma.get(valor) + 1);
    	        }
    	}
    	int repetidos = 0;
    	for (int cantidad : histograma.values()) {
    		if (cantidad > 1) {
    			repetidos++;
    	    }
    	}
    	return repetidos;
    }

    /**
     * Compara el arreglo de enteros con otro arreglo de enteros y verifica si son iguales, es decir que contienen los mismos elementos exactamente en el mismo orden.
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si los arreglos son idénticos y false de lo contrario
     */
    public boolean compararArregloEnteros( int[] otroArreglo )
    {
        if (this.arregloEnteros.length != otroArreglo.length) {
            return false;
        }


        for (int i = 0; i < this.arregloEnteros.length; i++) {
            if (this.arregloEnteros[i] != otroArreglo[i]) {

                return false;
            }
        }

        return true;
    }

    /**
     * Compara el arreglo de enteros con otro arreglo de enteros y verifica que tengan los mismos elementos, aunque podría ser en otro orden.
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si los elementos en los dos arreglos son los mismos
     */
    public boolean mismosEnteros( int[] otroArreglo )
    {
        if (this.arregloEnteros.length != otroArreglo.length) {
            return false;
        }
        
        int[] copiaArreglo = otroArreglo.clone();
        // organizar los dos Arreglos para llamar al metodo anterior
        organizarEnteros();
        java.util.Arrays.sort(copiaArreglo);
        
        return compararArregloEnteros(copiaArreglo);
    }

    /**
     * Cambia los elementos del arreglo de enteros por una nueva serie de valores generada de forma aleatoria.
     * 
     * Para generar los valores se debe partir de una distribución uniforme usando Math.random().
     * 
     * Los números en el arreglo deben quedar entre el valor mínimo y el máximo.
     * @param cantidad La cantidad de elementos que debe haber en el arreglo
     * @param minimo El valor mínimo para los números generados
     * @param maximo El valor máximo para los números generados
     */
    public void generarEnteros( int cantidad, int minimo, int maximo )
    {
    	int[] arregloNuevo = new int[cantidad];
    	
    	for(int i = 0 ; i<cantidad ; i++) {
    		int rango = maximo - minimo + 1 ;
    		arregloNuevo[i] = (int) (Math.random() * rango) + minimo;
    	}
    	
    	this.arregloEnteros = arregloNuevo;
    		
   	
    }

}

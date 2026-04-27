package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {

	private ProductoAjustado productoAjustado;
	private ProductoMenu productoBase;
	private Ingrediente tomate;
	private Ingrediente queso;
	private String facturaEsperada;
	
	@BeforeEach
	public void setup() {
		this.productoBase = new ProductoMenu("corral",14000);
		this.productoAjustado = new ProductoAjustado(productoBase);
		this.tomate = new Ingrediente("tomate", 1000);
		this.queso = new Ingrediente("queso", 2500);	
        this.facturaEsperada = "corral\n" +
                "            14000\n" +
                "    +queso" +
                "                2500" +
                "    +queso" +
                "                2500" +
                "    -tomate" +
                "            19000\n";
	}
	
	@Test
	public void getNombreTest() {
		assertEquals("corral", productoAjustado.getNombre(), "el nombre del producto no es el esperado");
		
	}
	
    @Test
    public void getPrecioSinAjustesTest() {
        assertEquals(14000, productoAjustado.getPrecio(), "El precio base no es el esperado");
    }

    @Test
    public void getPrecioConAgregadosTest() {
        productoAjustado.agregarIngrediente(tomate);
        productoAjustado.agregarIngrediente(queso);
        // 14000 + 1000 + 2500 = 17500
        assertEquals(17500, productoAjustado.getPrecio(), "El precio ajustado no es el esperado");
    }

    @Test
    public void getPrecioConEliminadosTest() {
        productoAjustado.eliminarIngrediente(tomate);
        // Eliminar no debe de cambiar el precio
        assertEquals(14000, productoAjustado.getPrecio(), "El precio ajustado no es el esperado");
    }

    @Test
    public void generarTextoFacturaTest() {
        productoAjustado.agregarIngrediente(queso);
        productoAjustado.agregarIngrediente(queso);
        productoAjustado.eliminarIngrediente(tomate);
        assertEquals(facturaEsperada, productoAjustado.generarTextoFactura(), "La factura generada no es la esperada");
    }
}
	
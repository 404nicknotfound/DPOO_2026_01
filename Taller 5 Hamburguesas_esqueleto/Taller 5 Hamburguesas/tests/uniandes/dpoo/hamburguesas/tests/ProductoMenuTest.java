package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {
	
	private ProductoMenu productoMenu1;
	private String facturaEsperada;
	
	@BeforeEach
	public void setup() {
		this.productoMenu1 = new ProductoMenu("corral", 14000);
		this.facturaEsperada = "corral\n" +
							   "            14000\n";
	}
	
	@Test
	public void getNombreTest() {
		assertEquals("corral", productoMenu1.getNombre(), "El nombre del producto no es el esperado" );
	}

	@Test
	public void  getPrecioTest() {
		assertEquals(14000, productoMenu1.getPrecio(), "El precio del producto no es el esperado");		
	}
	
	@Test
	public void generarTextoFacturaTest() {
		assertEquals(this.facturaEsperada, productoMenu1.generarTextoFactura(), "La Factura generada no es la esperada");
		
	}
}

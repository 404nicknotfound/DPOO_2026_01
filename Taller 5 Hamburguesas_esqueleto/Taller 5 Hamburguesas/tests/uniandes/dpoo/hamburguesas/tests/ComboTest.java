package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
	
	private ProductoMenu corral;
	private ProductoMenu papas;
	private ProductoMenu gaseosa;
	private Combo combo;
	private ArrayList<ProductoMenu> itemsCombo;
	private String facturaEsperada;

	@BeforeEach
	public void setup() {
		this.corral = new ProductoMenu("corral", 14000);
		this.papas = new ProductoMenu("papas medianas", 5500);
		this.gaseosa = new ProductoMenu("gaseosa", 5000);
		
		this.itemsCombo = new ArrayList<>();
		this.itemsCombo.add(corral);
		this.itemsCombo.add(papas);
		this.itemsCombo.add(gaseosa);
		
		this.facturaEsperada = "Combo combo corralazo\n" +
							   " Descuento: 0.1\n" +
							   "            22050\n";
		
		combo = new Combo("combo corralazo", 0.10, this.itemsCombo);
	}
	
    @Test
    public void getNombreTest() {
        assertEquals("combo corralazo", combo.getNombre(), "El nombre del combo no es el esperado");
    }

    @Test
    public void getPrecioTest() {
        // (14000 + 5500 + 5000) * (1 - 0.10) = 24500 * 0.90 = 22050
        assertEquals(22050, combo.getPrecio(), "El precio del combo no es el esperado");
    }

    @Test
    public void generarTextoFacturaTest() {

        assertEquals(this.facturaEsperada, combo.generarTextoFactura(), "La factura generada no es la esperada");
    }
	
	
	
}

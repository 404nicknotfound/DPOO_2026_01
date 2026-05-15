import urllib.request
import json
import sys

puml_code = """
@startuml
skinparam classAttributeIconSize 0
skinparam monochrome true
skinparam shadowing false

package uniandes.dpoo.swing.mundo {
    class Restaurante {
        - nombre : String
        - calificacion : int
        - coordenadaX : int
        - coordenadaY : int
        + visitado : boolean
        + Restaurante(nombre: String, calificacion: int, x: int, y: int, visitado: boolean)
        + getNombre() : String
        + getCalificacion() : int
        + getX() : int
        + getY() : int
        + isVisitado() : boolean
        + toString() : String
    }

    class Diario {
        - restaurantes : List<Restaurante>
        + Diario()
        + getRestaurantes(completos: boolean) : List<Restaurante>
        + agregarRestaurante(restaurante: Restaurante) : void
    }
}

package uniandes.dpoo.swing.interfaz.principal {
    class VentanaPrincipal {
        - mundo : Diario
        - pBotones : PanelBotones
        - pDetalles : PanelDetallesRestaurante
        - pLista : PanelLista
        - ventanaMapa : VentanaMapa
        - ventanaAgregar : VentanaAgregarRestaurante
        + VentanaPrincipal(elDiario: Diario)
        + mostrarVetanaNuevoRestaurante() : void
        + mostrarVentanaMapa() : void
        + agregarRestaurante(nombre: String, calificacion: int, x: int, y: int, visitado: boolean) : void
        + getRestaurantes(completos: boolean) : List<Restaurante>
        - actualizarRestaurantes() : void
        + cambiarRestauranteSeleccionado(seleccionado: Restaurante) : void
        + {static} main(args: String[]) : void
    }

    class PanelBotones {
        - ventanaPrincipal : VentanaPrincipal
        + PanelBotones(ventanaPrincipal: VentanaPrincipal)
        + actionPerformed(e: ActionEvent) : void
    }

    class PanelDetallesRestaurante {
        - labNombre : JLabel
        - labCalificacion : JLabel
        - labVisitado : JLabel
        - chkVisitado : JCheckBox
        + PanelDetallesRestaurante()
        + actualizarRestaurante(seleccionado: Restaurante) : void
    }

    class PanelLista {
        - ventanaPrincipal : VentanaPrincipal
        - listaDeRestaurantes : JList<Restaurante>
        + PanelLista(ventanaPrincipal: VentanaPrincipal)
        + valueChanged(e: ListSelectionEvent) : void
        + actualizarRestaurantes(restaurantes: List<Restaurante>) : void
        + seleccionarRestaurante(restaurante: Restaurante) : void
    }
}

package uniandes.dpoo.swing.interfaz.mapa {
    class VentanaMapa {
        - ventanaPrincipal : VentanaPrincipal
        - panelMapa : PanelMapaVisualizar
        + VentanaMapa(ventanaPrincipal: VentanaPrincipal, restaurantes: List<Restaurante>)
        + actionPerformed(e: ActionEvent) : void
    }

    class PanelMapaVisualizar {
        - labMapa : JLabel
        - restaurantes : List<Restaurante>
        + PanelMapaVisualizar(restaurantes: List<Restaurante>)
        + paint(g: Graphics) : void
        + actualizarMapa(restaurantes: List<Restaurante>) : void
    }
}

package uniandes.dpoo.swing.interfaz.agregar {
    class VentanaAgregarRestaurante {
        - ventanaPrincipal : VentanaPrincipal
        - panelBotones : PanelBotonesAgregar
        - panelDetalles : PanelEditarRestaurante
        - panelMapa : PanelMapaAgregar
        + VentanaAgregarRestaurante(principal: VentanaPrincipal)
        + agregarRestaurante() : void
        + cerrarVentana() : void
    }

    class PanelBotonesAgregar {
        - ventanaPrincipal : VentanaAgregarRestaurante
        + PanelBotonesAgregar(ventanaPrincipal: VentanaAgregarRestaurante)
        + actionPerformed(e: ActionEvent) : void
    }

    class PanelEditarRestaurante {
        - cbbCalificacion : JComboBox<Integer>
        - txtNombre : JTextField
        - chkVisitado : JCheckBox
        + PanelEditarRestaurante()
        + getVisitado() : boolean
        + getCalificacion() : int
        + getNombre() : String
    }

    class PanelMapaAgregar {
        - labMapa : JLabel
        - x : int
        - y : int
        + PanelMapaAgregar()
        + retornarX() : int
        + retornarY() : int
        + mouseClicked(e: MouseEvent) : void
    }
}

Diario "1" o--> "*" Restaurante : restaurantes

VentanaPrincipal "1" *--> "1" Diario : mundo
VentanaPrincipal "1" *--> "1" PanelBotones : pBotones
VentanaPrincipal "1" *--> "1" PanelDetallesRestaurante : pDetalles
VentanaPrincipal "1" *--> "1" PanelLista : pLista
VentanaPrincipal "1" *--> "0..1" VentanaMapa : ventanaMapa
VentanaPrincipal "1" *--> "0..1" VentanaAgregarRestaurante : ventanaAgregar

PanelBotones "1" --> "1" VentanaPrincipal : ventanaPrincipal
PanelLista "1" --> "1" VentanaPrincipal : ventanaPrincipal
VentanaMapa "1" --> "1" VentanaPrincipal : ventanaPrincipal
VentanaAgregarRestaurante "1" --> "1" VentanaPrincipal : ventanaPrincipal

VentanaMapa "1" *--> "1" PanelMapaVisualizar : panelMapa
PanelMapaVisualizar "1" --> "*" Restaurante : restaurantes

VentanaAgregarRestaurante "1" *--> "1" PanelBotonesAgregar : panelBotones
VentanaAgregarRestaurante "1" *--> "1" PanelEditarRestaurante : panelDetalles
VentanaAgregarRestaurante "1" *--> "1" PanelMapaAgregar : panelMapa

PanelBotonesAgregar "1" --> "1" VentanaAgregarRestaurante : ventanaPrincipal

@enduml
"""

data = json.dumps({
    "diagram_source": puml_code,
    "diagram_type": "plantuml",
    "output_format": "pdf"
}).encode('utf-8')

req = urllib.request.Request("https://kroki.io", data=data, headers={'Content-Type': 'application/json', 'User-Agent': 'Mozilla/5.0'})
try:
    with urllib.request.urlopen(req) as response:
        pdf_data = response.read()
        with open("Diagrama_Clases_UML.pdf", "wb") as f:
            f.write(pdf_data)
    print("PDF generated successfully.")
except Exception as e:
    print(f"Error generating PDF: {e}")
    sys.exit(1)

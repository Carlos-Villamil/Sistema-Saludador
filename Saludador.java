import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Sistema Saludador - Aplicación de escritorio (Java + JavaFX).
 *
 * Caso de uso: "Solicitar saludo" (incluye "Pedir datos").
 *  1. El estudiante hace clic en "Solicitar saludo".
 *  2. El sistema solicita nombre, edad y hora (AM/PM).
 *  3. El estudiante ingresa lo solicitado.
 *  4. El sistema saluda al estudiante por su nombre y menciona su edad.
 */
public class Saludador extends Application {

    private Label lblResultado;

    @Override
    public void start(Stage ventana) {
        Label titulo = new Label("Sistema Saludador");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Button btnSolicitar = new Button("Solicitar saludo");
        btnSolicitar.setStyle("-fx-font-size: 14px;");
        btnSolicitar.setOnAction(e -> pedirDatos(ventana));

        lblResultado = new Label("Presiona el botón para recibir tu saludo.");
        lblResultado.setWrapText(true);
        lblResultado.setStyle("-fx-font-size: 15px;");

        VBox raiz = new VBox(20, titulo, btnSolicitar, lblResultado);
        raiz.setAlignment(Pos.CENTER);
        raiz.setPadding(new Insets(30));

        ventana.setTitle("Sistema Saludador");
        ventana.setScene(new Scene(raiz, 480, 260));
        ventana.show();
    }

    /** Caso de uso incluido: "Pedir datos" (nombre, edad, hora AM/PM). */
    private void pedirDatos(Stage dueno) {
        Stage dialogo = new Stage();
        dialogo.initOwner(dueno);
        dialogo.initModality(Modality.APPLICATION_MODAL);
        dialogo.setTitle("Pedir datos");

        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Ej: Laura");

        TextField txtEdad = new TextField();
        txtEdad.setPromptText("Ej: 20");

        TextField txtHora = new TextField();
        txtHora.setPromptText("Hora (1-12), Ej: 7");
        txtHora.setPrefWidth(120);

        ComboBox<String> cmbJornada = new ComboBox<>();
        cmbJornada.getItems().addAll("AM", "PM");
        cmbJornada.setValue("AM");

        HBox filaHora = new HBox(10, txtHora, cmbJornada);

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(12);
        form.setPadding(new Insets(20));
        form.addRow(0, new Label("Nombre:"), txtNombre);
        form.addRow(1, new Label("Edad:"), txtEdad);
        form.addRow(2, new Label("Hora:"), filaHora);

        Button btnAceptar = new Button("Aceptar");
        Button btnCancelar = new Button("Cancelar");
        HBox botones = new HBox(10, btnAceptar, btnCancelar);
        botones.setAlignment(Pos.CENTER_RIGHT);
        botones.setPadding(new Insets(0, 20, 20, 20));

        btnCancelar.setOnAction(e -> dialogo.close());
        btnAceptar.setOnAction(e -> {
            String nombre = txtNombre.getText().trim();
            int edad;
            int hora;

            if (nombre.isEmpty()) {
                error("Debes ingresar tu nombre.");
                return;
            }
            try {
                edad = Integer.parseInt(txtEdad.getText().trim());
                if (edad < 0 || edad > 120) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                error("La edad debe ser un número entre 0 y 120.");
                return;
            }
            try {
                hora = Integer.parseInt(txtHora.getText().trim());
                if (hora < 1 || hora > 12) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                error("La hora debe ser un número entre 1 y 12.");
                return;
            }

            lblResultado.setText(saludar(nombre, edad, hora, cmbJornada.getValue()));
            dialogo.close();
        });

        VBox contenido = new VBox(form, botones);
        dialogo.setScene(new Scene(contenido));
        dialogo.setResizable(false);
        dialogo.showAndWait();
    }

    /** Construye el saludo según la hora y menciona la edad. */
    private String saludar(String nombre, int edad, int hora, String jornada) {
        String momento;
        if (jornada.equals("AM")) {
            momento = "Buenos días";
        } else if (hora == 12 || hora <= 5) {
            momento = "Buenas tardes";
        } else {
            momento = "Buenas noches";
        }
        String anios = (edad == 1) ? "año" : "años";
        return momento + ", " + nombre + "! Son las " + hora + " " + jornada
                + " y tienes " + edad + " " + anios + ".";
    }

    private void error(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING, mensaje);
        alerta.setHeaderText("Datos inválidos");
        alerta.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

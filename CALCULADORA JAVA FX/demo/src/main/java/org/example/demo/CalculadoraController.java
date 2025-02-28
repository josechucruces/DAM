package org.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class CalculadoraController {
    @FXML
    private TextField pantalla;

    private double operando1 = 0;
    private String operador = "";
    private boolean nuevoNumero = true;

    @FXML
    private void agregarNumero(ActionEvent event) {
        String valor = ((Button) event.getSource()).getText();

        if (nuevoNumero) {
            pantalla.setText(valor);
            nuevoNumero = false;
        } else {
            pantalla.setText(pantalla.getText() + valor);
        }
    }

    @FXML
    private void operar(ActionEvent event) {
        operando1 = Double.parseDouble(pantalla.getText());
        operador = ((Button) event.getSource()).getText();
        nuevoNumero = true;
    }

    @FXML
    private void calcular() {
        double operando2 = Double.parseDouble(pantalla.getText());
        double resultado = 0;

        switch (operador) {
            case "+": resultado = operando1 + operando2; break;
            case "-": resultado = operando1 - operando2; break;
            case "*": resultado = operando1 * operando2; break;
            case "/":
                if (operando2 != 0) {
                    resultado = operando1 / operando2;
                } else {
                    pantalla.setText("Error");
                    return;
                }
                break;
        }
        pantalla.setText(String.valueOf(resultado));
        nuevoNumero = true;
    }

    @FXML
    private void limpiar() {
        pantalla.setText("");
        operando1 = 0;
        operador = "";
        nuevoNumero = true;
    }
}

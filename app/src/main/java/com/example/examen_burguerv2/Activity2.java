package com.example.examen_burguerv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    private EditText edt_codigo;
    private TextView txt_hamburguesa, txt_complemento, txt_cupon, txt_iva, txt_registrado, txt_total;

    private String burguer;
    private String complemento1;
    private String complemento2;

    private double precioBurger;
    private double precioComp1;
    private double precioComp2;

    private String registrado;
    private int cupon = 0;
    private final double valorIva = 0.21;
    private double total;
    private int valorRegistrado = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        edt_codigo = (EditText) findViewById(R.id.edt_codigo);
        txt_hamburguesa = (TextView) findViewById(R.id.txt_hamburguesa);
        txt_complemento = (TextView) findViewById(R.id.txt_complemento);
        txt_cupon = (TextView) findViewById(R.id.txt_cupon);
        txt_iva = (TextView) findViewById(R.id.txt_iva);
        txt_registrado = (TextView) findViewById(R.id.txt_registrado);
        txt_total = (TextView) findViewById(R.id.txt_total);

        Intent intent = getIntent();
        if (intent != null) {
            burguer = intent.getStringExtra(MainActivity.EXTRA_HAMBURGUESA);
            complemento1 = intent.getStringExtra(MainActivity.EXTRA_COMPLEMENTO1);
            complemento2 = intent.getStringExtra(MainActivity.EXTRA_COMPLEMENTO2);
            registrado = intent.getStringExtra(MainActivity.EXTRA_REGISTRADO);

            precioBurger = Double.parseDouble(burguer);
            precioComp1 = Double.parseDouble(complemento1);
            precioComp2 = Double.parseDouble(complemento2);
        }
        actualizarValores();
    }

    private void actualizarValores() {
        txt_hamburguesa.setText("Hamburguesa: " + precioBurger + " €");
        txt_complemento.setText("Complementos: " + (precioComp1 + precioComp2) + " €");
        txt_cupon.setText("Cupon: " + cupon + " €");
        double iva = (precioBurger + precioComp1 + precioComp2) * valorIva;
        txt_iva.setText("iva: " + iva + " €");
        if(registrado.equals("true")){
            txt_registrado.setText("Usuario registrado: +2 € de descuento");
            valorRegistrado = 2;
        }else if(registrado.equals("false")){
            txt_registrado.setText("Usuario NO registrado: Sin promo");
            valorRegistrado = 0;
        }
        total = ((precioBurger + precioComp1 + precioComp2) - cupon - valorRegistrado) * 1.21;
        txt_total.setText("Total: " + total + " €");


    }

    public void aplicar(View view) {
        String codigo = edt_codigo.getText().toString();
        if (codigo.equalsIgnoreCase("mac123")){
            cupon = 1;
        }
        actualizarValores();
        ocultarTeclado(this);
    }

    public void ocultarTeclado(Activity activity)
    {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService (Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow (activity.getCurrentFocus (). getWindowToken (), 0);
    }
}
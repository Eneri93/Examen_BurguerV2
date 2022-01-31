package com.example.examen_burguerv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_HAMBURGUESA = "miBurguer";
    public static final String EXTRA_COMPLEMENTO1 = "miComplemento1";
    public static final String EXTRA_COMPLEMENTO2 = "miComplemento2";
    public static final String EXTRA_REGISTRADO = "miRegistrado";

    private Spinner sp_burguer;
    private RadioButton rad_si, rad_no;
    private CheckBox check_patatas, check_cola;

    private String burguer;
    private String complemento1 = "0";
    private String complemento2 = "0";

    private final String precioPollo = "3";
    private final String precioXL = "5";
    private final String precioCola = "2.5";
    private final String precioPatatas = "2";

    private String registrado = "true";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp_burguer = (Spinner) findViewById(R.id.sp_burguer);
        rad_si = (RadioButton) findViewById(R.id.rad_si);
        rad_no = (RadioButton) findViewById(R.id.rad_no);
        check_cola = (CheckBox) findViewById(R.id.check_cola);
        check_patatas = (CheckBox) findViewById(R.id.check_patatas);

        if(sp_burguer != null) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.array_Burguer, R.layout.combo);
            sp_burguer.setAdapter(adapter);
            sp_burguer.setOnItemSelectedListener(this);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        burguer = adapterView.getItemAtPosition(i).toString();

        if(burguer.equals("Mc Pollo")){
            burguer = precioPollo;
        }
        if(burguer.equals("XXL")){
            burguer = precioXL;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void clienteRegistrado(View view){
        RadioButton rb1 = (RadioButton) view;
        if(rb1.isChecked()){
            switch (rb1.getId()){
                case R.id.rad_si:
                    registrado = "true";
                    break;
                case R.id.rad_no:
                    registrado = "false";
                    break;
            }
        }
    }

    public void checkComplemento(View view){
        CheckBox ch1 = (CheckBox) view;
        if(ch1.isChecked()){
            switch (ch1.getId()){
                case R.id.check_cola:
                    complemento1 = precioCola;
                    break;
                case R.id.check_patatas:
                    complemento2 = precioPatatas;
                    break;
            }
        }else if(!ch1.isChecked()){
            switch (ch1.getId()){
                case R.id.check_cola:
                    complemento1 = "0";
                    break;
                case R.id.check_patatas:
                    complemento2 = "0";
                    break;
            }
        }
    }

    public void siguiente(View view) {
        Intent intent = new Intent(this,Activity2.class);
        intent.putExtra(EXTRA_HAMBURGUESA,burguer);
        intent.putExtra(EXTRA_COMPLEMENTO1,complemento1);
        intent.putExtra(EXTRA_COMPLEMENTO2,complemento2);
        intent.putExtra(EXTRA_REGISTRADO,registrado);
        startActivity(intent);
    }
}
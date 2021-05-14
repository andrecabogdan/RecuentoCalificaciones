package com.example.recuentocalificacionesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class IntroducirAsignaturas extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText nombreAsgiantura, calificacionAsignatura;
    Button registrarAsginatura;

    private String nombre="";
    private int calificacion=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducir_asignaturas);

        nombreAsgiantura = (EditText) findViewById(R.id.edittxt_nombre_asginatura);
        calificacionAsignatura = (EditText) findViewById(R.id.edittxt_calificacion_asginatura);
        registrarAsginatura = (Button) findViewById(R.id.btn_crear_asginatura);

        databaseHelper = new DatabaseHelper(this);

        registrarAsginatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = nombreAsgiantura.getText().toString();
                calificacion = Integer.parseInt(calificacionAsignatura.getText().toString());
                if(nombre.equals("")){
                    toastMessage("Falta introducir el nombre!");
                }else if(calificacion==-1){
                    toastMessage("Falta introducir la calificacion!");
                }else {
                    addData(nombre, calificacion);
                    nombreAsgiantura.setText("");
                    calificacionAsignatura.setText("");
                    calificacion=-1;
                    nombre="";
                }
                finish();
            }
        });


    }
    public void addData(String nombreFinal, int calificacionFinal){
        boolean insertarDatos = databaseHelper.addData(nombreFinal,calificacionFinal);
        if(insertarDatos){
            Toast.makeText(this, "La asignatura se ha registrado correctamente.", LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Los datos no se han podido registrar corretamente.", LENGTH_SHORT).show();
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
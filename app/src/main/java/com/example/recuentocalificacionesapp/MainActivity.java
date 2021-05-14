package com.example.recuentocalificacionesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnIntroducirAsignatura, btnVisualizarAsiganturas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIntroducirAsignatura=(Button) findViewById(R.id.btn_introducir_asignaturas);
        btnVisualizarAsiganturas=(Button) findViewById(R.id.btn_visualizar_asignaturas);

        btnIntroducirAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,IntroducirAsignaturas.class);
                startActivity(intent);
            }
        });

        btnVisualizarAsiganturas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,VisualizarAsignaturas.class);
                startActivity(intent);
            }
        });
    }
}
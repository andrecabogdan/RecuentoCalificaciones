package com.example.recuentocalificacionesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

public class VisualizarAsignaturas extends AppCompatActivity {

    HashMap<String, Integer> listaAsignaturas = new HashMap<>();
    DatabaseHelper databaseHelper;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_asignaturas);
        listView = (ListView) findViewById(R.id.lista_asgianturas);
        databaseHelper = new DatabaseHelper(this);
        mostrarListaAsignaturas();

    }

    public void mostrarListaAsignaturas() {
        //Creamos un hashmap donde guardaremos y separamos los datos en brutos obtenidos de la base de datos para poder mostrarlos por pantalla

        //Creamos un objeto de tipo cursor donde guardaremos los datos obtenidos del metodo getData() de la clase DatabaseHelper
        //dado que es el mismo tipo de objeto que utilizamos dentro de dicha clase
        Cursor data = databaseHelper.getData();
        //Creamos un loop donde recorremos todos los datos obtenidos de la base de datos que necesitamos mostrar dentro de la lista
        while (data.moveToNext()) {
            //Ahora ingresamos los valores a nuestro hashmap obteniendo mediante getString(1) el valor del nombre y mediante getInt(2) el valor de la calificacion
            listaAsignaturas.put(data.getString(1), data.getInt(2));
        }

        //Ahora creamos un adaptador para adaptar los datos conseguidos anteriormente a la listview para que se puedan mostrar de forma correcta por pantalla
        HashMapAdapter adapter = new HashMapAdapter(this, listaAsignaturas);
        listView.setAdapter(adapter);
    }
}
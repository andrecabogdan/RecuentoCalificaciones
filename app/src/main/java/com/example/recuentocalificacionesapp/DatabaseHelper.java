package com.example.recuentocalificacionesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.sql.SQLOutput;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String nombreTabla = "Asignaturas";
    private static final String columna1 = "id";
    private static final String columna2 = "nombres";
    private static final String columna3 = "calificaciones";

    public DatabaseHelper(Context context) {
        super(context, nombreTabla, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + nombreTabla + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                columna2 + " TEXT, " + columna3 + " INTEGER)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + nombreTabla);
        onCreate(sqLiteDatabase);
    }

    //Creamos un metodo para añadir datos a la base de datos
    public boolean addData(String nombre, Integer calificacion){
        //Primero creamos un objeto de sql database
        //Para declarar los objetos de la base de datos utilizamos getWritableDatabase dentro del contexto
        SQLiteDatabase db = this.getWritableDatabase();
        //Lo segundo sera crear un objeto de tipo ContentValues que nos permitira escribir dentro de la base de datos
        ContentValues contentValues = new ContentValues();
        //Y ahora añadiremos los valores al objeto contentValues para que se pueda escribir en la base de datos
        contentValues.put(columna2, nombre);
        contentValues.put(columna3, calificacion);

        //Creamos un log para observar si los datos se han introducido correctamente
        Log.d(TAG, "addData: Introducimos " + nombre + " y " + calificacion + " a " + nombreTabla);
        //Y por ultimo insetamos dentro de la tabla el objeto contentValues que contiene los valores de las columnas especificas
        long result = db.insert(nombreTabla,null, contentValues);

        //Ahora comprobamos de si se ha introducido correctamente los datos en la base de datos
        if(result == -1 ) {
            return false;
        }else{
            return true;
        }
    }

    //Metodo mediante el cual obtenemos los datos de la base de datos de tipo Cursor
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String consulta = "SELECT * FROM " + nombreTabla;
        Cursor data = db.rawQuery(consulta, null);
        return data;
    }

    public Integer deleteData(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        int result=db.delete(nombreTabla, columna2+" =?",new String[] {ID});
        return result;
    }

}

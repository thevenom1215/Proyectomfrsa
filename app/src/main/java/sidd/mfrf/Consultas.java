package sidd.mfrf;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class Consultas extends Activity {

//declara los distintos tipos de graficos que tendra la pantalla
    EditText nombre,apellidoP,apellidoM,id_prospec,calle1,calle2,no_de_col;

    TextView datos;

    Button consultar;

//declara los distintos tipos de objetos que se requeriran para el programa
    SQLiteOpenHelper ayuda;
    SQLiteDatabase database;
    String consulta;
    Cursor cursor;

    List lista = new ArrayList<>();

//se inicia el metodo principal
        public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.busqueda_prospectos);

            //se crea la relacion entre el contenido grafico y el codigo declarado anteriormente

            nombre = (EditText)findViewById(R.id.nombre);
            apellidoP=(EditText)findViewById(R.id.apellidoP);
            apellidoM=(EditText)findViewById(R.id.apellidoM);
            id_prospec=(EditText)findViewById(R.id.id_prospec);
            calle1=(EditText)findViewById(R.id.calle1);
            calle2=(EditText)findViewById(R.id.calle2);
            no_de_col=(EditText)findViewById(R.id.no_de_col);

            datos = (TextView)findViewById(R.id.datos);

            consultar=(Button)findViewById(R.id.consultar);

            //se crea una instancia de la base de datos y se obtiene su version editable
            ayuda = new ConexionDB(this);
            database = ayuda.getWritableDatabase();

            //inicia la accion del boton consultar
        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    //inicia las comparaciones
                    if(id_prospec.getText().toString().isEmpty()){

                        if(nombre.getText().toString().isEmpty()){

                            if(calle1.getText().toString().isEmpty()){
                                //muestra mensaje de que no se ha puesto ningun dato para comparar y lo muestra
                                Toast.makeText(v.getContext(),"No pusiste ningun dato",Toast.LENGTH_LONG).show();}

                            else{ //genera el string de la consulta en base a los datos de la calles e id colonia
                                consulta = "select * from prospectos where calle1 = '"+calle1.getText().toString()+
                            "' and calle2 = '"+calle2.getText().toString()+"' and id_colonia = '"+no_de_col.getText().toString()+"'";}
                        }
                        else{//genera el string de la consulta en base a los datos proporcionados por el nombre y los apellidos
                            consulta = "select * from prospectos where nomprospec = '"+ nombre.getText().toString()+"' AND ap_prospec ='"+
                                apellidoP.getText().toString()+"' AND amprospec ='"+apellidoM.getText().toString()+"'";}
                    }
                    else {//genera el string de la consulta en base al id prospec
                        consulta = "select * from prospectos where Idprospec = " + id_prospec.getText().toString();}

                    //ejecuta la consulta con base al string
                    cursor = database.rawQuery(consulta,null);
                    int i=0;

                    //mientras el cursor  se mueva obtiene todos los datos que coincidan con la consulta realizada
                    while(cursor.moveToNext()){
                        //agrega a una lista cada dato que coincida con los datos que se encuentren en la base de datos local
                        lista.add(i,cursor.getString(4)+" "+cursor.getString(5)+" "+cursor.getString(6)
                                +"\n"+cursor.getString(11)+"\n y "+cursor.getString(12)+" \nno. exterior:"+cursor.getString(14)+"\nno. interior" + cursor.getString(13)
                                +"\n entre calles: "+cursor.getString(15)+"\nNo de colonia:"+ cursor.getInt(18));
                        i++;}
                    //se pone en un texview la lista en el cual se mostrara
                    datos.setText(lista.toString());
                    //se muestra un mensaje de que la consulta fue realizada con exito
                    Toast.makeText(v.getContext(),"Consulta realizada",Toast.LENGTH_SHORT).show();
                }catch(SQLiteException ex){Toast.makeText(v.getContext(),ex.getMessage(),Toast.LENGTH_LONG).show();}
            }
        });
        }

}
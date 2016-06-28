package sidd.mfrf;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class Consultas extends Activity {

//declara los distintos tipos de graficos que tendra la pantalla
    EditText nombre,apellidoP,apellidoM,id_prospec,no_de_col;

    TextView datos;

    Button consultar;

//declara los distintos tipos de objetos que se requeriran para el programa
    SQLiteOpenHelper ayuda;
    SQLiteDatabase database;
    String consulta;
    Cursor cursor;
    ListView verlista;
    ArrayAdapter ladaptable;
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
            no_de_col=(EditText)findViewById(R.id.no_de_col);
            verlista=(ListView)findViewById(R.id.lista);
            final ArrayList <String> datos = new ArrayList<String>();

            consultar=(Button)findViewById(R.id.consultar);

            //se crea una instancia de la base de datos y se obtiene su version editable
            ayuda = new ConexionDB(this);
            database = ayuda.getWritableDatabase();

            //obtiene la manera de despliegue de la lista
            ladaptable= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datos);

            //inicia la accion del boton consultar

            consultar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                try {
                    //inicia las comparaciones
                    if(id_prospec.getText().toString().isEmpty()){
                        if (apellidoM.getText().toString().isEmpty()){
                            if (apellidoP.getText().toString().isEmpty()) {
                                if (nombre.getText().toString().isEmpty()) {
                                    if (no_de_col.getText().toString().isEmpty()) {
                                    //muestra mensaje de que no se ha puesto ningun dato para comparar y lo muestra
                                    Toast.makeText(v.getContext(), "No pusiste ningun dato", Toast.LENGTH_LONG).show();}
                                    else { //genera el string de la consulta en base a los datos de la calles e id colonia
                                    consulta = "select * from prospectos where id_colonia = '" + no_de_col.getText().toString() + "'";
                                    }
                            } else { consulta ="select * from prospectos where nomprospec = '"+nombre.getText()+"'";}
                        }else{consulta = "select * from prospecto where nompropec = '"+nombre.getText()+"' and ap_prospec ='"+apellidoP.getText()+"'";}
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
                    //           No     Nombre               Apellido paterno        Apellido Materno
                        datos.add(i,"Id prospecto "+cursor.getString(3)+"\n"+cursor.getString(4)+" "+cursor.getString(5)+" "+cursor.getString(6)
                                +"\n"+cursor.getString(11)+"\n y "+cursor.getString(12)+" \nno. exterior: "+cursor.getString(14)+"\nno. interior " + cursor.getString(13)
                                +"\nentre calles: "+cursor.getString(15)+"\nNo de colonia:"+ cursor.getInt(18));

                        i++;
                    }

                    //se pone en una lista en el cual se mostrara

                    verlista.setAdapter(ladaptable);

                    //se muestra un mensaje de que la consulta fue realizada con exito
                    Toast.makeText(v.getContext(),"Consulta realizada",Toast.LENGTH_SHORT).show();
                }catch(SQLiteException ex){Toast.makeText(v.getContext(),ex.getMessage(),Toast.LENGTH_LONG).show();}
            }
        });

verlista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //String texto = (verlista.getItemAtPosition(position)).toString();
       //Toast.makeText(view.getContext(),texto, Toast.LENGTH_LONG).show();
        conexion abre = new conexion();
        abre.setConsulta(consulta);
       // Toast.makeText(view.getContext(),abre.getConsulta(),Toast.LENGTH_LONG).show();
        Intent modfic = new Intent(view.getContext(),Registro.class);
        startActivity(modfic);
    }
});

        }

}
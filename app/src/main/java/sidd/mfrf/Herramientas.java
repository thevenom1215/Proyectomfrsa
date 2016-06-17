package sidd.mfrf;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Herramientas extends Activity {
  //se crean los tipos de datos que se van a requerir en el programa
    Button actualizar;
    String Sarchivo="datos.txt";
    File archivo;
    SQLiteDatabase database;
    SQLiteOpenHelper ayudadb;
    Cursor cursor;

//inicia el metodo principal
public void onCreate(Bundle savedInstance){
    super.onCreate(savedInstance);
    setContentView(R.layout.herramientas);

//se hace la relacion entre la parte grafica del programa y el codigo
    actualizar=(Button)findViewById(R.id.actualizar);
// se hace una instancia con respecto a la base de datos local
    ayudadb = new ConexionDB(this);
    database = ayudadb.getWritableDatabase();

    //se inicia la accion que llevara el boton de actualizar
    actualizar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
//se crea un archivo de texto que se llamara Sarchivo
                archivo = new File(Sarchivo);
                Toast.makeText(v.getContext(), "Archivo creado", Toast.LENGTH_SHORT).show();
                //se llama al metodo escribe archivo
                escribe_archivo();}
            //se crea la excepcion para errores fatales
            catch (IOException ex) {
                Toast.makeText(v.getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    });
}
    //Inicia el metodo escribe archivo
    public void escribe_archivo()throws IOException{

        //se realiza una consulta a la base de datos que nos traera el contenido de la base de datos
        cursor= database.rawQuery("select * from prospectos",null);

        //se realiza que el archivo sea editable
        FileOutputStream fos= openFileOutput(Sarchivo,MODE_PRIVATE);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
//se escribira el archivo lo realizado en la consulta a la base de datos
        while(cursor.moveToNext()){
            for(int i=0;i<28;i++){
                osw.write(cursor.getString(i)+",");}
        }
        osw.flush();
        osw.close();
        //lanza un mensaje al usuario de que el archivo fue escrito con exito
        Toast.makeText(this,"Archivo escrito",Toast.LENGTH_SHORT).show();
    }
}

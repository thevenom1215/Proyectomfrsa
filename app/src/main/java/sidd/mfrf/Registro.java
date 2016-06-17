package sidd.mfrf;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Registro extends Activity {

    //se declaran los componentes graficos (con el mismo nombre en este caso)
    EditText
            cve_compra,estatus,
            id_pros,cve_pros,
            nombre,apellidoP,apellidoM,
            rfc,
            tel_casa,tel_ofic,cel,
            calle1,calle2,
            no_int,no_ext,
            ent_call,cod_post,
            correo,
            id_col,id_loc,id_cd,id_mnp,
            id_est,id_pais,
            id_vend,cve_vend,
            fecha_act_reg,usr_reg;

    Button enviar;
    //se declara los objetos para uso de la base de datos

    SQLiteOpenHelper ayuda_db;
    SQLiteDatabase database;
    ContentValues valores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        //se relacionan los componentes de la pantalla con los declarados
        cve_compra =(EditText)findViewById(R.id.cve_compra);
        estatus =(EditText)findViewById(R.id.estatus);
        id_pros =(EditText)findViewById(R.id.id_prospecto);
        cve_pros =(EditText)findViewById(R.id.cve_prospecto);
        nombre =(EditText)findViewById(R.id.nombre_prospecto);
        apellidoP=(EditText)findViewById(R.id.prospecto_paterno);
        apellidoM=(EditText)findViewById(R.id.prospecto_materno);
        rfc=(EditText)findViewById(R.id.prospecto_rfc);
        tel_casa=(EditText)findViewById(R.id.tel_casa);
        tel_ofic=(EditText)findViewById(R.id.tel_ofic);
        cel=(EditText)findViewById(R.id.celular);
        calle1=(EditText)findViewById(R.id.calle1);
        calle2=(EditText)findViewById(R.id.calle2);
        no_int=(EditText)findViewById(R.id.no_interior);
        no_ext=(EditText)findViewById(R.id.no_exterior);
        ent_call=(EditText)findViewById(R.id.entre_calles);
        cod_post=(EditText)findViewById(R.id.cod_post);
        correo=(EditText)findViewById(R.id.corr_elec);
        id_col=(EditText)findViewById(R.id.colonia);
        id_loc=(EditText)findViewById(R.id.localidad);
        id_cd=(EditText)findViewById(R.id.ciudad);
        id_mnp=(EditText)findViewById(R.id.municipio);
        id_est=(EditText)findViewById(R.id.estado);
        id_pais=(EditText)findViewById(R.id.pais);
        id_vend=(EditText)findViewById(R.id.id_vendedor);
        cve_vend=(EditText)findViewById(R.id.clave_vendedor);
        fecha_act_reg=(EditText)findViewById(R.id.fecha_reg);
        usr_reg=(EditText)findViewById(R.id.usuario_reg);
        enviar=(Button)findViewById(R.id.enviar);

        //se agrega contexto a la base de datos y se establece que sera editable
        ayuda_db = new ConexionDB(this);
        database = ayuda_db.getWritableDatabase();

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //se declaran los valores que llevara la base de datos y se relacionan con las columnas
                valores= new ContentValues();
                try{
                valores.put("cve_comp",cve_compra.getText().toString());
                valores.put("estatus",estatus.getText().toString());
                valores.put("cveprospec",cve_pros.getText().toString());
                valores.put("nomprospec",nombre.getText().toString());
                valores.put("ap_prospec",apellidoP.getText().toString());
                valores.put("amprospec",apellidoM.getText().toString());
                valores.put("rfc",rfc.getText().toString());
                valores.put("telefcasa",tel_casa.getText().toString());
                valores.put("teloficina",tel_ofic.getText().toString());
                valores.put("celular",cel.getText().toString());
                valores.put("calle1",calle1.getText().toString());
                valores.put("calle2",calle2.getText().toString());
                valores.put("no_int",no_int.getText().toString());
                valores.put("numext",no_ext.getText().toString());
                valores.put("entrecalles",ent_call.getText().toString());
                valores.put("codpost",cod_post.getText().toString());
                valores.put("correlec",correo.getText().toString());
                valores.put("id_colonia",Integer.parseInt(id_col.getText().toString()));
                valores.put("id_localidad",Integer.parseInt(id_loc.getText().toString()));
                valores.put("n_ciudad",Integer.parseInt(id_cd.getText().toString()));
                valores.put("idmunicipio",Integer.parseInt(id_mnp.getText().toString()));
                valores.put("n_estado",Integer.parseInt(id_est.getText().toString()));
                valores.put("id_pais",Integer.parseInt(id_pais.getText().toString()));
                valores.put("id_vendedor",Integer.parseInt(id_vend.getText().toString()));
                valores.put("cvevendedor",cve_vend.getText().toString());
                valores.put("fecha_actreg",fecha_act_reg.getText().toString());
                valores.put("usractreg",usr_reg.getText().toString());

                    //pone todos los valores en la tabla
                    database.insert("prospectos",null,valores);

                    //muestra que la operacion fue exitosa al almacenar los datos
                    Toast.makeText(v.getContext(),"prospecto agregado correctamente",Toast.LENGTH_LONG).show();}

                catch(Exception e)
                {//muestra el error que ocurre en caso de que existir alguno
                    Toast.makeText(v.getContext(),e.getMessage(),Toast.LENGTH_LONG).show();}
            }
        });


    }

}

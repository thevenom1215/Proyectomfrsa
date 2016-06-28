package sidd.mfrf;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionDB extends SQLiteOpenHelper {

    //se declara el logtag, el nombre de la base de datos y la version de esta
    public static final String prospectos="Prospectos.db";
    public static final int dbversion = 1;

    //se declara el nombre de la tabla de la base de datos que tendra
    public static final String tabla_prospectos="prospectos";

    public static final String tabla_venddedores = "vendedores";

    //se declara los campos que tendra las tablas
    public static final String columna_cve_comp="cve_comp";
    public static final String columna_sts_prospec="estatus";
    public static final String columna_id_prospec="idprospec";
    public static final String columna_cve_prospec="cveprospec";
    public static final String columna_nom_prospec="nomprospec";
    public static final String columna_ap_prospec="ap_prospec";
    public static final String columna_am_prospec="amprospec";
    public static final String columna_rfc_prospec="rfc";
    public static final String columna_tel_casa="telefcasa";
    public static final String columna_tel_oficina="teloficina";
    public static final String columna_tel_cel="celular";
    public static final String columna_calle1="calle1";
    public static final String columna_calle2="calle2";
    public static final String columna_no_int="no_int";
    public static final String columna_no_ext="numext";
    public static final String columna_entre_calles="entrecalles";
    public static final String columna_cod_post="codpost";
    public static final String columna_corr_elec="correlec";
    public static final String columna_id_colonia="id_colonia";
    public static final String columna_id_localidad ="id_localidad";
    public static final String columna_id_ciudad="n_ciudad";
    public static final String columna_id_municipio="idmunicipio";
    public static final String columna_id_estado="n_estado";
    public static final String columna_id_pais="id_pais";
    public static final String columna_id_vendedor="id_vendedor";
    public static final String columna_cve_vendedor="cvevendedor";
    public static final String columna_fehca_actreg="fecha_actreg";
    public static final String columna_usr_actreg="usractreg";

    //columnas tabla vendedores
    public static final String clm_idvendedor="idvendedor";
    public static final String clm_nomvendedor="nombre";
    public static final String clm_apllp_vendedor="apellidoP";
    public static final String clm_apllm_vendedor="apellidoM";
    public static final String clm_direccion ="direccion";
    public static final String clm_telefono = "telefono";
    public static final String clm_colonia="colonia";
    public static final String clm_cp = "codigo postal";

    public ConexionDB(Context context)
    {super(context,prospectos, null, dbversion);}

    @Override
    public void onCreate(SQLiteDatabase db) {

    /*se hace el metodo SQL de como se creara la tabla declarando la CREATE TABLE
    y dentro del parentesis el nombre de la columna(s) y el tipo de dato que guardara*/
        final String crea_tabla=
                "CREATE TABLE "+tabla_prospectos+"("
                        +columna_cve_comp+" TEXT,"
                        +columna_sts_prospec+" TEXT,"
                        +columna_id_prospec +" INTEGER primary key autoincrement,"
                        +columna_cve_prospec+" TEXT,"
                        +columna_nom_prospec+" TEXT,"
                        +columna_ap_prospec+" TEXT,"
                        +columna_am_prospec+" TEXT,"
                        +columna_rfc_prospec +" TEXT,"
                        +columna_tel_casa+" TEXT,"
                        +columna_tel_oficina+" TEXT,"
                        +columna_tel_cel+" TEXT,"
                        +columna_calle1+" TEXT,"
                        +columna_calle2+" TEXT,"
                        +columna_no_int+" TEXT,"
                        +columna_no_ext+" TEXT,"
                        +columna_entre_calles+" TEXT,"
                        +columna_cod_post+" TEXT,"
                        +columna_corr_elec+" TEXT,"
                        +columna_id_colonia+" INTEGER,"
                        +columna_id_localidad+" INTEGER,"
                        +columna_id_ciudad+" INTEGER,"
                        +columna_id_municipio+" INTEGER,"
                        +columna_id_estado+" INTEGER,"
                        +columna_id_pais+" INTEGER,"
                        +columna_id_vendedor+" INTEGER,"
                        +columna_cve_vendedor+" TEXT,"
                        +columna_fehca_actreg +" TEXT,"
                        +columna_usr_actreg+" TEXT"+")";


      final String crea_vendedores="CREATE TABLE "+tabla_venddedores+"("+
              clm_idvendedor    +   " INTEGER primary key autoincrement,"+
              clm_nomvendedor   +   " TEXT,"+
              clm_apllp_vendedor+   " TEXT,"+
              clm_apllm_vendedor+   " TEXT,"+
              clm_direccion     +   " TEXT,"+
              clm_telefono      +   " TEXT,"+
              clm_colonia       +   " TEXT,"+
              clm_cp            +   " TEXT"+")";

        //se obtiene los datos de la base de datos

        //ejecuta los comandos y crea la base de datos en caso de no existir
        db.execSQL(crea_tabla);
        db.execSQL(crea_vendedores);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //hace la comparacion de si existe la base de datos
        db.execSQL("DROP TABLE IF EXIST" +tabla_prospectos);
        db.execSQL("DROP TABLE IF EXIST" + tabla_venddedores);
        onCreate(db);
    }
}

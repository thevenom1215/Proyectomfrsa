package sidd.mfrf;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;


public class Login extends Activity{
    EditText usuario,contraseña;
    Button iniciar;

    SQLiteOpenHelper ayuda;
    SQLiteDatabase database;
    String consulta;
    Cursor cursor;

    List lista = new ArrayList<>();


}

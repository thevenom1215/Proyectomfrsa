package sidd.mfrf;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class menu extends ActionBarActivity {

//inicia el metodo principal que sera sobreescrito
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    //metodo  para abrir la ventana de abrir fichas que se relaciona con boton grafico imageButton1
    public void abrir_fichas(View v){
        Intent abrir = new Intent(v.getContext(),Registro.class);
        startActivity(abrir);}

    //metodo  para abrir la ventana de abrir consultas que se relaciona con boton grafico imageButton2
    public void abrir_consultas(View v){
        Intent abrir = new Intent(v.getContext(),Consultas.class);
        startActivity(abrir);}

    //metodo  para abrir la ventana de herramientas que se relaciona con boton grafico imageButton3
    public void abrir_herramientas(View v){
        Intent abrir = new Intent(v.getContext(),Herramientas.class);
        startActivity(abrir);}

//crea el menu de opciones que viene con el dispositivo
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;}

    //genera los items del menu de opciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;}

        return super.onOptionsItemSelected(item);
    }
}

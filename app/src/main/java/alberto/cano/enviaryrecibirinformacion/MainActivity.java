package alberto.cano.enviaryrecibirinformacion;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import modelos.Direccion;
import modelos.Usuario;

public class MainActivity extends AppCompatActivity {
    private EditText txtPassword;

    private EditText eemail;
    private Button boton;

    private Button btnCrearDireccion;

    //private static final int DIRECCIONES = 123;

    private ActivityResultLauncher<Intent> launcherDirecciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarVista();
        inicializarLaunches();
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = eemail.getText().toString();
                String password = boton.getText().toString();
                if(password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Faltan Datos", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(MainActivity.this, DesencriptarAcc.class);
                    Bundle bundle = new Bundle();
                    //bundle.putString("EMAIL", email);
                    //bundle.putString("passW", password);
                    bundle.putSerializable("USER", new Usuario(email, password));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        btnCrearDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CrearDireccionActivity.class);
                //startActivityForResult(intent, DIRECCIONES);
                launcherDirecciones.launch(intent);
            }
        });
    }


    private void inicializarLaunches() {
        //Preparar como lanzar la actividad hija
        //Preparar que voy a hacer cuando la hija devuelva datos

        launcherDirecciones = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            if(result.getData() != null){
                                Bundle bundle = result.getData().getExtras();
                                Direccion direccion = (Direccion) bundle.getSerializable("DIR");
                                Toast.makeText(MainActivity.this, direccion.toString(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(MainActivity.this, "NO HAY DATOS", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "SE HA CERRADO CON ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DIRECCIONES){
            if(resultCode == RESULT_OK) {
                if (data != null){
                    Bundle bundle = data.getExtras();
                    Direccion direccion = (Direccion) bundle.getSerializable("DIR");
                    Toast.makeText(this, direccion.toString(), Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(this, "NO HAY DATOS", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "CANCELADA", Toast.LENGTH_SHORT).show();
            }
        }

     */
    //}

    private void inicializarVista() {
        txtPassword = findViewById(R.id.editTextTextPassword);
        boton = findViewById(R.id.boton);
        eemail = findViewById(R.id.editTextTextEmailAddress);
        btnCrearDireccion = findViewById(R.id.btnCrearDireccionMain);
    }

}
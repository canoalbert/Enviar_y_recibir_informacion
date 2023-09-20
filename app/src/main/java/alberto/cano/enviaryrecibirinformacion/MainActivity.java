package alberto.cano.enviaryrecibirinformacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import modelos.Usuario;

public class MainActivity extends AppCompatActivity {
    private EditText txtPassword;

    private EditText eemail;
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarVista();
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
    }

    private void inicializarVista() {
        txtPassword = findViewById(R.id.editTextTextPassword);
        boton = findViewById(R.id.boton);
    }

}
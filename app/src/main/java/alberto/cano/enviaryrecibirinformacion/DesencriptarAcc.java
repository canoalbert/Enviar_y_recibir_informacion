package alberto.cano.enviaryrecibirinformacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import modelos.Usuario;

public class DesencriptarAcc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desencriptar_acc);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){

            //String email = bundle.getString("EMAIL");
            //String password = bundle.getString("PASS");
            //Usuario usuario = new Usuario(email, password);
            Usuario usuario = (Usuario) bundle.getSerializable("USER");
            Toast.makeText(this, usuario.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
package e.a07sid.uvote;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void btn_reg(View v) {
        Intent i = new Intent(login.this, registration.class);
        startActivity(i);
    }
    public void btn_log(View v) {
        Intent i = new Intent(login.this, authenticate.class);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(this,navhome.class));

        }
    }
}


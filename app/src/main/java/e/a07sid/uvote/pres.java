package e.a07sid.uvote;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class pres extends AppCompatActivity {

    TextView finalresult;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,fetch;
    FirebaseAuth firebaseAuth;
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pres);
        finalresult = (TextView) findViewById(R.id.resulttext);
        finalresult.setEnabled(false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
        fetch= firebaseDatabase.getReference();
        firebaseAuth=FirebaseAuth.getInstance();
        fetch.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                value=dataSnapshot.child(firebaseAuth.getCurrentUser().getUid()).child("LvoteStat").getValue(Integer.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void SelectPres(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId())
        {
            case R.id.rb1:
                if(checked) {
                    finalresult.setText("Narendra Modi");
                    finalresult.setEnabled(true);
                }
                else
                {
                    finalresult.setEnabled(false);
                }
                break;
            case R.id.rb2:
                if(checked) {
                    finalresult.setText("Rahul Gandhi");
                    finalresult.setEnabled(true);
                }
                else
                {
                    finalresult.setEnabled(false);
                }
                break;

        }

    }

    public void sendvote(View view){
        String n = finalresult.getText().toString();

        if(n == "Narendra Modi" && value==0)
        {
            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("Narendra Modi").setValue(n);
            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("LvoteStat").setValue(1);
            Intent startnew = new Intent(this,success.class);
            startActivity(startnew);
        }
        else if(n== "Rahul Gandhi" && value==0)
        {   databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("Rahul Gandhi").setValue(n);
            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("LvoteStat").setValue(1);
            Intent startnew = new Intent(this,success.class);
            startActivity(startnew);

        }
        else{
            Toast.makeText(this,"Already Voted",Toast.LENGTH_SHORT).show();
        }


    }
}

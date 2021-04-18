package e.a07sid.uvote;

import android.content.Intent;
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

public class gov extends AppCompatActivity {

    TextView result;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,fetch;
    FirebaseAuth firebaseAuth;
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gov);
        result = (TextView) findViewById(R.id.resulttxt);
        result.setEnabled(false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseAuth=FirebaseAuth.getInstance();
        fetch= firebaseDatabase.getReference();
        fetch.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                value=dataSnapshot.child(firebaseAuth.getCurrentUser().getUid()).child("VvoteStat").getValue(Integer.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void select(View view){
        boolean check = ((RadioButton) view).isChecked();
        switch (view.getId())
        {
            case R.id.rb9:
                if(check){
                    result.setText("Akhilesh Singh Yadav");
                    result.setEnabled(true);
                }
                else{
                    result.setEnabled(false);
                }
                break;
            case R.id.rb10:
                if(check){
                    result.setText("Yogi Adityanath");
                    result.setEnabled(true);
                }
                else {
                    result.setEnabled(false);
                }
                break;
        }


    }
    public void finalbtn(View view){
        String y = result.getText().toString();
        if(y == "Akhilesh Singh Yadav" && value==0){
            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("Akhilesh Singh Yadav").setValue(y);
            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("VvoteStat").setValue(1);
            Intent finalact = new Intent(this,success.class);
            startActivity(finalact);
        }
        else if(y== "Yogi Adityanath" && value==0)
        {
            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("Yogi Adityanath").setValue(y);
            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("VvoteStat").setValue(1);
            Intent finalact = new Intent(this,success.class);
            startActivity(finalact);
        }
        else{
            Toast.makeText(this,"Already Voted",Toast.LENGTH_SHORT).show();
        }

    }
}

package vinkas.support.app;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by vinothkannan on 12/8/17.
 */

public class AppCompatActivity extends android.support.v7.app.AppCompatActivity {

    private FirebaseAuth mAuth;
    protected FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
    }

}

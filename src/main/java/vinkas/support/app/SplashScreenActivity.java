package vinkas.support.app;

import android.app.Activity;
import android.content.Intent;

import static vinkas.support.app.SignInActivity.ON_SUCCESS_ACTIVITY;

/**
 * Created by vinothkannan on 12/8/17.
 */

public abstract class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();

        startNextActivity();
    }

    protected abstract Class<? extends Activity> getMainActivity();

    protected Class<? extends Activity> getSignInActivity() {
        return SignInActivity.class;
    }

    protected void startNextActivity() {
        Intent intent;
        if (currentUser == null) {
            intent = new Intent(this, getSignInActivity());
            intent.putExtra(ON_SUCCESS_ACTIVITY, getMainActivity());
        } else {
            intent = new Intent(this, getMainActivity());
        }
        startActivity(intent);
        finish();
    }

}

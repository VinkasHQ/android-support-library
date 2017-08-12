package vinkas.support.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;

import java.util.ArrayList;
import java.util.List;

import vinkas.support.R;

/**
 * Created by vinothkannan on 12/8/17.
 */

public class SignInActivity extends AppCompatActivity {

    public static final String ON_SUCCESS_ACTIVITY = "SUCCESS ACTIVITY";
    private static final int RC_SIGN_IN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        startNextActivity();
    }

    private List<AuthUI.IdpConfig> getSelectedProviders() {
        List<AuthUI.IdpConfig> selectedProviders = new ArrayList<>();

            /*selectedProviders.add(
                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER)
                            .build());

            selectedProviders.add(
                    new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER)
                            .build());

            selectedProviders.add(new AuthUI.IdpConfig.Builder(AuthUI.TWITTER_PROVIDER).build());*/

        selectedProviders.add(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build());

        selectedProviders.add(
                new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build());

        return selectedProviders;
    }

    protected void onSuccess() {
        startSuccessActivity();
    }

    protected void startSuccessActivity() {
        Class<? extends Activity> activity = getIntent().getExtras().getParcelable(ON_SUCCESS_ACTIVITY);
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        finish();
    }

    protected void onFailure(int resID) {

    }

    protected void startNextActivity() {
        if (currentUser != null) {
            startSuccessActivity();
        } else {
            startActivityForResult(
                    AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(getSelectedProviders()).build(),
                    RC_SIGN_IN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == ResultCodes.OK) {
                onSuccess();
                return;
            } else {
                if (response == null) {
                    onFailure(0);
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    onFailure(R.string.no_internet_connection);
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    onFailure(R.string.unknown_error);
                    return;
                }
            }

            onFailure(R.string.unknown_sign_in_response);
        }
    }

}

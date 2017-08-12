package vinkas.support.discourse;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

/**
 * Created by vinothkannan on 12/8/17.
 */

public class Auth implements OnSuccessListener<GetTokenResult> {

    public Auth(FirebaseUser user) {
        user.getIdToken(true).addOnSuccessListener(this);
    }

    @Override
    public void onSuccess(GetTokenResult result) {
        String idToken = result.getToken();
    }
}

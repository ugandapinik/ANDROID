package www.jewel_mahmud.com.fragment_in_activity_runtime;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wohhie on 9/30/2016.
 */

public class LoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.login_fragment_layout, container, false);


        return view;
    }
}

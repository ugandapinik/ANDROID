package www.jewel_mahmud.com.fragment_in_activity_runtime;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.register_link);


        //when start display login fragment.
        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.fragment_container, loginFragment);
        fragmentTransaction.commit();


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RegisterFragment registerFragment = new RegisterFragment();

                //replace the login fragment with Register Fragment.
                FragmentManager fragmentManagerRegister = getFragmentManager();
                FragmentTransaction fragmentTransactionRegister = fragmentManagerRegister.beginTransaction();
                fragmentTransactionRegister.replace(R.id.fragment_container, registerFragment);
                fragmentTransactionRegister.commit();
            }
        });
    }
}

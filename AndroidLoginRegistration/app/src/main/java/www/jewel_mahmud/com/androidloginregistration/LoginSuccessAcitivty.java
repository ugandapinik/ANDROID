package www.jewel_mahmud.com.androidloginregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginSuccessAcitivty extends AppCompatActivity {

    TextView eName;
    TextView eEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success_acitivty);

        Bundle bundle = getIntent().getExtras();

        eName = (TextView) findViewById(R.id.displayName);
        eEmail = (TextView) findViewById(R.id.displayEmail);

        eName.setText("Welcome " + bundle.getString("name"));
        eEmail.setText("Email Address: " + bundle.getString("email"));

    }
}

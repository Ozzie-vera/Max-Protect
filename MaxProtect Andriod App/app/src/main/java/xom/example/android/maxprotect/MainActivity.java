package xom.example.android.maxprotect;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Locate Login Button in activity_main
        Button button = (Button) findViewById(R.id.LoginButton);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener(){
            public void onClick(View arg0){
                //start new activity class
                Intent myIntent = new Intent(MainActivity.this, DataActivity.class);
                startActivity(myIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }


}

package my.lss2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    MainFragment mainFragment;
    CharacterFragment characterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = new MainFragment();
        characterFragment = new CharacterFragment();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();
                        break;
                    case R.id.tab2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,characterFragment).commit();
                }
                return true;
            }
        });
    }
}
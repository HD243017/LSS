package my.lss2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CharacterinfoWindow extends AppCompatActivity {

    Boolean ck1;
    Boolean ck2;

    int pos = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characterinfo_window);

        MainFragment mainFragment = new MainFragment();
        CharacterInfoAdapter characterInfoAdapter = new CharacterInfoAdapter();

        TextView Cname = findViewById(R.id.textCName);
        TextView ItemLV = findViewById(R.id.textCItemLV);
        TextView CJob = findViewById(R.id.textServer);
        CheckBox Cb1 = findViewById(R.id.checkBox);
        CheckBox Cb2 = findViewById(R.id.checkBox);

        Intent intent = getIntent();


        String name = intent.getStringExtra("name");
        String lv = intent.getStringExtra("item");
        String job = intent.getStringExtra("server");
        ck1 = intent.getBooleanExtra("ck1", false);
        ck2 = intent.getBooleanExtra("ck2", false);
        pos = intent.getIntExtra("pos",0);
        Cb1.setChecked(ck1);
        Cb2.setChecked(ck2);

        Cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ck1){
                    ck1 = true;
                }
                else {
                    ck1 = false;
                }
                //characterInfoAdapter.list.get(pos).setCk1(ck1);
            }
        });

        Cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ck2){
                    ck2 = true;
                }
                else {
                    ck2 = false;
                }
                //characterInfoAdapter.list.get(pos).setCk1(ck2);
            }
        });

        Cname.setText(name);
        ItemLV.setText(lv);
        CJob.setText(job);
    }
}
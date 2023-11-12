package my.lss2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    public static Context mContext;
    private static final String TAG = "MainFragment";

    private final int REQUEST_CODE = 101;

    private Button mOpenDialog;
    public TextView mInputDisplay;
    public TextView textView;
    public TextView textSS;
    public String mInput;

    String C_url = "https://lostark.game.onstove.com/Profile/Character/";   //로아전투정보실 주소
    String nums;    //이름 저장
    String LVnums;
    String ServerName; // 캐릭 서버
    String ImageCC;
    CharacterInfoAdapter adapter;

    int n_i = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container,false);
        // Inflate the layout for this fragment
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewC);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);

        mContext = this.getContext();
        //////
        mOpenDialog = rootView.findViewById(R.id.buttonAdd);
        mInputDisplay = rootView.findViewById(R.id.textView2);
        textView = rootView.findViewById(R.id.textCC);
        textSS = rootView.findViewById(R.id.textSer);
        //////
        adapter = new CharacterInfoAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnCharacterClickListener(new OnCharacterClickListener(){
            @Override
            void onItemClick(CharacterInfoAdapter.ViewHolder viewHolder, View view, int position) {
                Intent intent = new Intent(mContext.getApplicationContext(),CharacterinfoWindow.class);
                CharacterInfo item = adapter.getItem(position);
                intent.putExtra("name", item.getName());
                intent.putExtra("item", item.getItemLV());
                intent.putExtra("server", item.getJob());
                intent.putExtra("ck1", item.getCk1());
                intent.putExtra("ck2", item.getCk2());
                intent.putExtra("pos", position);

                //intent.putExtra("레벨", item.getItemLV());
                startActivity(intent);
            }
        });
        mOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "오픈");
                Customdialog dialog = new Customdialog();
                dialog.show(getActivity().getSupportFragmentManager(), "Customdialog");
            }
        });

        return rootView;
    }
    public void reCUrlData(){
        C_url = "https://lostark.game.onstove.com/Profile/Character/";
        nums = "";
        LVnums = "";
    }
    public void getCUrlData(){
        final Bundle bundle = new Bundle();
        new Thread(){
            @Override
            public void run() {
                Document doc = null;
                String CName = mInputDisplay.getText().toString();
                C_url += CName;
                try {
                    doc = Jsoup.connect(C_url).get();
                    Elements contents = doc.getElementsByClass("level-info2__expedition");
                    LVnums = contents.text();

                    Elements contents2 = doc.getElementsByClass("profile-character-info__server");
                    ServerName = contents2.text();

                    bundle.putString("numbers", LVnums);
                    bundle.putString("Server", ServerName);
                    Message msg = handler.obtainMessage();
                    msg.setData(bundle);
                    handler.sendMessage(msg);

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            textView.setText(bundle.getString("numbers"));
            textSS.setText(bundle.getString("Server"));
            if(n_i == 0){
                addAdapter();
                n_i = 1;
                reCUrlData();
            }
        }
    };

    public void addAdapter(){
        //String infod = textView.getText().toString();
        String tName = mInputDisplay.getText().toString();
        String tLV = textView.getText().toString();
        String tServer = textSS.getText().toString();
        String tImage = ImageCC;
        adapter.addItem(new CharacterInfo(tName, tLV, tServer,false, false));
        adapter.notifyDataSetChanged();

        nums = "";
    }
}
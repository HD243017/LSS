package my.lss2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CharacterInfoAdapter extends RecyclerView.Adapter<CharacterInfoAdapter.ViewHolder> {
    ArrayList<CharacterInfo> list = new ArrayList<CharacterInfo>();


    OnCharacterClickListener onCharacterClickListener;
    public void setOnCharacterClickListener(OnCharacterClickListener listener){
        onCharacterClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View item = layoutInflater.inflate(R.layout.characterinfo_item,parent,false);

        return new ViewHolder(item, onCharacterClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CharacterInfo ch = list.get(position);
        holder.setItem(ch);
}

    @Override
    public int getItemCount() {
        return list.size();
    }

static class ViewHolder extends RecyclerView.ViewHolder {
    TextView textname, textLV, textjob;

    public ViewHolder(@NonNull View itemView, OnCharacterClickListener listener) {
        super(itemView);

        textname = itemView.findViewById(R.id.textName);
        textLV = itemView.findViewById(R.id.textLV);
        textjob = itemView.findViewById(R.id.textJob);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = getAdapterPosition();
                if(listener != null){
                    listener.onItemClick(ViewHolder.this,view,pos);
                }
            }
        });
    }
    public void setItem(CharacterInfo characterInfo){
        textname.setText(characterInfo.getName());
        textLV.setText(characterInfo.getItemLV());
        textjob.setText(characterInfo.getJob());
    }
}
    public void addItem(CharacterInfo characterInfo){ list.add(characterInfo); }
    public CharacterInfo getItem(int position) { return list.get(position); }
}
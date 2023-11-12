package my.lss2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Customdialog extends DialogFragment {
    private static final String TAG = "Customdialog";

    private EditText mInput;
    private TextView mActionOk;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //MainFragment mainFragment = new MainFragment();
        View view = inflater.inflate(R.layout.dialog, container, false);
        mActionOk = view.findViewById(R.id.buttonAddC);
        mInput = view.findViewById(R.id.editTextCA);




        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "추가 버튼 눌림");
                String input = mInput.getText().toString();
                if(!input.equals("")){
                    ((MainActivity)getActivity()).mainFragment.mInputDisplay.setText(input);
                    //((MainFragment) mContext).mInputDisplay.setText(input);
                    //((MainActivity)getActivity()).addAdapter();
                    ((MainActivity)getActivity()).mainFragment.n_i = 0;
                    ((MainActivity)getActivity()).mainFragment.getCUrlData();
                }


                getDialog().dismiss();
            }
        });
        return view;
    }


}

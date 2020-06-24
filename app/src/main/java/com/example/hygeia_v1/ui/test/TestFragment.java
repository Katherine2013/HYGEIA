package com.example.hygeia_v1.ui.test;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hygeia_v1.MainActivity;
import com.example.hygeia_v1.R;
import com.example.hygeia_v1.ui.searchmap.MapActivity;
import com.example.hygeia_v1.ui.slideshow.SlideshowViewModel;

public class TestFragment extends Fragment {

    private TestViewModel testViewModel;
    private Button btn;
    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        testViewModel =
                ViewModelProviders.of(this).get(TestViewModel.class);
        View root = inflater.inflate(R.layout.fragment_test, container, false);
        EditText username = root.findViewById(R.id.login_username);
        EditText password = root.findViewById(R.id.login_password);
        final TextView textView = root.findViewById(R.id.text_test);
        testViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        btn = root.findViewById(R.id.button);
        return root;
    }
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
//        // TODO: Use the ViewModel
//    }
@Override
public void onActivityCreated(Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    super.onActivityCreated(savedInstanceState);
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Toast.makeText(getActivity(), "success2", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(getActivity().getApplicationContext(), MapActivity.class); //intent.setClass(HistoryActivity.this,EventDetail.class);
            //是当前activity跳转到后一个activity，这里是fragment，所以要getactivity
            startActivity(intent);
        }
    });
}
}
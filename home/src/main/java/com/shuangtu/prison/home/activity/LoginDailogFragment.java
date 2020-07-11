package com.shuangtu.prison.home.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.shuangtu.prison.home.R;

public class LoginDailogFragment extends DialogFragment implements View.OnClickListener {

    public static final String USERNAME = "userName";
    public static final String USERPASSWORD = "userPassword";
    private EditText mUsername;
    private EditText mPassword;
    private Button btn;
    private LoginDailogFragment fragment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置背景透明
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.dialog_login, null);
        mUsername= view.findViewById(R.id.editText);
        btn= view.findViewById(R.id.button5);
        mPassword= view.findViewById(R.id.editText2);
        btn.setOnClickListener(this);

        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button5) {
            if (getTargetFragment() == null) {
                return;
            }
            Intent intent = new Intent();
            intent.putExtra(USERNAME, mUsername.getText().toString());
            intent.putExtra(USERPASSWORD, mPassword.getText().toString());

        }
    }
}

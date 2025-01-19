package com.graphhene.densenium;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

public class LoginTabFragment extends Fragment {
    TextInputLayout Email,Password;
    TextView ForgetPassword;
    TextView LoginButton;
    float v=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState){
        ViewGroup root= (ViewGroup)inflater.inflate(R.layout.login_tab_fragment,container,false);

        Email = root.findViewById(R.id.login_email);
        Password = root.findViewById(R.id.login_pwd);
        ForgetPassword = root.findViewById(R.id.forget_pwd);
        LoginButton = root.findViewById(R.id.login_btn);

        Email.setTranslationX(800);
        Password.setTranslationX(800);
        ForgetPassword.setTranslationX(800);
        LoginButton.setTranslationX(800);

        Email.setAlpha(v);
        Password.setAlpha(v);
        ForgetPassword.setAlpha(v);
        LoginButton.setAlpha(v);

        Email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        Password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        ForgetPassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        LoginButton.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();

        return root;
    }
}

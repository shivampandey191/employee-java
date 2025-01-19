package com.graphhene.densenium;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.ContentValues.TAG;

public class SignupTabFragment extends Fragment {

    TextInputLayout Name,Email,PhoneNumber,Password,ConfirmPassword,Cc;
    TextView SignupButton;
    Boolean isValidData=false;
    FirebaseAuth fAuth;

    float v=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState){
        ViewGroup root= (ViewGroup)inflater.inflate(R.layout.signup_tab_fragment,container,false);

        fAuth = FirebaseAuth.getInstance();

        Name = root.findViewById(R.id.reg_name);
        Email = root.findViewById(R.id.reg_email);
        PhoneNumber = root.findViewById(R.id.reg_phone);
        Password = root.findViewById(R.id.reg_pwd);
        ConfirmPassword = root.findViewById(R.id.confirm_pwd);
        Cc = root.findViewById(R.id.reg_cc);
        SignupButton = root.findViewById(R.id.reg_btn);

        Email.setTranslationX(800);
        Password.setTranslationX(800);
        Name.setTranslationX(800);
        PhoneNumber.setTranslationX(800);
        ConfirmPassword.setTranslationX(800);
        SignupButton.setTranslationX(800);

        Email.setAlpha(v);
        Password.setAlpha(v);
        Name.setAlpha(v);
        PhoneNumber.setAlpha(v);
        ConfirmPassword.setAlpha(v);
        SignupButton.setAlpha(v);

        Email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        Password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        Name.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        PhoneNumber.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        ConfirmPassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        SignupButton.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();


        validateData(Name);
        validateData(Email);
        validateData(PhoneNumber);
        validateData(Password);
        validateData(ConfirmPassword);
        validateData(Cc);

        if(!Password.getEditText().toString().equals(ConfirmPassword.getEditText().toString())){
            isValidData = false;
            ConfirmPassword.setError("Password did not Match");
        }
        else{
            isValidData = true;
        }

        if(isValidData){
            fAuth.createUserWithEmailAndPassword(Email.getEditText().toString(),Password.getEditText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(getActivity(), "User Account is Created", Toast.LENGTH_SHORT).show();
                    Intent phone = new Intent(getActivity(),VerifyPhone.class);
                    phone.putExtra("phone","+"+Cc.getEditText().toString()+PhoneNumber.getEditText().toString());
                    startActivity(phone);
                    Log.d(TAG,"onSuccess: "+"+"+Cc.getEditText().toString()+PhoneNumber.getEditText().toString());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "Error !!!"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


        return root;
    }


    public void validateData(TextInputLayout field){
        if(field.getEditText().toString().isEmpty()){
            isValidData=false;
            field.setError("Field can't be Empty");
        }
        else{
            isValidData =true;
        }
    }
}

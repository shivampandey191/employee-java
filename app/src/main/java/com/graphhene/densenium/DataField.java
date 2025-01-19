package com.graphhene.densenium;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class DataField extends AppCompatActivity {
    TextInputLayout Position,referredBy,Name,Contact,Email,Address,Aadhar,FamilyName,FamilyPhone,PreviousComp,Designation,Salary,RefName1,RefName2,RefName3,RefContact1,RefContact2,RefContact3,RefDesign1,RefDesign2,RefDesign3,WorkExperience;
    CheckBox WorkStatus;
    LinearLayout layoutList;
    //NeomorphFrameLayout SubmitLayout;
    ImageView buttonAdd;
    TextInputLayout Qualification;
    TextView submitBtn,YesorNo;
    AutoCompleteTextView DateofJoining,LeavingDate,DropdownText;
    DatePickerDialog.OnDateSetListener dateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_datafield);

        //layoutList = findViewById(R.id.layout_list);
        //buttonAdd = findViewById(R.id.addbtn);
        submitBtn = findViewById(R.id.submitbtn);

        /*SubmitLayout = findViewById(R.id.submitbtnlayout);
        SubmitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubmitLayout.setShadowInner();
            }
        });*/

        Qualification = findViewById(R.id.qualification);
        DropdownText = findViewById(R.id.dropdown_text);
        String[] Qual= new String[]{"B.Tech/B.E.","B.A.","B.Arch","BBA/BMS","B.Com","B.Ed","BSC","B.Pharma",
              "CA","CS","MBA","MA","M.Tech","M.Ed","MSC(Science)","PGDM","MCA","PG Diploma","LLM","Other"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(DataField.this,R.layout.dropdown_item,Qual);
        DropdownText.setAdapter(adapter);

        /*buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView();
            }
        }); */

        Position = findViewById(R.id.appliedfor);
        referredBy = findViewById(R.id.referredby);
        Name = findViewById(R.id.name);
        Contact = findViewById(R.id.contact);
        Email = findViewById(R.id.email);
        Address = findViewById(R.id.address);
        WorkExperience = findViewById(R.id.workexperience);
        Aadhar = findViewById(R.id.aadhar);
        FamilyName = findViewById(R.id.familyname);
        FamilyPhone = findViewById(R.id.familyphone);

        RefName1 = findViewById(R.id.refname1);
        RefName2 = findViewById(R.id.refname2);
        RefName3 = findViewById(R.id.refname3);
        RefContact1 = findViewById(R.id.refnumber1);
        RefContact2 = findViewById(R.id.refnumber2);
        RefContact3 = findViewById(R.id.refnumber3);
        RefDesign1 = findViewById(R.id.refdesign1);
        RefDesign2 = findViewById(R.id.refdesign2);
        RefDesign3 = findViewById(R.id.refdesign3);

        PreviousComp = findViewById(R.id.pc);
        Designation = findViewById(R.id.designation);
        Salary = findViewById(R.id.sal);
        LeavingDate = findViewById(R.id.ld);
        DateofJoining = findViewById(R.id.doj);
        WorkStatus = findViewById(R.id.workingstatus);
        YesorNo =findViewById(R.id.yesorno);

        WorkStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    YesorNo.setText(R.string.yes);
                }else{
                    YesorNo.setText(R.string.no);
                }
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if(!validateName() | !validateEmail() |  !validateAddress() | !validateFamilyName() | !validateFamilyPhone() | !validatePhoneno() | !validatePositionApplied()
                 | !validateQualification()){
                    return ;
               }
                else {
                   addItemToSheet();
                 Intent intent = new Intent(getApplicationContext(),Done.class);
                 startActivity(intent);
               }
            }
        });
        DateofJoining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                DatePickerDialog picker = new DatePickerDialog(DataField.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                DateofJoining.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        LeavingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                DatePickerDialog picker = new DatePickerDialog(DataField.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                LeavingDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }


    public void addView() {
        final View pcRowAdd = getLayoutInflater().inflate(R.layout.pcrow,null,false);
        TextInputLayout previousComp = (TextInputLayout) pcRowAdd.findViewById(R.id.pc1);
        TextInputLayout design = (TextInputLayout)pcRowAdd.findViewById(R.id.designation1);
        TextInputLayout salary = (TextInputLayout)pcRowAdd.findViewById(R.id.sal1);
        Button removeBtn = (Button)pcRowAdd.findViewById(R.id.removebtn);
        final TextView YesOrNo =(TextView)pcRowAdd.findViewById(R.id.yesorno1);
        final MaterialCheckBox workStatus=(MaterialCheckBox)pcRowAdd.findViewById(R.id.workingstatus1);
        workStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    YesOrNo.setText(R.string.yes);
                }else{
                    YesOrNo.setText(R.string.no);
                }
            }
        });


         final MaterialAutoCompleteTextView dateofJoining1 = (MaterialAutoCompleteTextView) pcRowAdd.findViewById(R.id.doj1);
         final MaterialAutoCompleteTextView leavingDate1 = (MaterialAutoCompleteTextView)pcRowAdd.findViewById(R.id.ld1);


        dateofJoining1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                DatePickerDialog picker = new DatePickerDialog(DataField.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int
                                    year, int monthOfYear, int dayOfMonth) {
                                dateofJoining1.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });


        leavingDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                DatePickerDialog picker = new DatePickerDialog(DataField.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int
                                    year, int monthOfYear, int dayOfMonth) {
                                leavingDate1.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
             removeView(pcRowAdd);
            }
        });
        layoutList.addView(pcRowAdd);

    }

    private void removeView(View view) {
        layoutList.removeView(view);

    }

    private Boolean validateEmail(){
        String emailinput = Email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(emailinput.isEmpty()){
            Email.setError("Field cannot be Empty");
            return false;
        }
        else if(!emailinput.matches(emailPattern)){
            Email.setError("Invalid Email Address");
            return false;
        }
        else {
            Email.setError(null);
            return true ;
        }
    }

    private Boolean validateName(){
        String val = Name.getEditText().getText().toString();
        if(val.isEmpty()){
            Name.setError("Field cannot be Empty");
            return false;
        }
        else {
            Name.setError(null);
            return true ;
        }
    }

    private Boolean validateFamilyName(){
        String val = FamilyName.getEditText().getText().toString();
        if(val.isEmpty()){
            FamilyName.setError("Field cannot be Empty");
            return false;
        }
        else {
            FamilyName.setError(null);
            return true ;
        }
    }

    private Boolean validatePositionApplied(){
        String val = Position.getEditText().getText().toString();
        if(val.isEmpty()){
            Position.setError("Field cannot be empty");
            return false;
        }
        else {
            Position.setError(null);
            return true;
        }
    }

    private Boolean validateAddress(){
        String val = Address.getEditText().getText().toString();
        if(val.isEmpty()){
            Address.setError("Field cannot be empty");
            return false;
        }
        else {
            Address.setError(null);
            return true;
        }

    }

    private Boolean validatePhoneno(){
        String val = Contact.getEditText().getText().toString();
        String phoneVal = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[6789]\\d{9}$";
        if(val.isEmpty()){
            Contact.setError("Field cannot be Empty");
            return false;
        }
        else if(!val.matches(phoneVal)){
            Contact.setError("Enter a valid Phone Number");
            return false;
        }
        else {
            Contact.setError(null);
            return true ;
        }
    }

    private Boolean validateFamilyPhone(){
        String valid = FamilyPhone.getEditText().getText().toString();
        String phoneValid = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[6789]\\d{9}$";
        if(valid.isEmpty()){
            FamilyPhone.setError("Field cannot be Empty");
            return false;
        }
        else if(!valid.matches(phoneValid)){
            FamilyPhone.setError("Enter a valid Phone Number");
            return false;
        }
        else {
            FamilyPhone.setError(null);
            return true ;
        }
    }

    private Boolean validateQualification(){
        String val = Qualification.getEditText().getText().toString();
        if(val.isEmpty()){
            Qualification.setError("Field cannot be Empty");
            return false;
        }
        else {
            Qualification.setError(null);
            return true ;
        }
    }

    private Boolean validateAadharPan() {
        String val = Aadhar.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{10,12}\\z";

        if (val.isEmpty()) {
            Aadhar.setError("Field cannot be empty");
            return false;
        } else if (val.length() > 12) {
            Aadhar.setError("Aadhar or Pan should be 10 to 12 characters long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            Aadhar.setError("White Spaces are not allowed");
            return false;
        } else {
            Aadhar.setError(null);
            return true;
        }
    }

    private void   addItemToSheet() {

        final ProgressDialog loading = ProgressDialog.show(this,"Submitting Your Details","Please wait");
        final String BPosition = Position.getEditText().getText().toString().trim();
        final String BReferredby = referredBy.getEditText().getText().toString().trim();
        final String BName = Name.getEditText().getText().toString().trim();
        final String BContact = Contact.getEditText().getText().toString().trim();
        final String BEmail = Email.getEditText().getText().toString().trim();
        final String BAddress = Address.getEditText().getText().toString().trim();
        final String BQualification = DropdownText.getText().toString().trim();
        final String BWorkexperience = WorkExperience.getEditText().getText().toString().trim();
        final String BAadhar = Aadhar.getEditText().getText().toString().trim();
        final String BFamilyname = FamilyName.getEditText().getText().toString().trim();
        final String BFamilyphone = FamilyPhone.getEditText().getText().toString().trim();
        final String BPreviouscomp = PreviousComp.getEditText().getText().toString().trim();
        final String BDesignation = Designation.getEditText().getText().toString().trim();
        final String BSalary = Salary.getEditText().getText().toString().trim();
        final String BLeavingdate = LeavingDate.getText().toString().trim();
        final String BDateofjoining = DateofJoining.getText().toString().trim();
        final String BWorkstatus = YesorNo.getText().toString().trim();
        final String BRefname1 = RefName1.getEditText().getText().toString().trim();
        final String BRefname2 = RefName2.getEditText().getText().toString().trim();
        final String BRefname3 = RefName3.getEditText().getText().toString().trim();
        final String BRefcontact1 = RefContact1.getEditText().getText().toString().trim();
        final String BRefcontact2 = RefContact2.getEditText().getText().toString().trim();
        final String BRefcontact3 = RefContact3.getEditText().getText().toString().trim();
        final String BRefdesign1 = RefDesign1.getEditText().getText().toString().trim();
        final String BRefdesign2 = RefDesign2.getEditText().getText().toString().trim();
        final String BRefdesign3 = RefDesign3.getEditText().getText().toString().trim();


        //  https://script.google.com/a/densenium.com/macros/s/AKfycbzSB_3zSTqngjMmWIdPPh6pdwo8R5XYOyvksPLW736R9mn5vPc/exec
        //  https://script.google.com/macros/s/AKfycbye_gjHX89r1XV_2m6hEjTmDJaicKjw7Ws9x4oOHXh2zeJrZTAq/exec


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/a/densenium.com/macros/s/AKfycbzSB_3zSTqngjMmWIdPPh6pdwo8R5XYOyvksPLW736R9mn5vPc/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(DataField.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();


                parmas.put("action","addItem");
                parmas.put("position",BPosition);
                parmas.put("refer",BReferredby);
                parmas.put("name",BName);
                parmas.put("contact",BContact);
                parmas.put("email",BEmail);
                parmas.put("address",BAddress);
                parmas.put("workexperience",BWorkexperience);
                parmas.put("qualification",BQualification);
                parmas.put("aadhar",BAadhar);
                parmas.put("familyname",BFamilyname);
                parmas.put("familyphone",BFamilyphone);
                parmas.put("previouscomp",BPreviouscomp);
                parmas.put("designation",BDesignation);
                parmas.put("dateofjoining",BDateofjoining);
                parmas.put("leavingdate",BLeavingdate);
                parmas.put("workstatus",BWorkstatus);
                parmas.put("salary",BSalary);
                parmas.put("refname1",BRefname1);
                parmas.put("refcontact1",BRefcontact1);
                parmas.put("refdesign1",BRefdesign1);
                parmas.put("refname2",BRefname2);
                parmas.put("refcontact2",BRefcontact2);
                parmas.put("refdesign2",BRefdesign2);
                parmas.put("refname3",BRefname3);
                parmas.put("refcontact3",BRefcontact3);
                parmas.put("refdesign3",BRefdesign3);

                return parmas;
            }
        };

        int socketTimeOut = 50000;

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);

    }


}
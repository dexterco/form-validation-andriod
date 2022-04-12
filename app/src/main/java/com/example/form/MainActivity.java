package com.example.form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button vbtn ;
EditText fname;
EditText lname;
Switch genderSwitch;
TextView errormessage;
RadioButton bsbtn;
RadioButton msbtn;
CheckBox matricbox;
CheckBox fscbox;
CheckBox bsbox;
Spinner city;
SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vbtn = (Button) findViewById(R.id.vbtn);
        fname=(EditText) findViewById(R.id.fname);
        lname=(EditText) findViewById(R.id.lname);
        errormessage=(TextView) findViewById(R.id.error);
        bsbtn =(RadioButton) findViewById(R.id.bs);
        msbtn =(RadioButton) findViewById(R.id.ms);
        matricbox=(CheckBox) findViewById(R.id.matric);
        fscbox =(CheckBox) findViewById(R.id.fsc);
        bsbox=(CheckBox) findViewById(R.id.cbs);
        genderSwitch = (Switch) findViewById(R.id.gender);
        city =(Spinner) findViewById(R.id.citylist);
        //BS Radio Button Logic
        bsButtonClick();
        //MS Radio Button Logic
        msButtonClick();
        //validate Button
        validate();
        //Checking for already stored Data
        backup();

    }

    public void validate(){
        vbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fn = fname.getText().toString();
                String ln = lname.getText().toString();
                if (fn.isEmpty() || ln.isEmpty() || !bsbtn.isChecked() && !msbtn.isChecked()) {
                    errormessage.setText("Please Fill All the required feilds");
                    errormessage.setTextColor(Color.RED);
                }
                else{

                    if (bsbtn.isChecked()){
                    if (!matricbox.isChecked() || !fscbox.isChecked()){
                        errormessage.setText("Please select your previous education");
                        errormessage.setTextColor(Color.RED);
                    }else{
                        errormessage.setText("* Required Fields");
                        errormessage.setTextColor(Color.WHITE);
                    }
                }else if (msbtn.isChecked()){
                        if (!matricbox.isChecked() || !fscbox.isChecked() || !bsbox.isChecked()){
                        errormessage.setText("Please select your previous education");
                        errormessage.setTextColor(Color.RED);
                        }else{
                        errormessage.setText("* Required Fields");
                        errormessage.setTextColor(Color.WHITE);
                    }
                    }

                }

                if (!fn.isEmpty() && !ln.isEmpty() && bsbtn.isChecked() && !msbtn.isChecked()){
                    if (matricbox.isChecked() && fscbox.isChecked()){
                        bsbox.setChecked(false);
                        //will do intent from here
                        Toast.makeText(getApplicationContext(),"Thanks "+fn+" "+ln,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getBaseContext(), Thanks.class);
                        intent.putExtra("name", fn+" "+ln);
                        String getGender;
                        if (genderSwitch.isChecked()){
                            getGender = "Gender: Female";
                        }else{
                            getGender = "Gender: Male";
                        }
                        intent.putExtra("gender",getGender);
                        intent.putExtra("program","Apply For BS");
                        intent.putExtra("pe","Previous Education is Matric , FSC");
                        intent.putExtra("city","City: "+city.getSelectedItem().toString());
                        sharedPreferences = getSharedPreferences("formData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("fName",fn);
                        editor.putString("lName",ln);
                        editor.putString("gender",getGender);
                        editor.putString("program","Apply For BS");
                        editor.putString("pe","Previous Education is Matric , FSC");
                        editor.putString("city","City: "+city.getSelectedItem().toString());
                        editor.apply();
                        Toast.makeText(getApplicationContext(),"Data has been saved",Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }
                } else if (!fn.isEmpty() && !ln.isEmpty() && msbtn.isChecked() && !bsbtn.isChecked()){
                    if (matricbox.isChecked() && fscbox.isChecked() && bsbox.isChecked()){
                        //will do intent from here
                        Toast.makeText(getApplicationContext(),"Thanks "+fn+" "+ln,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getBaseContext(), Thanks.class);
                        intent.putExtra("name", fn+" "+ln);
                        String getGender;
                        if (genderSwitch.isChecked()){
                            getGender = "Gender: Female";
                        }else{
                            getGender = "Gender: Male";
                        }
                        intent.putExtra("gender",getGender);
                        intent.putExtra("program","Apply For MS");
                        intent.putExtra("pe","Previous Education is Matric , FSC , BS");

                        intent.putExtra("city","City: "+city.getSelectedItem().toString());
                        //Here Using Share Preferences
                        sharedPreferences = getSharedPreferences("formData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("fName",fn);
                        editor.putString("lName",ln);
                        editor.putString("gender",getGender);
                        editor.putString("program","Apply For MS");
                        editor.putString("pe","Previous Education is Matric , FSC , BS");
                        editor.putString("city","City: "+city.getSelectedItem().toString());
                        editor.apply();
                        Toast.makeText(getApplicationContext(),"Data has been saved",Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }
                }


            }
        });

    }

    public void bsButtonClick(){
        bsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msbtn.setChecked(false);
                bsbox.setChecked(false);
                bsbox.setVisibility(View.GONE);
            }
        });

    }
    public void msButtonClick(){
        msbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bsbtn.setChecked(false);
                bsbox.setVisibility(View.VISIBLE);
            }
        });
    }

    public void backup(){
        sharedPreferences = getSharedPreferences("formData",MODE_PRIVATE);
        if (sharedPreferences.contains("fName")){
            fname.setText(sharedPreferences.getString("fName",null));
        }
        if(sharedPreferences.contains("lName")){
            lname.setText(sharedPreferences.getString("lName",null));
        }
        if (sharedPreferences.contains("gender")){

            String gen = sharedPreferences.getString("gender",null);
            if (gen.equals("Gender: Male")){
                genderSwitch.setChecked(false);
            }
            if(gen.equals("Gender: Female")){
                genderSwitch.setChecked(true);
            }

        }
        if (sharedPreferences.contains("program")){
            String deg = sharedPreferences.getString("program",null);
            if (deg.equals("Apply For MS")){
                msbtn.setChecked(true);
                matricbox.setChecked(true);
                fscbox.setChecked(true);
                bsbox.setChecked(true);
                bsbox.setVisibility(View.VISIBLE);
            }
            if(deg.equals("Apply For BS")){
                bsbtn.setChecked(true);
                matricbox.setChecked(true);
                fscbox.setChecked(true);
                bsbox.setVisibility(View.GONE);
            }
        }

    }
}

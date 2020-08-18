package com.jasonoh.day0518compound_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText et_name, et_phone01, et_phone02, et_phone03;
    RadioGroup rg_gender, rg_city;
    CheckBox cb_email, cb_phone, cb_visit, cb_sms;
    Button btn;
    TextView tv_result;
    String tv_result_text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = findViewById(R.id.et_name);
        et_phone01 = findViewById(R.id.et_phone01);
        et_phone02 = findViewById(R.id.et_phone02);
        et_phone03 = findViewById(R.id.et_phone03);
        rg_gender = findViewById(R.id.rg_gender);
        rg_city = findViewById(R.id.rg_city);
        cb_email = findViewById(R.id.check_email);
        cb_phone = findViewById(R.id.check_phone);
        cb_visit = findViewById(R.id.check_visit);
        cb_sms = findViewById(R.id.check_sms);
        btn = findViewById(R.id.btn_reg);
        tv_result = findViewById(R.id.tv_result);

        try {

            //참고 사이트 : https://www.androidpub.com/955344
            et_phone01.addTextChangedListener( textWatcher );
            et_phone02.addTextChangedListener( textWatcher );
            et_phone03.addTextChangedListener( textWatcher );

            btn.setOnClickListener(new View.OnClickListener() {
                RadioButton rb_gender, rb_city; // rg_gender, rg_city 확인용
                @Override
                public void onClick(View v) {

                    try{
                        tv_result_text += et_name.getText().toString() + " ";
//                        tv_result.append( et_name.getText().toString() + " "  );

                        rb_gender = findViewById( rg_gender.getCheckedRadioButtonId() );
                        tv_result_text += rb_gender.getText().toString() + " ";
//                        tv_result.append( rb.getText().toString() + " " );

                        rb_city = findViewById( rg_city.getCheckedRadioButtonId() );
                        tv_result_text += rb_city.getText().toString() + " ";
//                        tv_result.append( rb.getText().toString() + " " );

                        if( et_phone01.getText().toString().length() != 0 )
                            tv_result_text += et_phone01.getText().toString() + "-" + et_phone02.getText().toString() + "-" + et_phone03.getText().toString() + " ";
//                        tv_result.append( et_phone01.getText().toString() + "-" + et_phone02.getText().toString() + "-" + et_phone03.getText().toString() + " " );

                        if( cb_email.isChecked() )   tv_result_text += cb_email.getText().toString() + ", ";
                        if( cb_phone.isChecked() ) tv_result_text += cb_phone.getText().toString() + ", ";
                        if( cb_visit.isChecked() ) tv_result_text += cb_visit.getText().toString() + ", ";
                        if( cb_sms.isChecked() ) tv_result_text += cb_sms.getText().toString() + ", ";

//                        if( cb_email.isChecked() ) tv_result.append( cb_email.getText().toString() + ", " );
//                        if( cb_phone.isChecked() ) tv_result.append( cb_phone.getText().toString() + ", " );
//                        if( cb_visit.isChecked() ) tv_result.append( cb_visit.getText().toString() + ", " );
//                        if( cb_sms.isChecked() ) tv_result.append( cb_sms.getText().toString() + ", " );

                        if( tv_result_text.endsWith( ", " ) ) tv_result_text = tv_result_text.substring( 0, tv_result_text.length() - 2 );
//                        if( tv_result.getText().toString().endsWith( ", " ) ) tv_result.setText(  tv_result.getText().toString().substring( 0, tv_result.getText().toString().length() - 2 ) + "\n" );
                        // (tv_result.getText().toString().length() - 2) 의미는 (, ) 이것을 빼는 것으로 해서 -2를 한 것이다.

                        tv_result_text += "\n";
                        tv_result.setText( tv_result_text );

                        et_name.setText( "" );
                        et_phone01.setText( "" );
                        et_phone02.setText( "" );
                        et_phone03.setText( "" );
                        rb_gender.setChecked(false);
                        rb_city.setChecked(false);
                        cb_email.setChecked(false);
                        cb_phone.setChecked(false);
                        cb_visit.setChecked(false);
                        cb_sms.setChecked(false);

                    } catch (Exception e) { tv_result.append( "" ); } // OnClick Method -> inner try/catch문

                } //OnClick Method



            });  //onClickListener Anonymous Method



        } catch (Exception e) { tv_result.append( "" ); } // try/catch문

    } // onCreate

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if( et_phone01.getText().toString().length() == 3 ) et_phone02.requestFocus(); //et_phone01이 3자리라면 et_phone02로 커서(포커스)이동
            if(et_phone02.getText().toString().length() == 4) et_phone03.requestFocus(); //et_phone02이 4자리라면 et_phone03로 커서(포커스)이동
//            if(et_phone03.getText().toString().length() == 4) cb_email.requestFocus(); //et_phone03이 4자리라면 cb_email로 커서(포커스)이동
        } // onTextChanged overriod method

        @Override
        public void afterTextChanged(Editable s) { }
    }; //textWatcher class

    RadioGroup.OnCheckedChangeListener changeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton rb = findViewById( checkedId );
            tv_result.append( rb.getText().toString() + " " );
        }
    }; //

}// MainActivity class

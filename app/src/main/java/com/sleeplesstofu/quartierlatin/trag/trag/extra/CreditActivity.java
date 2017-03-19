package com.sleeplesstofu.quartierlatin.trag.trag.extra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sleeplesstofu.quartierlatin.trag.MainActivity;
import com.sleeplesstofu.quartierlatin.trag.R;

public class CreditActivity extends AppCompatActivity {

    private TextView txtCreditSpecials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        txtCreditSpecials = (TextView) findViewById(R.id.txt_credit_special);
        txtCreditSpecials.setTypeface(MainActivity.tragFont);
    }

    public void doFinishCredit(View v){
        finish();
    }
}

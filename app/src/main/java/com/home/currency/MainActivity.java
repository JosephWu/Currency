package com.home.currency;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static double USD2NTD = 30.9;
    public static double JPY2NTD = 0.2829;
    private EditText etNTD;
    private TextView tvUSD;
    private TextView tvJPY;
    private Button btGo;

    private double ntdAmount = 0;
    private double usdAmount = 0;
    private double jpyAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNTD = findViewById(R.id.etNTD);
        tvUSD = findViewById(R.id.tvUSD);
        tvJPY = findViewById(R.id.tvJPYen);
        btGo = findViewById(R.id.btGo);

        btGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempNTD = etNTD.getText().toString();
                if(tempNTD != null && !tempNTD.equals("")) {
                    ntdAmount = Double.valueOf(tempNTD);
                    usdAmount = ntdAmount / USD2NTD;
                    jpyAmount = ntdAmount / JPY2NTD;
                    tvUSD.setText(myFormattedString(usdAmount));
                    tvJPY.setText(myFormattedString(jpyAmount));

                    String message = constructMessage(ntdAmount, usdAmount, jpyAmount);

                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.result)
                            .setMessage(message)
                            .setPositiveButton(getResources().getString(R.string.ok), null)
                            .show();
                }
                else{
                    tvUSD.setText(getResources().getString(R.string.none));
                    tvJPY.setText(getResources().getString(R.string.none));
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.problem)
                            .setMessage(R.string.enter_ntd_amount)
                            .setPositiveButton(getResources().getString(R.string.ok), null)
                            .show();
                }
            }

            private String constructMessage(double ntdAmount, double usdAmount, double jpyAmount) {
                StringBuilder msg = new StringBuilder();
                msg.append(getResources().getString(R.string.exchange_rate_part1));
                msg.append(USD2NTD);
                msg.append(getResources().getString(R.string.exchange_rate_part2));
                msg.append(JPY2NTD);
                msg.append(getResources().getString(R.string.exchange_rate_part3));

                msg.append(myFormattedString(ntdAmount));
                msg.append(getResources().getString(R.string.result_msg_part1));
                msg.append(myFormattedString(usdAmount));
                msg.append(getResources().getString(R.string.result_msg_part2));
                msg.append(myFormattedString(jpyAmount));
                msg.append(getResources().getString(R.string.result_msg_part3));
                return msg.toString();
            }


            private String myFormattedString(double ntdAmount) {
                return String.format(getResources().getString(R.string.str_format), ntdAmount);
            }
        });

    }
}

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
                            .setTitle("Result")
                            .setMessage(message)
                            .setPositiveButton("OK", null)
                            .show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Input NTD Quantity!",
                            Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Problem")
                            .setMessage("Please enter your NTD amount")
                            .setPositiveButton("OK", null)
                            .show();
                }
            }

            private String constructMessage(double ntdAmount, double usdAmount, double jpyAmount) {
                StringBuilder msg = new StringBuilder();
                msg.append("Exchange rate: 1 USD = ");
                msg.append(USD2NTD);
                msg.append(" NTD, and 1 JPY = ");
                msg.append(JPY2NTD);
                msg.append(" NTD. \n\n");

                msg.append(myFormattedString(ntdAmount));
                msg.append(" NTD can exchange \n");
                msg.append(myFormattedString(usdAmount));
                msg.append(" USD or \n");
                msg.append(myFormattedString(jpyAmount));
                msg.append(" JPY.");
                return msg.toString();
            }


            private String myFormattedString(double ntdAmount) {
                return String.format("%.2f", ntdAmount);
            }
        });

    }
}

package com.home.currency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static double USD2NTD = 30.77;
    public static double JPY2NTD = 0.2829;
    private EditText etNTD;
    private TextView tvUSD;
    private TextView tvJPY;
    private Button btGo;

    private double ntdQuantity = 0;
    private double usdQuantity = 0;
    private double jpyQuantity = 0;

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
                    ntdQuantity = Double.valueOf(tempNTD);
                    usdQuantity = ntdQuantity / USD2NTD;
                    jpyQuantity = ntdQuantity / JPY2NTD;
                    tvUSD.setText(Double.toString(usdQuantity));
                    tvJPY.setText(Double.toString(jpyQuantity));
                }
                else{
                    Toast.makeText(MainActivity.this, "Input NTD Quantity!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

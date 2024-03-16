package com.example.rizkyleoquiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText namaEditText;
    EditText kodeEditText;
    EditText jumlahEditText;
    RadioButton goldRadioButton;
    RadioButton silverRadioButton;
    RadioButton biasaRadioButton;
    Button processButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        namaEditText = findViewById(R.id.nama);
        kodeEditText = findViewById(R.id.kode);
        jumlahEditText = findViewById(R.id.jumlah);
        goldRadioButton = findViewById(R.id.rgold);
        silverRadioButton = findViewById(R.id.rsilver);
        biasaRadioButton = findViewById(R.id.rbiasa);
        processButton = findViewById(R.id.process_button);


        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve data from EditText fields
                String nama = namaEditText.getText().toString();
                String kode = kodeEditText.getText().toString();
                int jumlah = Integer.parseInt(jumlahEditText.getText().toString());


                String namaBarang = "";
                double hargaBarang = 0;


                switch (kode) {
                    case "OAS":
                        namaBarang = "Oppo A5s";
                        hargaBarang = 1989123;
                        break;
                    case "AAE":
                        namaBarang = "Acer Aspire E14";
                        hargaBarang = 8676981;
                        break;
                    case "AV4":
                        namaBarang = "Asus Vivobook 14";
                        hargaBarang = 9150999;
                        break;
                    default:
                        // If kode is not recognized, show an error message and return
                        Toast.makeText(MainActivity.this, "Kode barang tidak dikenali", Toast.LENGTH_SHORT).show();
                        return;
                }


                double totalHarga = hargaBarang * jumlah;


                double diskon = 0;
                if (goldRadioButton.isChecked()) {
                    diskon = 0.1; // 10% discount for Gold
                } else if (silverRadioButton.isChecked()) {
                    diskon = 0.05; // 5% discount for Silver
                } else if (biasaRadioButton.isChecked()) {
                    diskon = 0.02; // 2% discount for Biasa
                }


                if (totalHarga > 10000000) {
                    totalHarga -= 100000;
                }


                totalHarga -= totalHarga * diskon;

                Intent intent = new Intent(MainActivity.this, TampilanHasil.class);
                intent.putExtra("totalHarga", totalHarga); // Pass totalHarga to TampilanHasil
                intent.putExtra("nama", nama);
                intent.putExtra("kode", kode);
                intent.putExtra("namaBarang", namaBarang); // Pass namaBarang to TampilanHasil
                intent.putExtra("hargaBarang", hargaBarang); // Pass hargaBarang to TampilanHasil
                intent.putExtra("diskon", diskon);
                startActivity(intent);
            }
        });
    }
}

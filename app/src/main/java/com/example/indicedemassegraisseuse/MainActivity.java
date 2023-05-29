package com.example.indicedemassegraisseuse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView _lblResultat,_lblInterpretation;
    EditText _edtTaille,_edtpoids,_edtAge;
    RadioButton _rdbHomme,_rdbFemme;
    RadioGroup _rdgHomFem;
    Button _btnCalculIMG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _edtTaille=(EditText) findViewById(R.id.txtTaille);
        _edtpoids=(EditText) findViewById(R.id.txtPoids);
        _edtAge=(EditText) findViewById(R.id.txtAge);
        _rdbHomme=(RadioButton) findViewById(R.id.rdbHomme);
        _rdbFemme=(RadioButton)findViewById(R.id.rdbFemme);
        _rdgHomFem=(RadioGroup)findViewById(R.id.rdgHomFem);
        _lblResultat=(TextView)findViewById(R.id.lblResultat);
        _lblInterpretation=(TextView) findViewById(R.id.lblInterpretation);
        _btnCalculIMG=(Button) findViewById(R.id.btnCalculIMG);
        _btnCalculIMG.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(_edtTaille.getText().toString().equals("")||_edtpoids.getText().toString().equals("")||_edtAge.getText().toString().equals("")||(!_rdbHomme.isChecked()&&!_rdbFemme.isChecked()))
                    return;
                double IMC;
                IMC=Float.parseFloat(_edtpoids.getText().toString())/(Float.parseFloat(_edtTaille.getText().toString())*Float.parseFloat(_edtTaille.getText().toString()));
                double IMG=0;
                int Age=Integer.parseInt(_edtAge.getText().toString());
                if(Age>=16) {
                    if (_rdbHomme.isChecked()) {
                        IMG=(1.20 * IMC) + (0.23 * Age) - (10.8 * 1) - 5.4;
                    }
                    if (_rdbFemme.isChecked()) {
                        IMG= (1.20 * IMC) + (0.23 *Age) - (10.8 * 0) - 5.4;
                    }
                }else {
                    if(_rdbHomme.isChecked())
                        IMG=(1.51*IMC)+(0.70*Age)-(3.6*1)+1.4;
                    if(_rdbFemme.isChecked())
                        IMG=(1.51*IMC)+(0.70*Age)-(3.6*0)+1.4;
                }
                Toast.makeText(getApplicationContext(),String.valueOf(IMG),Toast.LENGTH_LONG).show();
                _lblResultat.setText(String.valueOf(IMG));
                if (_rdbFemme.isChecked()){
                    if (IMG<25)
                        Toast.makeText(getApplicationContext(), "Trop maigre",Toast.LENGTH_LONG).show();
                        _lblInterpretation.setText("Trop maigre");
                    if ((IMG>=25)&&(IMG<=30))
                        Toast.makeText(getApplicationContext(),"Pourcentage normal",Toast.LENGTH_LONG).show();
                        _lblInterpretation.setText("Pourcentage normal");
                    if (IMG>30)
                        Toast.makeText(getApplicationContext(),"Trop de graisse",Toast.LENGTH_LONG).show();
                        _lblInterpretation.setText("Trop de graisse");

                }
                if (_rdbHomme.isChecked())
                    if (IMG<15)
                        Toast.makeText(getApplicationContext(), "trop maigre",Toast.LENGTH_LONG).show();
                        _lblInterpretation.setText("Trop maigre");
                    if ((IMG>=15)&&(IMG<=20))
                        Toast.makeText(getApplicationContext(),"Pourcentage normal",Toast.LENGTH_LONG).show();
                        _lblInterpretation.setText("Pourcentage normal");
                    if (IMG>20)
                        Toast.makeText(getApplicationContext(),"Trop de graisse",Toast.LENGTH_LONG).show();
                        _lblInterpretation.setText("Trop de graisse");

            }
        });

    }
}
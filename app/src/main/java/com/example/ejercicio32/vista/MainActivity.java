package com.example.ejercicio32.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ejercicio32.R;
import com.example.ejercicio32.modelo.empleado;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtNombre)
    EditText txtNombre;

    @BindView(R.id.txtApellido)
    EditText txtApellido;

    @BindView(R.id.txtEdad)
    EditText txtEdad;

    @BindView(R.id.txtDireccion)
    EditText txtDireccion;

    @BindView(R.id.txtPuesto)
    EditText txtPuesto;

    @BindView(R.id.btnGuardar)
    Button btnGuardar;

    @BindView(R.id.btnEmpleados)
    Button btnEmpleados;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("empleado");

        btnGuardar.setOnClickListener(v -> {
            String nom = txtNombre.getText().toString();
            String ape = txtApellido.getText().toString();
            int edad = Integer.parseInt(txtEdad.getText().toString());
            String direc = txtDireccion.getText().toString();
            String puesto = txtPuesto.getText().toString();


            empleado mempleado = new empleado(nom, ape, edad, direc, puesto);
            String id = mDatabaseReference.push().getKey();
            if (id != null) {
                mDatabaseReference.child(id).setValue(mempleado);
                Toast.makeText(this,"Guardado con exito",Toast.LENGTH_LONG).show();
                Clean();
            }

        });

        btnEmpleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Clean(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        txtDireccion.setText("");
        txtPuesto.setText("");
    }


}
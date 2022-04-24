package com.example.ejercicio32.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio32.R;
import com.example.ejercicio32.modelo.empleado;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ModificarActivity extends AppCompatActivity {

    @BindView(R.id.txtEditNombre)
    EditText txtEditNombre;

    @BindView(R.id.txtEditApellido)
    EditText txtEditApellido;

    @BindView(R.id.txtEditEdad)
    EditText txtEditEdad;

    @BindView(R.id.txtEditDireccion)
    EditText txtEditDireccion;

    @BindView(R.id.txtEditPuesto)
    EditText txtEditPuesto;

    @BindView(R.id.btnActualizar)
    Button btnActualizar;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        ButterKnife.bind(this);
        String mKey= Objects.requireNonNull(getIntent().getExtras()).getString("key");

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("empleado").child(mKey);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                empleado empleado = dataSnapshot.getValue(com.example.ejercicio32.modelo.empleado.class);

                if (empleado.getNombre() != null) {
                    txtEditNombre.setText(empleado.getNombre());
                }

                if (empleado.getApellido()!= null) {
                    txtEditApellido.setText(empleado.getApellido());
                }

                if (empleado.getEdad() != 0) {
                    txtEditEdad.setText(""+empleado.getEdad());
                }

                if (empleado.getDireccion()!= null) {
                    txtEditDireccion.setText(empleado.getDireccion());
                }

                if (empleado.getPuesto()!= null) {
                    txtEditPuesto.setText(empleado.getPuesto());
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(ModificarActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnActualizar.setOnClickListener(v -> {
            mDatabaseReference.child("nombre").setValue(txtEditNombre.getText().toString());
            mDatabaseReference.child("apellido").setValue(txtEditApellido.getText().toString());
            mDatabaseReference.child("edad").setValue(Integer.parseInt(txtEditEdad.getText().toString()));
            mDatabaseReference.child("direccion").setValue(txtEditDireccion.getText().toString());
            mDatabaseReference.child("puesto").setValue(txtEditPuesto.getText().toString());
            finish();
            Toast.makeText(this,"Registro actualizado correctamente",Toast.LENGTH_LONG).show();
        });
    }
}
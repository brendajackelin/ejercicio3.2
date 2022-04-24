package com.example.ejercicio32.vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ejercicio32.R;
import com.example.ejercicio32.controlador.EmpleadoAdapter;
import com.example.ejercicio32.modelo.empleado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.empleadoRecyclerView)
    RecyclerView mRecyclerView;

    @SuppressLint("NonConstantResourceId")

    EmpleadoAdapter mEmpleadoAdapter;

    LinearLayoutManager mLayoutManager;

    private ArrayList<empleado> mListEmpleado;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        ButterKnife.bind(this);

        mListEmpleado = new ArrayList<>();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("empleado");

        Recycler();
    }

    public void Recycler() {

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mEmpleadoAdapter = new EmpleadoAdapter(mListEmpleado);
        mRecyclerView.setAdapter(mEmpleadoAdapter);
        Content();
        deleteSwipe();
    }

    private void Content() {

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mListEmpleado.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    empleado empleado = postSnapshot.getValue(com.example.ejercicio32.modelo.empleado.class);

                    if (empleado != null) {
                        empleado.setKey(postSnapshot.getKey());
                    }

                    mListEmpleado.add(empleado);

                }
                mEmpleadoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(ListaActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteSwipe() {

        ItemTouchHelper.SimpleCallback touchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mDatabaseReference.child(mListEmpleado.get(viewHolder.getAdapterPosition()).getKey()).setValue(null);
                mEmpleadoAdapter.deleteItem(viewHolder.getAdapterPosition());
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(touchHelperCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
}
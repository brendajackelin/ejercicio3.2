
package com.example.ejercicio32.controlador;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicio32.R;
import com.example.ejercicio32.modelo.empleado;
import com.example.ejercicio32.vista.ModificarActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EmpleadoAdapter extends RecyclerView.Adapter<ViewHolder>{


    private final List<empleado> mEmpleadoList;

    public EmpleadoAdapter(List<empleado> characterList) {
        mEmpleadoList = characterList;
    }

    @Override
    public void onBindViewHolder(com.example.ejercicio32.controlador.ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public com.example.ejercicio32.controlador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.contenido, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mEmpleadoList != null & mEmpleadoList.size() > 0) {
            return mEmpleadoList.size();
        } else {
            return 0;
        }
    }

    public void addItems(List<empleado> empleadoList) {
        mEmpleadoList.addAll(empleadoList);
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        if (mEmpleadoList != null & mEmpleadoList.size() > 0) {
            mEmpleadoList.remove(position);
        }
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public class ViewHolder extends com.example.ejercicio32.controlador.ViewHolder {

        @BindView(R.id.txtNomMostrar)
        TextView txtNomMostrar;

        @BindView(R.id.txtApeMostrar)
        TextView txtApeMostrar;

        @BindView(R.id.txtEdadMostrar)
        TextView txtEdadMostrar;

        @BindView(R.id.txtDirecMostrar)
        TextView txtDirecMostrar;

        @BindView(R.id.txtPuestoMostrar)
        TextView txtPuestoMostrar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            txtNomMostrar.setText("");
            txtApeMostrar.setText("");
            txtEdadMostrar.setText("");
            txtDirecMostrar.setText("");
            txtPuestoMostrar.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            empleado mempleado= mEmpleadoList.get(position);

            if (mempleado.getNombre() != null) {
                txtNomMostrar.setText(mempleado.getNombre());
            }

            if (mempleado.getApellido() != null) {
                txtApeMostrar.setText(mempleado.getApellido());
            }

            if (mempleado.getEdad() != 0) {
                txtEdadMostrar.setText(""+mempleado.getEdad());
            }

            if (mempleado.getDireccion() != null) {
                txtDirecMostrar.setText(mempleado.getDireccion());
            }

            if (mempleado.getPuesto() != null) {
                txtPuestoMostrar.setText(mempleado.getPuesto());
            }

            itemView.setOnLongClickListener(v -> {
                Intent intent=new Intent(itemView.getContext(), ModificarActivity.class);
                intent.putExtra("key",  mempleado.getKey());
                itemView.getContext().startActivity(intent);
                return false;
            });

        }
    }

}
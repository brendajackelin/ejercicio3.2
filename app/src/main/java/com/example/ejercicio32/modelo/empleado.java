package com.example.ejercicio32.modelo;

import com.google.firebase.database.Exclude;

public class empleado {

    private String key;
    private String Nombre;
    private String Apellido;
    private int edad;
    private String direccion;
    private String Puesto;

    public empleado(){

    }

    public empleado(String nombre, String apellido, int edad, String direccion, String puesto) {
        Nombre = nombre;
        Apellido = apellido;
        this.edad = edad;
        this.direccion = direccion;
        Puesto = puesto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String puesto) {
        Puesto = puesto;
    }

    @Exclude
    public String getKey() {
        return key;
    }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }
}

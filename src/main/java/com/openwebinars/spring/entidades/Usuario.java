package com.openwebinars.spring.entidades;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(nullable = false)
    private String contraseña;

    @Column(nullable = false)
    private String rol;

    @Column(nullable = false, length = 800)
    private String direccion;

    @Column(nullable = false, length = 50)
    private String telefono;

    @OneToMany(mappedBy = "usuario")
    private Set<Pedidos> pedidos = new HashSet<>();

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String email, String dni, String contraseña, String rol,
            String direccion, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.dni = dni;
        this.contraseña = contraseña;
        this.rol = rol;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public void addPedido(Pedidos pedido) {
        this.pedidos.add(pedido);
        pedido.setUsuario(this);
    }

    public void removePedido(Pedidos pedido) {
        this.pedidos.remove(pedido);
        pedido.setUsuario(null);
    }

    public Set<Pedidos> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedidos> pedidos) {
        this.pedidos = pedidos;
    }

    public Pedidos getPedidoById(Long id) {
        return pedidos.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

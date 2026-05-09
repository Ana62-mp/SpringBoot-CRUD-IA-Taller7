package com.krakedev.videojuegos.entidades;

public class Videojuego {
	private String nombre;
	private int codigo;
	private String plataforma;
	private String genero;
	private double precio;
	private int stock;

	public Videojuego() {
	}

	public Videojuego(String nombre, int codigo, String plataforma, String genero, double precio, int stock) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.plataforma = plataforma;
		this.genero = genero;
		this.precio = precio;
		this.stock = stock;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Videojuego [nombre=" + nombre + ", codigo=" + codigo + ", plataforma=" + plataforma + ", genero="
				+ genero + ", precio=" + precio + ", stock=" + stock + "]";
	}
}
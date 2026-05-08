package com.krakedev.videojuegos.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.krakedev.videojuegos.entidades.Videojuego;

@Service
public class VideojuegoService {
	private ArrayList<Videojuego> videojuegos;

	public VideojuegoService() {
		videojuegos = new ArrayList<Videojuego>();
	}

	public Videojuego crear(Videojuego videojuego) {
		Videojuego videojuegoExiste = buscarPorCodigo(videojuego.getCodigo());

		if (videojuegoExiste != null) {
			return null;
		} else {
			videojuegos.add(videojuego);
			return videojuego;
		}
	}

	public ArrayList<Videojuego> listar() {
		return videojuegos;
	}

	public Videojuego buscarPorCodigo(int codigo) {
		for (Videojuego videojuego : videojuegos) {
			if (videojuego.getCodigo() == codigo) {
				return videojuego;
			}
		}

		return null;
	}

	public Videojuego actualizar(int codigo, Videojuego videojuegoActualizado) {
		Videojuego videojuegoExiste = buscarPorCodigo(codigo);

		if (videojuegoExiste != null) {
			videojuegoExiste.setNombre(videojuegoActualizado.getNombre());
			videojuegoExiste.setPlataforma(videojuegoActualizado.getPlataforma());
			videojuegoExiste.setPrecio(videojuegoActualizado.getPrecio());
			videojuegoExiste.setStock(videojuegoActualizado.getStock());

			return videojuegoExiste;
		} else {
			return null;
		}
	}

	public boolean eliminar(int codigo) {
		Videojuego videojuegoExiste = buscarPorCodigo(codigo);

		if (videojuegoExiste != null) {
			videojuegos.remove(videojuegoExiste);
			return true;
		} else {
			return false;
		}
	}
}
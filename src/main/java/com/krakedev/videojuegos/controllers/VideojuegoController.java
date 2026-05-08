package com.krakedev.videojuegos.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.videojuegos.entidades.Videojuego;
import com.krakedev.videojuegos.services.VideojuegoService;

@RestController
@RequestMapping("/videojuegos")
public class VideojuegoController {
	private VideojuegoService videojuegoService;

	public VideojuegoController(VideojuegoService videojuegoService) {
		this.videojuegoService = videojuegoService;
	}

	@PostMapping
	public Videojuego crear(@RequestBody Videojuego videojuego) {
		return videojuegoService.crear(videojuego);
	}

	@GetMapping
	public ArrayList<Videojuego> listar() {
		return videojuegoService.listar();
	}

	@GetMapping("/{codigo}")
	public Videojuego buscarPorCodigo(@PathVariable int codigo) {
		return videojuegoService.buscarPorCodigo(codigo);
	}

	@PutMapping("/{codigo}")
	public Videojuego actualizar(@PathVariable int codigo, @RequestBody Videojuego videojuegoActualizado) {

		return videojuegoService.actualizar(codigo, videojuegoActualizado);
	}

	@DeleteMapping("/{codigo}")
	public boolean eliminar(@PathVariable int codigo) {
		return videojuegoService.eliminar(codigo);
	}
}
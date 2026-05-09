package com.krakedev.videojuegos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.videojuegos.entidades.Videojuego;
import com.krakedev.videojuegos.services.VideojuegoService;

public class VideojuegoServiceTest {

	private VideojuegoService videojuegoService;

	@BeforeEach
	public void setUp() {
		videojuegoService = new VideojuegoService();
	}

	@Test
	public void testCrearVideojuegoNuevo() {
		Videojuego videojuego = new Videojuego("Resident Evil 4", 201, "PC", "Terror", 39.99, 10);

		Videojuego resultado = videojuegoService.crear(videojuego);

		assertNotNull(resultado);
		assertEquals(201, resultado.getCodigo());
		assertEquals("Resident Evil 4", resultado.getNombre());
		assertEquals("PC", resultado.getPlataforma());
		assertEquals("Terror", resultado.getGenero());
		assertEquals(39.99, resultado.getPrecio());
		assertEquals(10, resultado.getStock());
		assertEquals(1, videojuegoService.listar().size());
	}

	@Test
	public void testCrearVideojuegoDuplicadoRetornaNull() {
		Videojuego videojuego1 = new Videojuego("Resident Evil 4", 201, "PC", "Terror", 39.99, 10);
		Videojuego videojuego2 = new Videojuego("Call of Duty", 201, "PlayStation 5", "Guerra", 69.99, 8);

		videojuegoService.crear(videojuego1);
		Videojuego resultado = videojuegoService.crear(videojuego2);

		assertNull(resultado);
		assertEquals(1, videojuegoService.listar().size());
	}

	@Test
	public void testBuscarPorCodigoCuandoExiste() {
		Videojuego videojuego = new Videojuego("Call of Duty", 202, "PlayStation 5", "Guerra", 69.99, 8);

		videojuegoService.crear(videojuego);
		Videojuego resultado = videojuegoService.buscarPorCodigo(202);

		assertNotNull(resultado);
		assertEquals(202, resultado.getCodigo());
		assertEquals("Call of Duty", resultado.getNombre());
		assertEquals("PlayStation 5", resultado.getPlataforma());
		assertEquals("Guerra", resultado.getGenero());
		assertEquals(69.99, resultado.getPrecio());
		assertEquals(8, resultado.getStock());
	}

	@Test
	public void testBuscarPorCodigoCuandoNoExiste() {
		Videojuego resultado = videojuegoService.buscarPorCodigo(999);

		assertNull(resultado);
	}

	@Test
	public void testListarVideojuegosVacio() {
		List<Videojuego> videojuegos = videojuegoService.listar();

		assertNotNull(videojuegos);
		assertEquals(0, videojuegos.size());
	}

	@Test
	public void testListarVideojuegosConDatos() {
		Videojuego videojuego1 = new Videojuego("Resident Evil 4", 201, "PC", "Terror", 39.99, 10);
		Videojuego videojuego2 = new Videojuego("FIFA 24", 203, "PlayStation 5", "Deportes", 59.99, 12);

		videojuegoService.crear(videojuego1);
		videojuegoService.crear(videojuego2);

		List<Videojuego> videojuegos = videojuegoService.listar();

		assertEquals(2, videojuegos.size());
		assertEquals(201, videojuegos.get(0).getCodigo());
		assertEquals("Terror", videojuegos.get(0).getGenero());
		assertEquals(203, videojuegos.get(1).getCodigo());
		assertEquals("Deportes", videojuegos.get(1).getGenero());
	}

	@Test
	public void testActualizarVideojuegoExistente() {
		Videojuego videojuego = new Videojuego("Minecraft", 204, "PC", "Aventura", 26.95, 15);
		Videojuego videojuegoActualizado = new Videojuego("Minecraft Deluxe Edition", 999, "PC", "Supervivencia", 35.50,
				20);

		videojuegoService.crear(videojuego);
		Videojuego resultado = videojuegoService.actualizar(204, videojuegoActualizado);

		assertNotNull(resultado);
		assertEquals(204, resultado.getCodigo());
		assertEquals("Minecraft Deluxe Edition", resultado.getNombre());
		assertEquals("PC", resultado.getPlataforma());
		assertEquals("Supervivencia", resultado.getGenero());
		assertEquals(35.50, resultado.getPrecio());
		assertEquals(20, resultado.getStock());
	}

	@Test
	public void testActualizarVideojuegoNoExistenteRetornaNull() {
		Videojuego videojuegoActualizado = new Videojuego("Mario Kart 8 Deluxe", 999, "Nintendo Switch", "Carreras",
				59.99, 7);

		Videojuego resultado = videojuegoService.actualizar(999, videojuegoActualizado);

		assertNull(resultado);
	}

	@Test
	public void testEliminarVideojuegoExistente() {
		Videojuego videojuego = new Videojuego("FIFA 24", 203, "PlayStation 5", "Deportes", 59.99, 12);

		videojuegoService.crear(videojuego);
		boolean resultado = videojuegoService.eliminar(203);

		assertTrue(resultado);
		assertEquals(0, videojuegoService.listar().size());
		assertNull(videojuegoService.buscarPorCodigo(203));
	}

	@Test
	public void testEliminarVideojuegoNoExistente() {
		boolean resultado = videojuegoService.eliminar(999);

		assertFalse(resultado);
		assertEquals(0, videojuegoService.listar().size());
	}

	@Test
	public void testCrearVariosVideojuegosYBuscarUnoEspecifico() {
		Videojuego videojuego1 = new Videojuego("Resident Evil 4", 201, "PC", "Terror", 39.99, 10);
		Videojuego videojuego2 = new Videojuego("Call of Duty", 202, "PlayStation 5", "Guerra", 69.99, 8);
		Videojuego videojuego3 = new Videojuego("Mario Kart 8 Deluxe", 205, "Nintendo Switch", "Carreras", 59.99, 7);

		videojuegoService.crear(videojuego1);
		videojuegoService.crear(videojuego2);
		videojuegoService.crear(videojuego3);

		Videojuego resultado = videojuegoService.buscarPorCodigo(202);

		assertNotNull(resultado);
		assertEquals("Call of Duty", resultado.getNombre());
		assertEquals("PlayStation 5", resultado.getPlataforma());
		assertEquals("Guerra", resultado.getGenero());
		assertEquals(69.99, resultado.getPrecio());
		assertEquals(8, resultado.getStock());
	}

	@Test
	public void testEliminarUnVideojuegoSinAfectarLosDemas() {
		Videojuego videojuego1 = new Videojuego("Resident Evil 4", 201, "PC", "Terror", 39.99, 10);
		Videojuego videojuego2 = new Videojuego("Call of Duty", 202, "PlayStation 5", "Guerra", 69.99, 8);

		videojuegoService.crear(videojuego1);
		videojuegoService.crear(videojuego2);

		boolean resultado = videojuegoService.eliminar(201);

		assertTrue(resultado);
		assertEquals(1, videojuegoService.listar().size());
		assertNull(videojuegoService.buscarPorCodigo(201));
		assertNotNull(videojuegoService.buscarPorCodigo(202));
	}
}
package com.krakedev.videojuegos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.krakedev.videojuegos.entidades.Videojuego;
import com.krakedev.videojuegos.services.VideojuegoService;

public class VideojuegoServiceTest {

    @Test
    public void crearVideojuegoExitoso() {
        VideojuegoService service = new VideojuegoService();
        Videojuego videojuego = new Videojuego("Minecraft", 201, "PC", 26.95, 15);

        Videojuego resultado = service.crear(videojuego);

        // Valida que se cree el videojuego cuando no existe otro con el mismo código
        assertEquals(videojuego, resultado);
        assertEquals(1, service.listar().size());
    }

    @Test
    public void crearVideojuegoDuplicado() {
        VideojuegoService service = new VideojuegoService();
        Videojuego videojuego1 = new Videojuego("Minecraft", 201, "PC", 26.95, 15);
        Videojuego videojuego2 = new Videojuego("FIFA 24", 201, "PlayStation 5", 59.99, 10);

        service.crear(videojuego1);
        Videojuego resultado = service.crear(videojuego2);

        // Valida que no se cree un videojuego si ya existe otro con el mismo código
        assertNull(resultado);
        assertEquals(1, service.listar().size());
    }

    @Test
    public void buscarVideojuegoExistente() {
        VideojuegoService service = new VideojuegoService();
        Videojuego videojuego = new Videojuego("The Legend of Zelda", 202, "Nintendo Switch", 69.99, 8);

        service.crear(videojuego);
        Videojuego resultado = service.buscarPorCodigo(202);

        // Valida que se encuentre un videojuego existente por su código
        assertEquals(videojuego, resultado);
    }

    @Test
    public void buscarVideojuegoNoExistente() {
        VideojuegoService service = new VideojuegoService();

        Videojuego resultado = service.buscarPorCodigo(999);

        // Valida que retorne null cuando no existe un videojuego con ese código
        assertNull(resultado);
    }

    @Test
    public void listarVideojuegos() {
        VideojuegoService service = new VideojuegoService();
        Videojuego videojuego1 = new Videojuego("Minecraft", 201, "PC", 26.95, 15);
        Videojuego videojuego2 = new Videojuego("FIFA 24", 203, "PlayStation 5", 59.99, 12);

        service.crear(videojuego1);
        service.crear(videojuego2);

        ArrayList<Videojuego> videojuegos = service.listar();

        // Valida que la lista retorne todos los videojuegos creados
        assertEquals(2, videojuegos.size());
        assertEquals(videojuego1, videojuegos.get(0));
        assertEquals(videojuego2, videojuegos.get(1));
    }

    @Test
    public void actualizarVideojuegoExistente() {
        VideojuegoService service = new VideojuegoService();
        Videojuego videojuego = new Videojuego("Minecraft", 201, "PC", 26.95, 15);
        Videojuego videojuegoActualizado = new Videojuego("Minecraft Deluxe Edition", 201, "PC", 35.50, 20);

        service.crear(videojuego);
        Videojuego resultado = service.actualizar(201, videojuegoActualizado);

        // Valida que se actualicen nombre, plataforma, precio y stock
        assertEquals("Minecraft Deluxe Edition", resultado.getNombre());
        assertEquals("PC", resultado.getPlataforma());
        assertEquals(35.50, resultado.getPrecio());
        assertEquals(20, resultado.getStock());
        assertEquals(201, resultado.getCodigo());
    }

    @Test
    public void actualizarVideojuegoNoExistente() {
        VideojuegoService service = new VideojuegoService();
        Videojuego videojuegoActualizado = new Videojuego("Mario Kart 8 Deluxe", 999, "Nintendo Switch", 59.99, 7);

        Videojuego resultado = service.actualizar(999, videojuegoActualizado);

        // Valida que retorne null cuando se intenta actualizar un videojuego que no existe
        assertNull(resultado);
    }

    @Test
    public void eliminarVideojuegoExistente() {
        VideojuegoService service = new VideojuegoService();
        Videojuego videojuego = new Videojuego("FIFA 24", 203, "PlayStation 5", 59.99, 12);

        service.crear(videojuego);
        boolean resultado = service.eliminar(203);

        // Valida que se elimine correctamente un videojuego existente
        assertTrue(resultado);
        assertEquals(0, service.listar().size());
    }

    @Test
    public void eliminarVideojuegoNoExistente() {
        VideojuegoService service = new VideojuegoService();

        boolean resultado = service.eliminar(999);

        // Valida que retorne false cuando se intenta eliminar un videojuego que no existe
        assertFalse(resultado);
    }
}

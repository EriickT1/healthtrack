package com.healthtrack;

import com.healthtrack.model.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//public
class UsuarioTest {
    @Test
    void testActualizarPeso() {
        Usuario usuario = new Usuario("Erick", 70.0);
        usuario.actualizarPeso(75.0);
        assertEquals(75.0, usuario.getPeso());
    }
}
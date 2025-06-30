package com.healthtrack.controller;

//import com.healthtrack.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

//import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
// public
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    // public
    void testMostrarFormulario() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("usuario"));
    }

    @Test
    // public
    void testActualizarPeso() throws Exception {
        mockMvc.perform(post("/actualizar")
                .param("peso", "75.0"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("usuario"));
    }
}

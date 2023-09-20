package com.crud.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.crud.demo.modelo.Mascota;
import com.crud.demo.modeloDAO.IMascota;
import com.crud.demo.service.MascotaService;

public class CrudSpringBootTest {

    @InjectMocks
   // UTILICE MASCOTA SERVICE PORQUE ESO ABARCA TODO EL CRUD.(AGREGAR,EDITAR,ELIMINAR)
    private MascotaService mascotaService;

    @Mock
    private IMascota dao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testListar() {
         //Datos de ejemplo
        List<Mascota> listaMascotas = new ArrayList<>();
        listaMascotas.add(new Mascota(1, "Perro1", "Macho", "Raza1"));
        listaMascotas.add(new Mascota(2, "Perro2", "Hembra", "Raza2"));
        when(dao.findAll()).thenReturn(listaMascotas);
        List<Mascota> resultado = mascotaService.listar();
        // Verifica que la lista resultante no sea nula y tenga la longitud esperada
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @Test
    void testListarId() {
        // Define un objeto de ejemplo
        Mascota mascotaEjemplo = new Mascota(1, "Perro1", "Macho", "Raza1");
        when(dao.findById(1)).thenReturn(Optional.of(mascotaEjemplo));
        Optional<Mascota> resultado = mascotaService.listarId(1);
        // Verifica que el resultado no sea nulo y sea igual al objeto de ejemplo
        assertNotNull(resultado);
        assertTrue(resultado.isPresent());
        assertEquals(mascotaEjemplo, resultado.get());
    }

    @Test
    void testListarId1() {
        // Definir un objeto de ejemplo
        Mascota mascotaEjemplo = new Mascota(1, "Perro1", "Macho", "Raza1");
        //Devuelve el objeto de ejemplo
        when(dao.findById(1)).thenReturn(Optional.of(mascotaEjemplo));
        Optional<Mascota> resultado = mascotaService.listarId(1);
        // Verifica que el resultado no sea nulo y sea igual al objeto de ejemplo
        assertNotNull(resultado);
        assertTrue(resultado.isPresent());
        assertEquals(mascotaEjemplo, resultado.get());
    }

    @Test
    void testSave() {
        // Define un objeto de ejemplo a guardar
        Mascota mascotaEjemplo = new Mascota(1, "Perro1", "Macho", "Raza1");
        when(dao.save(mascotaEjemplo)).thenReturn(mascotaEjemplo);
        int resultado = mascotaService.save(mascotaEjemplo);
        // Verifica que el resultado sea 1, lo que indica éxito en la inserción
        assertEquals(1, resultado);
    }

    @Test
    void testDelete() {
        // Llamar al método delete del servicio
        mascotaService.delete(1);
        // Verifica que se haya llamado a dao.deleteById con el argumento 1
        verify(dao, times(1)).deleteById(1);
    }
}
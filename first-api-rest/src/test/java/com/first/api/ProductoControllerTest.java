package com.first.api;


import com.first.api.controller.ProductoController;
import com.first.api.model.Producto;
import com.first.api.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//Suscribete a La Tecnologia Avanza
public class ProductoControllerTest {

    //New Comment 1
    @InjectMocks
    private ProductoController productoController;

    //New Comment 2
    @Mock
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void agregarProducto_CuandoSeLlama_DevuelveProducto() {
        Producto producto = new Producto(1, "Producto 1", 10, 100.0);
        when(productoService.saveProducto(any(Producto.class))).thenReturn(producto);

        Producto resultado = productoController.agregarProducto(producto);

        assertNotNull(resultado);
        assertEquals(producto.getId(), resultado.getId());
        verify(productoService, times(1)).saveProducto(producto);
    }

    @Test
    void listarProductos_CuandoSeLlama_DevuelveListaDeProductos() {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "Producto 1", 10, 100.0));
        productos.add(new Producto(2, "Producto 2", 5, 50.0));
        when(productoService.getProductos()).thenReturn(productos);

        List<Producto> resultado = productoController.listarProductos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(productoService, times(1)).getProductos();
    }

    @Test
    void buscarProducto_CuandoSeLlamaConId_DevuelveProducto() {
        Producto producto = new Producto(1, "Producto 1", 10, 100.0);
        when(productoService.getProductoById(1)).thenReturn(producto);

        Producto resultado = productoController.buscarProducto(1);

        assertNotNull(resultado);
        assertEquals(producto.getId(), resultado.getId());
        verify(productoService, times(1)).getProductoById(1);
    }

    @Test
    void actualizarProducto_CuandoSeLlama_DevuelveProductoActualizado() {
        Producto producto = new Producto(1, "Producto 1", 10, 100.0);
        when(productoService.updateProducto(any(Producto.class))).thenReturn(producto);

        Producto resultado = productoController.actualizarProducto(producto);

        assertNotNull(resultado);
        assertEquals(producto.getId(), resultado.getId());
        verify(productoService, times(1)).updateProducto(producto);
    }

    @Test
    void eliminarProducto_CuandoSeLlama_DevuelveMensajeDeEliminacion() {
        when(productoService.deleteProducto(1)).thenReturn("Producto eliminado !!1");

        String resultado = productoController.eliminarProducto(1);

        assertNotNull(resultado);
        assertEquals("Producto eliminado !!1", resultado);
        verify(productoService, times(1)).deleteProducto(1);
    }
}
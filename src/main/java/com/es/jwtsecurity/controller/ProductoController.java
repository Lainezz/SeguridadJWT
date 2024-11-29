package com.es.jwtsecurity.controller;

import com.es.jwtsecurity.dto.ProductoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {


    /*
       - *RUTAS PROTEGIDAS* Todas las rutas requieren que el usuario esté autenticado para acceder a las mismas
   - `GET /productos/{id}`: Devuelve la información de un producto.
      - **Entrada**: ID del producto.
      - **Salida**: JSON con `nombre`, `stock`, y `precio`.
   - `GET /productos/byNombre/{nombre}`: Devuelve la información de un producto.
      - **Entrada**: nombre del producto.
      - **Salida**: JSON con `nombre`, `stock`, y `precio`.
   - `GET /productos/asc`: Devuelve todos los productos almacenados en la base de datos ordenados ascendentemente por su *nombre*
      - **Salida**: Lista con los productos : JSON con `nombre`, `stock`, y `precio`.
   - `GET /productos/desc`: Devuelve todos los productos almacenados en la base de datos ordenados descendentemente por su *nombre*
      - **Salida**: Lista con los productos : JSON con `nombre`, `stock`, y `precio`.
   - `POST /productos`: Permite insertar un nuevo producto.
      - *SÓLO ADMIN*: Sólo los usuarios con ROL ADMIN pueden acceder a este recurso
      - **Entrada**: JSON con `nombre`, `stock`, y `precio`.
   - `DELETE /productos/{id}`: Permite eliminar un nuevo producto.
      - *SÓLO ADMIN*: Sólo los usuarios con ROL ADMIN pueden acceder a este recurso
     */


    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getById(@PathVariable String id) {
        // Prueba para ver si funciona
        return new ResponseEntity<>(new ProductoDTO("getProductoById", 1.00, 1), HttpStatus.OK);
    }

    @GetMapping("/byNombre/{nombre}")
    public ResponseEntity<ProductoDTO> getByNombre(@PathVariable String nombre) {
        // Prueba para ver si funciona
        return new ResponseEntity<>(new ProductoDTO("getProductoByNombre", 2.00, 2), HttpStatus.OK);
    }

    @GetMapping("/asc")
    public ResponseEntity<List<ProductoDTO>> getAllAsc() {
        List<ProductoDTO> listaProductos = new ArrayList<>();
        listaProductos.add(new ProductoDTO("getProductosAsc", 3.00, 3));
        // Prueba para ver si funciona
        return new ResponseEntity<>(listaProductos, HttpStatus.OK);
    }

    @GetMapping("/desc")
    public ResponseEntity<List<ProductoDTO>> getAllDesc() {
        List<ProductoDTO> listaProductos = new ArrayList<>();
        listaProductos.add(new ProductoDTO("getProductosDesc", 4.00, 4));
        // Prueba para ver si funciona
        return new ResponseEntity<>(listaProductos, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ProductoDTO> insert(@RequestBody ProductoDTO productoDTO) {
        // Prueba para ver si funciona
        return new ResponseEntity<>(new ProductoDTO("insertProducto", 5.00, 5), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        // Prueba para ver si funciona
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}

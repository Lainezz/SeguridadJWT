package com.es.jwtsecurity.controller;

import com.es.jwtsecurity.dto.ProductoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return null;
    }

    @GetMapping("/byNombre/{nombre}")
    public ResponseEntity<ProductoDTO> getByNombre(@PathVariable String nombre) {
        return null;
    }

    @GetMapping("/asc")
    public ResponseEntity<List<ProductoDTO>> getAllAsc() {
        return null;
    }

    @GetMapping("/desc")
    public ResponseEntity<List<ProductoDTO>> getAllDesc() {
        return null;
    }

    @PostMapping("/")
    public ResponseEntity<ProductoDTO> insert(@RequestBody ProductoDTO productoDTO) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }



}

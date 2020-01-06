/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import daos.ProductoDAO;
import entidades.Producto;
import java.util.List;

/**
 *
 * @author XxkokoxXT
 */
public class ProductoService {
    
    private ProductoDAO productoDAO;
    
    public ProductoService(){
        productoDAO = new ProductoDAO();
    }
    
    public List<Producto> listarProductos(){
        return productoDAO.listarProductos();
    }
 
     public int registrarProducto(String descripcion, String detalle, Integer stock, Double precio, String imagen){
        return productoDAO.registrarProducto(descripcion, detalle, stock,precio,imagen);
    }
    
    public int actualizarProducto(String codigo, String descripcion, String detalle, Integer stock, Double precio, String imagen){
        return productoDAO.actualizarProducto(codigo, descripcion, detalle, stock,precio,imagen);
    }
    
    public List<Producto> validarProducto(String codigo){
        return productoDAO.validarProducto(codigo);
    }
    public int eliminarProducto(String codigo){
        return productoDAO.eliminarProducto(codigo);
    }
}
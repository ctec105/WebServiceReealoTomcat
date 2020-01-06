/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entidades.Producto;
import entidades.Ubicacion;
import entidades.Usuario;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import servicios.ProductoService;
import servicios.UbicacionService;
import servicios.UsuarioService;
import utilidades.Utilidades;

/**
 * REST Web Service
 *
 * @author XxkokoxXT
 */
@Path("testWS")
public class TestWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of testWS
     */
    public TestWS() {
    }
       
    @GET
    @Path("listarUbicaciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUbicaciones(){
        try {
            List<Ubicacion> ubicaciones = new UbicacionService().listarUbicaciones();
            
            String json = new Gson().toJson(ubicaciones);
            
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.SEE_OTHER).entity("Error: " + e.toString()).build();
        }
    }
    
    @GET
    @Path("validarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validarUsuario(@QueryParam("correo") String correo, @QueryParam("contraseña") String contraseña){
        try {
            List<Usuario> usuarios = new UsuarioService().validarUsuario(correo, contraseña);
            
            String json = new Gson().toJson(usuarios);
            
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.SEE_OTHER).entity("Error: " + e.toString()).build();
        }
    }
    
    @GET
    @Path("listarProductos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProductos(){
        try {
            List<Producto> productos = new ProductoService().listarProductos();
            
            String json = new Gson().toJson(productos);
            
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.SEE_OTHER).entity("Error: " + e.toString()).build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("registrarUsuario")
    public int registrarUsuario(String json){
        try {
            Gson gson = new Gson();
            
            Usuario usuario = (Usuario) gson.fromJson(json, Usuario.class);
            
            int resultado = new UsuarioService().registrarUsuario(usuario.getNombre(), usuario.getApellido(), usuario.getCorreo(), usuario.getContraseña());
            
            return resultado;
        } catch (Exception e) {
            return -1;
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("registrarProducto")
    public int registrarProducto(String json){
        try {
            Gson gson = new Gson();
            
            Producto producto = (Producto) gson.fromJson(json, Producto.class);
            
            // decodificar cadena base64 a imagen
            //String imageBase64 = "/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAkGBwgHBgkICAgKCgkLDhcPDg0NDhwUFREXIh4jIyEeICAlKjUtJScyKCAgLj8vMjc5PDw8JC1CRkE6RjU7PDn/2wBDAQoKCg4MDhsPDxs5JiAmOTk5OTk5OTk5OTk5OTk5OTk5OTk5OTk5OTk5OTk5OTk5OTk5OTk5OTk5OTk5OTk5OTn/wgARCAHCAyADAREAAhEBAxEB/8QAGwABAAMBAQEBAAAAAAAAAAAAAAECAwQFBwb/xAAaAQEBAQEBAQEAAAAAAAAAAAAAAQIDBAUG/9oADAMBAAIQAxAAAAD8r8j5zWd9cdJFABA6pmyIACrHTM2QoASde+vfM2AAFX3264sgAC3p6+jqSVAAV2dO3SkgAFOXbr8X0N5AABwct18vTYAAHm+fpp5d3AAB5nLe/Hd5QBELPGx36OHSQADPpz/Nfe+FvJbn2vx9n4P9H+UnfLovO0KACL2dGZMgAC3VOiZIUATHpb6dEzIABF109Ou+ZKAAN9O7fXZCgATrfob3qgAAp5Pd38O94AAHk+Xr08t2QACDyfF26+VkAAoeb5uvXiyoAiOa3h4+jbnZAAPM9/h8z6fy9c0D8P8Aa+Lp04bMyAAI3TWZQAAt6ZnRAABs16NuqAABvr2W6SABSNOnfu1bKAATbp27qsAAJZ8H0+3NmAAMpfN8fbrykAA5ca5/F13yAAHFjVfP01zZoARJ5rpHn7XlAAH5/wC18anfz2gD8P8Ac+HvrjogAAmOlm0iAALW9OcWoAAdmundJZAABfp26y0gAC3p6+jqSVABF12dOvSkgAFOPfr8fv3RAAV5/Hby9NQAAeb5+mnm3eAAB5nHe/HdpZoAQngzt1cOl82QAZ9Of5r73wt4mAPxH3fg9F5WAABeOiZmQAAa10TIACSV9LfTomZAAqGurfXaZkAAb6d++uoAASddPR3rQAAFfJ7u7h3vAACvI8nbq5WwABB5Pi7dfKyAAZx5/Dr1c9SAAcPXz+N6fPv5vXpw9WvLd86tA833+HzPp/L1lQB+L+/+e2zmaAAG8msygAAvSzpMrQAk21v0rdJAAA317LdJAABp17911YAAG3Tr3VYAAjOreH6XbmzAAGMvn+Pt1ZSAAcuLz+LtvkAAOLGo8/TSWQADwvb8/Dr5ryoc/RPD16ef1689eF9n48duF4QB+O+9+d0kUACSvRnN0QAFWXpmLIUAJOzfXumbAABdOnbqksgAC3p6+jqJAAFvZ069SAACnHv1+T3b5AADz+W3m6awAAPM8/TXzbvAAA8vl06OGrqAIkiz8x9L5W7NoAESufo8j2eelXjSJWT8h9385aFAAXTozmYAAW6pvMyhQBK+lrfRMyAARddXTrtmSgADfTv301UAAW3v0Nb0QAAV8nv7uHa8AADyPJ16uerAAER5Pj7dfKyAAZnn+br041JIBEnD34eP7fnbZSAAAVrm1eboxq8flvufnZAAEbprnIAAW9MzogAA2uvSt0kAADfXst0kAAGnTv3atlAAJt07d1WAAImreD6XbmzAAGEvB4+3VlIAByc9YePrvkAAPNTHh16eerxYSgnhe7wY9fNeJAAAABWubb8X9/8APAACY6WbZgAAtXTM2AAB2a6d0zYAAGnXv1TNoAAXXV17dJKgAi67enTpAABTj6Ovx+3eAAFedw3Pm6agAA8zzdNfPu8AADwOng055Z9FsejTn22zbyyz+Y+n8rfObAAAAAAH4D9H+eAAF46JiYAAVsbTMgARZfS3veZkACouurXXaZkAAnfTu301CgAlt9PQ1rQAAFfH7+7j2vAACvI8nbq5WwABWPK8Xbr56lAAMLjw9fP6cJBE3E72x6ebefN9fz+jC0CQAAAAD8B+j/PAADeTWZQAArpk0kUAEm2unpVpIAAG+vbbeQAAade/dbYKACbdO3dVgACJb+D6nZlMAAYZvD4+3TEgAHJz1h4+u+QAA8rfl555tMgABlXJ0uGla0i0WgSAAAD8B+j/ADwAJK9Oc2kAAF66JmyFACTt317ZmwAAXTp26pLIAAuurr36SQABb29OvSgAApx9HX4/bvAAA87jufN01AAUnlefpt5t6QhQA/P9PBrjnaAAAAMq5el5tIrTKxaBIAB+A/R/ngALp0ZkyAALdpN2QABK+nre8zIABGt9e+muZKAATvp3b66gAAtvp6GtaIAAK+P39/HtaAAB4/k7dfPVkAAqng+XO+O+ud65XllBz3n4uvB04AAAAAAZacm3PuwaZWiwJB+A/R/ngAjdNc5AAE10SaIAANrr0rdJAABO+vZbeQAAadfR3VZQAQu/Tr3VIABGdX8P0+zKYAA583i8nbpykAA4s8ubz+bbKjpnO8TtpOmuXm68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";
            Utilidades util = new Utilidades();
            String nombreImagen = util.decodeimage(producto.getImagen()); // imageBase64
            
            int resultado = new ProductoService().registrarProducto(producto.getDescripcion(), producto.getDetalle(), producto.getStock(), producto.getPrecio(), nombreImagen);
            
            return resultado;
        } catch (Exception e) {
            return -1;
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("actualizarProducto")
    public int actualizarProducto(String json){
        try {
            Gson gson = new Gson();
            
            Producto producto = (Producto) gson.fromJson(json, Producto.class);
            
            int resultado = new ProductoService().actualizarProducto(producto.getCodigo(), producto.getDescripcion(), producto.getDetalle(), producto.getStock(), producto.getPrecio(), producto.getImagen());
            
            return resultado;
        } catch (Exception e) {
            return -1;
        }
    }

    @GET
    @Path("validarProducto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validarProducto(@QueryParam("codigo") String codigo){
        try {
            List<Producto> producto = new ProductoService().validarProducto(codigo);
            
            String json = new Gson().toJson(producto);
            
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.SEE_OTHER).entity("Error: " + e.toString()).build();
        }
    }
 
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("eliminarProducto")
    public int eliminarProducto(String json){
        try {
            Gson gson = new Gson();
            
            Producto producto = (Producto) gson.fromJson(json, Producto.class);
            
            int resultado = new ProductoService().eliminarProducto(producto.getCodigo());
            
            return resultado;
        } catch (Exception e) {
            return -1;
        }
    }

}

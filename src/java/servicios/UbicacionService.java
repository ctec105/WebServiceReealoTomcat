/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import daos.UbicacionDAO;
import entidades.Ubicacion;
import java.util.List;

/**
 *
 * @author XxkokoxXT
 */
public class UbicacionService {
    
    private UbicacionDAO ubicacionDAO;
    
    public UbicacionService(){
        ubicacionDAO = new UbicacionDAO();
    }
    
    public List<Ubicacion> listarUbicaciones(){
        return ubicacionDAO.listarUbicaciones();
    }
    
}

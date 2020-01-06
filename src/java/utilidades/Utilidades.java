/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import daos.ProductoDAO;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author XxkokoxXT
 */
public class Utilidades {

    public String decodeimage(String img){
        //String base64String = "data:image/jpeg;base64,iVBORw0KGgoAAAANSUhEUgAAAHkAAAB5C...";
        String base64String = "data:image/jpg;base64," + img;
        String[] strings = base64String.split(",");
        String extension;
        switch (strings[0]) { // comprobar la extensión de la imagen
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default: // debería escribir más casos para más tipos de imágenes
                extension = "jpg";
                break;
        }
        // convertimos la cadena base64 a datos binarios
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
       
        // generamos el codigo del producto
        ProductoDAO p = new ProductoDAO();
        String codigoProd = p.obtenerCodProd();
       
        String nombreImagen = "image" + codigoProd + "." + extension;
        String path = "/opt/glassfish/glassfish/domains/domain1/applications/ROOT-160/imagenes/" + nombreImagen; //String path = "E:\\test_image." + extension;
        //String path = "web\\imagenes\\" + nombreImagen; //String path = "E:\\test_image." + extension;
        File file = new File(path);
        
        System.out.println("path: " + file.getAbsolutePath());
       
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return nombreImagen;
    }

}

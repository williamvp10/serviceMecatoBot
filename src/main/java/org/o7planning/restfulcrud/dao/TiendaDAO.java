/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.o7planning.restfulcrud.dao;


 /**
 *
 * @author Carlos Alberto
 */
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.o7planning.restfulcrud.model.Tienda;
import org.o7planning.restfulcrud.model.Product;
 
public class TiendaDAO {

    private Connection conexion;

    public TiendaDAO() {
        try {
            this.conexion = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(TiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean crearTienda(Tienda tienda) {
        boolean resultado = false;
        try {
            //1.Establecer la consulta
            String consulta = "INSERT INTO tienda VALUES(?,?,?,?,?)";
            //2. Crear el PreparedStament
            PreparedStatement statement
                    = this.conexion.prepareStatement(consulta);
            //-----------------------------------
            statement.setString(1, tienda.getId());
            statement.setString(2, tienda.getNombre());
            statement.setString(3, tienda.getDireccion());
            statement.setString(4, tienda.getTelefono());
            statement.setString(5, tienda.getUrl());

            //--------------------------------------
            //3. Hacer la ejecucion
            resultado = statement.execute();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultado;
    }

    public List<Tienda> leerTienda() {
        //1.Consulta
        List<Tienda> respuesta = new ArrayList<Tienda>();

        String consulta = "SELECT * FROM tienda";
        try {
            //----------------------------
            //Statement
            Statement statement
                    = this.conexion.createStatement();
            //Ejecucion
            ResultSet resultado
                    = statement.executeQuery(consulta);
            //----------------------------
            //Recorrido sobre el resultado

            while (resultado.next()) {

                Tienda tienda = new Tienda();

                tienda.setId(resultado.getString(1));
                tienda.setNombre(resultado.getString(2));
                tienda.setDireccion(resultado.getString(3));
                tienda.setTelefono(resultado.getString(4));
                tienda.setUrl(resultado.getString(5));
                respuesta.add(tienda);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return respuesta;
    }

    public Tienda leerTiendaPorID(String id) {
        //1.Consulta
        List<Tienda> respuesta = new ArrayList<Tienda>();
        Tienda tienda = new Tienda();

        String consulta = "SELECT * FROM tienda where id='" + id + "'";
        try {
            //----------------------------
            //Statement
            Statement statement
                    = this.conexion.createStatement();
            //Ejecucion
            ResultSet resultado
                    = statement.executeQuery(consulta);
            //----------------------------
            //Recorrido sobre el resultado

            while (resultado.next()) {

                tienda.setId(resultado.getString(1));
                tienda.setNombre(resultado.getString(2));
                tienda.setDireccion(resultado.getString(3));
                tienda.setTelefono(resultado.getString(4));
                tienda.setUrl(resultado.getString(5));
                respuesta.add(tienda);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tienda;
    }
    
    
    public List<String> idTiendasSugeridas(List<Product> productos){
        
        List<String> ids = new ArrayList<String>();

        for (int i = 0; i < productos.size(); i++) {
            if (i == 0) {
                String cadena = productos.get(i).getIdTienda().getId();
                ids.add(cadena);

            } else {
                String cadena = productos.get(i).getIdTienda().getId();

                for (int j = 0; j < ids.size(); j++) {
                    if (cadena.equals(ids.get(j))) {
                        j = ids.size();
                    } else if (j == ids.size() - 1) {
                        ids.add(cadena);
                    }
                }

            }
        }
        
        
        return ids;
    }

    public List<Tienda> sugerenciaTienda(List<Product> productos) {
        List<Tienda> sugerencia = new ArrayList<Tienda>();
        List<String> ids = new ArrayList<String>();
        
        ids=idTiendasSugeridas(productos);
        
        for (int i = 0; i < ids.size(); i++) {
            Tienda tienda = new Tienda();
            
            tienda=leerTiendaPorID(ids.get(i));
            sugerencia.add(tienda);
            
        }
        

        return sugerencia;
    }

}

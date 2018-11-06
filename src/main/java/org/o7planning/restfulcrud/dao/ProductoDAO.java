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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.o7planning.restfulcrud.model.Product;
import org.o7planning.restfulcrud.model.Tienda;

public class ProductoDAO {

    private Connection conexion;

    public ProductoDAO() {
        try {
            this.conexion = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean crearProducto(Product producto) {
        boolean resultado = false;
        try {
            //1.Establecer la consulta
            String consulta = "INSERT INTO Producto VALUES(?,?,?,?,?,?)";
            //2. Crear el PreparedStament
            PreparedStatement statement
                    = this.conexion.prepareStatement(consulta);
            //-----------------------------------
            statement.setString(1, producto.getId());
            statement.setString(2, producto.getNombre());
            statement.setString(3, producto.getTipo());
            statement.setString(4, producto.getIdTienda().getId());
            statement.setString(5, producto.getIngredientes());
            statement.setInt(6, producto.getPrecio());

            //--------------------------------------
            //3. Hacer la ejecucion
            resultado = statement.execute();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultado;
    }

    public boolean modificarProducto(Product producto) {
        boolean result = false;
        String query = "update Producto set id = ?, Nombre = ?, Tipo = ?, Ingredientes = ?, idTienda = ?, Precio = ? where id = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = this.conexion.prepareStatement(query);
            preparedStmt.setString(1, producto.getId());
            preparedStmt.setString(2, producto.getNombre());
            preparedStmt.setString(3, producto.getTipo());
            preparedStmt.setString(4, producto.getIngredientes());
            preparedStmt.setString(5, producto.getIdTienda().getId());
            preparedStmt.setInt(6, producto.getPrecio());

            if (preparedStmt.executeUpdate() > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Product> leerProducto() {
        //1.Consulta
        List<Product> respuesta = new ArrayList<Product>();
        String consulta = "SELECT * FROM Producto";
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
                Product prod = new Product();
                Tienda tienda = new Tienda();

                prod.setId(resultado.getString(1));
                prod.setNombre(resultado.getString(2));
                prod.setTipo(resultado.getString(3));
                prod.setIngredientes(resultado.getString(4));
                tienda.setId(resultado.getString(5));
                prod.setIdTienda(tienda);
                prod.setPrecio(resultado.getInt(6));
                respuesta.add(prod);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return respuesta;
    }

    public List<Product> leerProductoporTipo(String tipo) {
        //1.Consulta
        List<Product> respuesta = new ArrayList<Product>();
        String consulta = "SELECT * FROM Producto where tipo = '" + tipo + "'";
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
                Product prod = new Product();
                Tienda tienda = new Tienda();

                prod.setId(resultado.getString(1));
                prod.setNombre(resultado.getString(2));
                prod.setTipo(resultado.getString(3));
                prod.setIngredientes(resultado.getString(4));
                tienda.setId(resultado.getString(5));
                prod.setIdTienda(tienda);
                prod.setPrecio(resultado.getInt(6));
                respuesta.add(prod);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return respuesta;
    }

    public List<Product> obtenerTipos(List<Product> productos) {
        List<String> tipos = new ArrayList<String>();
        List<Product> prodTipos = new ArrayList<Product>();

        int k = 0;
        for (int i = 0; i < productos.size(); i++) {
            if (i == 0) {

                tipos.add(productos.get(i).getTipo());

            } else {
                if (!tipos.get(k).equals(productos.get(i).getTipo())) {

                    tipos.add(productos.get(i).getTipo());
                    k = k + 1;
                }

            }
        }

        for (int i = 0; i < tipos.size(); i++) {
            Product prod = new Product();
            String tipo = productos.get(i).getTipo();
            prod.setTipo(tipo);
            prodTipos.add(prod);
        }

        return prodTipos;
    }

    public List<String> splitArray(String[] ing) {
        List<String> ingredientes = new ArrayList<String>();

        for (int i = 0; i < ing.length; i++) {
            ingredientes.add(ing[i]);
        }

        return ingredientes;
    }

    public List<Product> sugerenciasProductos(String tipo, List<String> ingredientes) {
        List<Product> sugerencia = new ArrayList<Product>();
        List<Product> productos = new ArrayList<Product>();

        productos = leerProductoporTipo(tipo);

        for (int i = 0; i < productos.size(); i++) {

            String cadena = productos.get(i).getIngredientes();
            String[] parts = cadena.split(",");
            int c = 0;
            for (int j = 0; j < ingredientes.size(); j++) {
                for (int k = 0; k < parts.length; k++) {
                    if (parts[k].equals(ingredientes.get(j))) {
                        c = c + 1;
                    }
                }
            }

            if (c == ingredientes.size() - 3) {
                sugerencia.add(productos.get(i));
            }
        }

        return sugerencia;
    }

    public List<String> obtenerIngredientes(List<Product> productos) {
        List<String> ingredientes = new ArrayList<String>();

        for (int i = 0; i < productos.size(); i++) {
            if (i == 0) {
                String cadena = productos.get(i).getIngredientes();
                String[] parts = cadena.split(",");
                for (int j = 0; j < parts.length; j++) {
                    ingredientes.add(parts[j]);
                    System.out.println("Ingreso primeros ing");
                }
            } else {
                String cadena = productos.get(i).getIngredientes();
                String[] parts = cadena.split(",");
                List<String> aux = new ArrayList();
                aux = ingredientes;
                for (int j = 0; j < parts.length; j++) {
                    for (int k = 0; k < aux.size(); k++) {
                        //System.out.println("Entro ambos for");
                        if (parts[j].equals(ingredientes.get(k))) {
                            k = aux.size();
                        } else if (k == aux.size() - 1) {
                            ingredientes.add(parts[j]);
                        }
                    }

                }
            }
        }

        return ingredientes;
    }

    public boolean borrarProducto(Product producto) {
        boolean result = false;
        String query = "delete from Producto where id = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = this.conexion.prepareStatement(query);
            preparedStmt.setString(1, producto.getId());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}

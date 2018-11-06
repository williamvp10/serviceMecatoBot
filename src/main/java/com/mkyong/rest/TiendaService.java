/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mkyong.rest;

 
import java.util.ArrayList;
import java.util.List;
 
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 

import org.o7planning.restfulcrud.dao.TiendaDAO;
import org.o7planning.restfulcrud.dao.ProductoDAO;

import org.o7planning.restfulcrud.model.Tienda;
import org.o7planning.restfulcrud.model.Product;
 
@Path("/tiendas")
public class TiendaService {
 
    // URI:
    // /contextPath/servletPath/employees
//    @GET
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    public List<Tienda> getProducts_JSON() {
//        List<Tienda> listOfCountries = TiendaDAO.getAllTiendas();
//        return listOfCountries;
//    }
 
    // URI:
    // /contextPath/servletPath/employees/{empNo}
    @GET
    @Path("/{tipo}/{ing}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Tienda> getTiendas(@PathParam("tipo") String tipo, @PathParam("ing") String ing) {
        ProductoDAO prod = new ProductoDAO();
        TiendaDAO tienda = new TiendaDAO();
        List<String> ingredientes = new ArrayList();
        List<Product> productosSugeridos = new ArrayList();
        List<Tienda> TiendasSugeridas = new ArrayList();
        String [] split = ing.split(",");
        ingredientes = prod.splitArray(split);
        productosSugeridos=prod.sugerenciasProductos(tipo, ingredientes);
        
        TiendasSugeridas = tienda.sugerenciaTienda(productosSugeridos);
        
        
        return TiendasSugeridas;
    }
 
//    // URI:
//    // /contextPath/servletPath/employees
//    @POST
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    public Tienda addProduct(Tienda ti) {
//        return TiendaDAO.addTienda(ti);
//    }
// 
//    // URI:
//    // /contextPath/servletPath/employees
//    @PUT
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    public Tienda updateProduct(Tienda ti) {
//        return TiendaDAO.updateTienda(ti);
//    }
// 
//    @DELETE
//    @Path("/{id}")
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    public void deleteProduct(@PathParam("id") String ti) {
//        TiendaDAO.deleteTienda(ti);
//    }
 
}
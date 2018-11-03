/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mkyong.rest;

 
import VO.Producto;
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
 

import org.o7planning.restfulcrud.dao.ProductDAO;

import org.o7planning.restfulcrud.model.Product;
 
@Path("/products")
public class ProductService {
    ProductDAO pDAO =new ProductDAO();
 
    // URI:
    // /contextPath/servletPath/employees
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ArrayList<Producto> leerProductos_JSON() {
        ArrayList<Producto> listOfCountries = pDAO.leerProductos();
        return listOfCountries;
    }
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ArrayList<Producto>  obtenerTipos_JSON() {
        ArrayList<Producto> listOftypes = pDAO.leerProductos();
        return listOftypes;
    }
 
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ArrayList<Producto> obtenerIngredientes_JSON() {
        ArrayList<Producto> listOfIngredientes = pDAO.leerProductos();
        return listOfIngredientes;
    }
 
 
    // URI:
    // /contextPath/servletPath/employees/{empNo}
    //@GET
    //@Path("/{id}")
    //@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    //public Product getProduct(@PathParam("id") String id) {
    //    return ProductDAO.getProduct(id);
    //}
 
    // URI:
    // /contextPath/servletPath/employees
    //@POST
    //@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    //public Product addProduct(Product pr) {
    //    return ProductDAO.addProduct(pr);
    //}
 
    // URI:
    // /contextPath/servletPath/employees
    //@PUT
    //@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    //public Product updateProduct(Product pr) {
    //    return ProductDAO.updateProduct(pr);
    //}
 
    //@DELETE
    //@Path("/{id}")
    //@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    //public void deleteProduct(@PathParam("id") String pr) {
    //    ProductDAO.deleteProduct(pr);
    //}
 
}
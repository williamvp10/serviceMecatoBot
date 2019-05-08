package com.mkyong.rest;
 

import java.util.List;
 
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
import org.o7planning.restfulcrud.dao.EmployeeDAO;
import org.o7planning.restfulcrud.dao.ProductoDAO;
import org.o7planning.restfulcrud.model.Employee;
import org.o7planning.restfulcrud.model.Ingredient;
import org.o7planning.restfulcrud.model.Product;
 
@Path("/products")
public class ProductService {
 
    // URI:
    // /contextPath/servletPath/employees
//    @GET
//    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML })
//    public List<String> getTipos_JSON() {
//        ProductoDAO prod = new ProductoDAO();
//        List<Product> listofProd = prod.leerProducto();
//        List<String> tipos = prod.obtenerTipos(listofProd);
//        return tipos;
//    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML })
    public List<Product> getTipos_JSON() {
        ProductoDAO prod = new ProductoDAO();
        List<Product> listofProd = prod.leerProducto();
        for (int i = 0; i < listofProd.size(); i++) {
            System.out.println(listofProd.get(i).getTipo());
        }
        List<Product> tipos = prod.obtenerTipos(listofProd);
        return tipos;
    }
 
    // URI:
    // /contextPath/servletPath/employees/{empNo}
    @GET
    @Path("/{tipo}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML })
    public List<Ingredient> getIngredientes(@PathParam("tipo") String tipo) {
        ProductoDAO prod = new ProductoDAO();
        List<Product> listofProd ;
        List<Ingredient> ingredientes ;
        listofProd = prod.leerProductoporTipo(tipo);
        ingredientes = prod.obtenerIngredientes(listofProd);
        return ingredientes;
        
    }
 
//    // URI:
//    // /contextPath/servletPath/employees
//    @POST
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    public Employee addEmployee(Employee emp) {
//        return EmployeeDAO.addEmployee(emp);
//    }
// 
//    // URI:
//    // /contextPath/servletPath/employees
//    @PUT
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    public Employee updateEmployee(Employee emp) {
//        return EmployeeDAO.updateEmployee(emp);
//    }
// 
//    @DELETE
//    @Path("/{empNo}")
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    public void deleteEmployee(@PathParam("empNo") String empNo) {
//        EmployeeDAO.deleteEmployee(empNo);
//    }
 
}
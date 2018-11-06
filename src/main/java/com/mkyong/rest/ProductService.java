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
 
import org.o7planning.restfulcrud.dao.EmployeeDAO;
import org.o7planning.restfulcrud.dao.ProductoDAO;
import org.o7planning.restfulcrud.model.Employee;
import org.o7planning.restfulcrud.model.Product;
 
@Path("/products")
public class ProductService {
 
    // URI:
    // /contextPath/servletPath/employees
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ArrayList<String> getTipos_JSON() {
        ProductoDAO prod = new ProductoDAO();
        ArrayList<Product> listofProd = prod.leerProducto();
        ArrayList<String> tipos = prod.obtenerTipos(listofProd);
        return tipos;
    }
 
    // URI:
    // /contextPath/servletPath/employees/{empNo}
    @GET
    @Path("/{tipo}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ArrayList<String> getIngredientes(@PathParam("tipo") String tipo) {
        ProductoDAO prod = new ProductoDAO();
        ArrayList<Product> listofProd = prod.leerProductoporTipo(tipo);
        ArrayList<String> ingredientes = prod.obtenerIngredientes(listofProd);
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
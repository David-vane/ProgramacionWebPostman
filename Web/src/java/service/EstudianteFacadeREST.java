/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Modelo.Estudiante;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Robin
 */
@Stateless
@Path("modelo.estudiante")
public class EstudianteFacadeREST extends AbstractFacade<Estudiante> {

    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

    public EstudianteFacadeREST() {
        super(Estudiante.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Estudiante entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Estudiante entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Estudiante find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Estudiante> findAll() {
        return super.findAll();
    }
    
    @POST
    @Path("Hola")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String Mensage() {
        return "Hola mundo";
    }
    
    @GET
    @Path("holaNombre")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String Holanombre(@QueryParam("n") String nom) {
        return "Bienvenido: "+nom;
    }
    
    @GET
    @Path("suma")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String Suma(@QueryParam("sum1") int num1, @QueryParam("sum2")int num2){
        int res=num1+num2;
        return "el resultado es: "+res;
    } 
    
    @POST
    @Path("Multiplicacion")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String Multiplicacion(@FormParam("num1")int num1,@FormParam("num2")int num2) {
        int res=num1*num2;
        return "El resultado es: "+res;
    }
    
    @GET
    @Path("numMayor")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String numMayor(@QueryParam("num1") int num1, @QueryParam("num2")int num2){
        if (num1>num2) {
            return "el numero mayor es: "+num1;
        } else {
            return "el numero mayor es: "+ num2;
        }
            
        }
    
    @POST
    @Path("crear")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String crear (@FormParam("cedula")String cedula,@FormParam("nombres")String nombres,@FormParam("apellidos")String apellidos,@FormParam("carrera")String carrera,
            @FormParam("modulo")String modulo, @FormParam("telefono")String Telefono) {
        Estudiante ob =new Estudiante(cedula, nombres, apellidos, carrera, modulo, Telefono);
        if(cedula.length()==10){
        super.create(ob);
        return "el objeto se inserto con exito";
        }else{
            return "ingrese correctamente su cedula";
        }
        
    }
    
    @POST
    @Path("consultar")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Estudiante> leerCedula(@FormParam ("cedula") String cedula){
           TypedQuery consulta = getEntityManager().createQuery("SELECT e FROM Estudiante e WHERE e.cedula = :cedula", Estudiante.class);
    consulta.setParameter("cedula", cedula);
    return consulta.getResultList();
    }
    
    @POST
    @Path("editar")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String editar (@FormParam ("cedula") String cedula, @FormParam ("nombres") String nombres, @FormParam ("apellidos") String apellidos, @FormParam ("carrera") String carrera,
    @FormParam ("modulo") String modulo, @FormParam ("telefono") String telefono) {
        Estudiante jc = super.find(cedula);
        jc.setCedula(cedula);
        jc.setNombres(nombres);
        jc.setApellidos(apellidos);
        jc.setCarrera(carrera);
        jc.setModulo(modulo);
        jc.setTelefono(telefono);
        super.edit(jc);
           return ("se actualizo con exito");
    }
    
    @POST
    @Path("eliminar")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String eliminar(@FormParam ("cedula")String cedula){
        Estudiante jc = super.find(cedula);
        super.remove(jc);
        return "se elimino con exito";
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Estudiante> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("serie")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String serie (@QueryParam("n") int n){
        int a,b;
            int c=0;
        a=-1;
        b=1;
        for(int i=0; 1 <= n;i++){
            c=a+b;
            a=b;
            b=c;
        }
        return ""+c;
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

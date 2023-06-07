package org.uv.Programa09.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmpleadoController {
    
    @Autowired
    private RepositoryEmpleado repositoryEmpleado;
    
    private static List<DTOEmpleado> listemp = new ArrayList<>();
    @GetMapping("/msg")
    public String holaMundo() {
        return "Hola Mundo";
    }
    
   @GetMapping("/empleado/{id}")
    public DTOEmpleado obtenerEmpleado(@PathVariable("id") long id){
        
        DTOEmpleado emp = new DTOEmpleado();
        
        ArrayList<Empleado> lista=(ArrayList<Empleado>) repositoryEmpleado.findAll();
        for(Empleado e:lista){
            if (e.getClave()==id){
                emp.setClave(e.getClave());
                emp.setNombre(e.getNombre());
                emp.setDireccion(e.getDireccion());
                emp.setTelefono(e.getTelefono());
            }
        }
        return emp;
    }
    
     @GetMapping("/empleado")
     public List<DTOEmpleado> obtenerTodosLosEmpleados(){
        ArrayList<Empleado> lista=(ArrayList<Empleado>) repositoryEmpleado.findAll();
        ArrayList<DTOEmpleado> list=new ArrayList<>();
        DTOEmpleado empDTO=new DTOEmpleado();
        for(Empleado e:lista){
            empDTO.setClave(e.getClave());
            empDTO.setNombre(e.getNombre());
            empDTO.setDireccion(e.getDireccion());
            empDTO.setTelefono(e.getTelefono());
            list.add(empDTO);
        }
        return list;
     }
     
    @PostMapping("/empleado")
    public DTOEmpleado saveEmpleado(@Validated @RequestBody DTOEmpleado emp) {
        Empleado pojo = new Empleado();
        pojo.setNombre(emp.getNombre());
        pojo.setDireccion(emp.getDireccion());
        pojo.setTelefono(emp.getTelefono());
        
        
        
        DTOEmpleado emp1=null;
        emp1=emp;
        
        return emp1;
    }
    
    @PutMapping("/empleado/{id}")
    public DTOEmpleado updateEmpleado(@PathVariable ("id") long id, @RequestBody DTOEmpleado emp) {
        //repositoryEmpleado.s
        DTOEmpleado emp2=new DTOEmpleado();
        for (DTOEmpleado emp1:listemp){
            if (emp1.getClave()==id){
                emp1.setClave(emp.getClave());
                emp1.setNombre(emp.getNombre());
                emp1.setDireccion(emp.getDireccion());
                emp1.setTelefono(emp.getTelefono());
                emp2=emp1;
            }
        }
       return emp2;
    }
    
    @DeleteMapping("/empleados/{id}")
    public boolean deleteEmpleado(@PathVariable ("id") long id){
        boolean bandera = false;
        Iterator <DTOEmpleado> emp=listemp.iterator();
        while (emp.hasNext()){
            if(emp.next().getClave()==id){
                emp.remove();
                bandera=true;
            }
        }
       return bandera;
    }
}
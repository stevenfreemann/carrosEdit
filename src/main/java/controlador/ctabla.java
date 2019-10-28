/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import modelo.carro;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;



/**
 *
 * @author Wilson Rodriguez, Jhony Rojas
 * @version 1.0
 */

@ManagedBean
@ViewScoped
/**
 * Clase que se encarga de controlar la vista y validar campos
 */
public class ctabla implements Serializable{
    /**
     * Variables Con validaciones de tamaño y año
     */
    
    private String nombre;
    private String marca;
    private Integer modelo;
    private static List<carro> lista = new ArrayList();
    private static List<carro> filteredCars = new ArrayList();
    private static List<String> marcas = new ArrayList();


    /**
     * metodo que se encarga de llenar la lista de carros y la lista de marcas 
     */
   @PostConstruct
    public void llenar(){
        if(lista.isEmpty()){
             carro c1 = new carro();
        c1.setMarca("Mazda");
        c1.setNombre("aveo");
        c1.setModelo(1995);
        c1.setId(getRandomId());
        lista.add(c1);
        marcas.add(c1.getMarca());
        carro c2 = new carro();
        c2.setMarca("chevrolet");
        c2.setNombre("ranger");
        c2.setModelo(2000);
        c2.setId(getRandomId());
        lista.add(c2);
        marcas.add(c2.getMarca());
        
        carro c3 = new carro();
        c3.setMarca("honda");
        c3.setNombre("luxx");
        c3.setModelo(2010);
        c3.setId(getRandomId());
        lista.add(c3);
        marcas.add(c3.getMarca());
        
        carro c4 = new carro();
        c4.setMarca("kia");
        c4.setNombre("usin");
        c4.setModelo(1980);
        c4.setId(getRandomId());
        lista.add(c4);
        marcas.add(c4.getMarca());
        
        }
       
    
    }
    public String eliminar(){
        for (carro ca : lista) {
            if(ca.isSelect()){
                filteredCars.add(ca);
            }
        }
        if (!filteredCars.isEmpty()) {
            lista.removeAll(filteredCars);
             FacesContext.getCurrentInstance().addMessage(null, new  FacesMessage("Carro/s Eliminado/s"));
           }
    
        return "tabla";
    }

     private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
    public void onRowEdit(RowEditEvent event ) {
        carro c = (carro) event.getObject();
        c.setNombre(nombre);
        FacesMessage msg = new FacesMessage("Carro editado", ((carro) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Editar cancelado", ((carro) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

   /**
    * Getters y Setters de las variables 
    * @return 
    */
    public List<carro> getLista() {
        return lista;
    }

    public void setLista(List<carro> lista) {
        this.lista = lista;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    } 

    public List<carro> getFilteredCars() {
        return filteredCars;
    }

    public void setFilteredCars(List<carro> filteredCars) {
        this.filteredCars = filteredCars;
    }

    public List<String> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<String> marcas) {
        this.marcas = marcas;
    }
    
    
}

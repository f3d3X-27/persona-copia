package com.example.persona.services;

import com.example.persona.entities.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.io.Serializable;
import java.util.List;

public interface BaseService<E extends Base, ID extends Serializable> {// El Id se hace generico por que todas estas clases lo vamos a poder reutilizar en el proyecto que nosotros queramos

    public List<E> findAll() throws  Exception; // Va a traernos una lista de las personas o entidades que se encuentran en nuestra base de datos
    public Page<E> findAll(Pageable pageable) throws Exception;// Nos permite devolver los datos paginados (Una sublista con otros datos)
    public E findById(ID id) throws Exception;// Va a traernos una persona o entidad, de acuerdo a su numero de id
    public E save (E entity) throws  Exception;// Va a creer una nueva entidad, y para eso le vamos a enviar una entidad al repositorio
    public E update (ID id, E entity) throws  Exception; // Va a tener los parametros id y entidad, que sera la entidad actualizada
    public boolean delete (ID id) throws  Exception;//Se encarga a eliminar un registro de nuestra base de datos



}
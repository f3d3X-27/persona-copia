package com.example.persona.services;

import com.example.persona.entities.Base;
import com.example.persona.entities.Persona;
import com.example.persona.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {

    protected BaseRepository<E, ID> baseRepository; // Esto lo hacemos protegido, para que lo puedan utilizar las clases que heredan de BaseService

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository){
        this.baseRepository = baseRepository;
    }
    @Override
    @Transactional // Esto significa que los metodos haran transaciones con la base de datos
    public List<E> findAll() throws Exception {
        try {
            List<E> entities =baseRepository.findAll();// Va a obtener de la bse de datos todas las personas que tengamos registradas
            return  entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<E> findAll(Pageable pageable) throws Exception{

        try {
            Page<E> entities =baseRepository.findAll(pageable);
            return  entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findById(ID id) throws Exception {
        try {
            // lo hacemos por que en la base de datos no sabemos si se va a encontrar una entidad o un registro que tenga esa id como clave primaria
            Optional<E> entityOptional = baseRepository.findById(id);
            return entityOptional.get(); // Va a obtener una entidad si es que la encuentra y de otra forma lanzara una excepcion
        }catch (Exception e){

            throw  new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public E save(E entity) throws Exception { //Utiliza el metodo save del repositorio
        try {
            entity = baseRepository.save(entity);
            return entity;
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            E entityUpdate = entityOptional.get(); // si no obtenemos una persona se lanza un rollback de la base de datos
            entityUpdate = baseRepository.save(entity);
            return entityUpdate;
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }

    }


    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        try {
            if (baseRepository.existsById(id)) {// Es para saber si existe alguna persona con el id que indicamos
                baseRepository.deleteById(id);
                return true;
            }else{
                throw  new Exception();
            }

        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }

    }
}

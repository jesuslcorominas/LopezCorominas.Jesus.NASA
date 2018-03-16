package com.jesuslcorominas.nasa.data.database.objectbox.dao.impl;

import com.jesuslcorominas.nasa.data.database.objectbox.dao.ObjectBoxDao;

import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.Property;
import io.objectbox.query.Query;

/**
 * @author Jesús López Corominas
 */
public abstract class AbstractObjectBoxDao<VO, ENTITY> implements ObjectBoxDao<VO, ENTITY> {

    protected final Box<ENTITY> box;

    private Class<VO> voClass;
    private Class<ENTITY> entityClass;

    private Type entityListType;
    private Type voListType;


    private ModelMapper mapper;

    AbstractObjectBoxDao(Box<ENTITY> box, Class<VO> voClass, Class<ENTITY> entityClass, Type voListType, Type entityListType, ModelMapper mapper) {
        this.box = box;

        this.voClass = voClass;
        this.entityClass = entityClass;

        this.voListType = voListType;
        this.entityListType = entityListType;

        this.mapper = mapper;
    }

    @Override
    public List<VO> getAll() {

        return find(null);
    }

    @Override
    public List<VO> find(Property columnProperty, String filterValue) {
        return find(box.query().equal(columnProperty, filterValue).build());
    }

    @Override
    public VO findById(long id) {
        return mapper.map(box.get(id), this.voClass);
    }

    @Override
    public List<VO> find(Query<ENTITY> query) {
        List<ENTITY> entities = query == null ? box.getAll() : query.find();

        return mapper.map(entities, voListType);
    }

    @Override
    public VO save(VO vo) {
        ENTITY entity = mapper.map(vo, this.entityClass);
        box.put(entity);

        return mapper.map(entity, this.voClass);
    }

    @Override
    public void save(final List<VO> vos) {
        List<ENTITY> entities = mapper.map(vos, this.entityListType);

        box.put(entities);
    }

    @Override
    public void delete(List<VO> vos) {
        List<ENTITY> entities = mapper.map(vos, this.entityListType);

        box.remove(entities);
    }

    @Override
    public void delete(Property columnProperty, String filterValue) {
        delete(box.query().equal(columnProperty, filterValue).build());
    }

    @Override
    public void delete(Query<ENTITY> query) {
        box.remove(query.find());
    }

    @Override
    public void delete(VO vo) {
        box.remove(mapper.map(vo, this.entityClass));
    }
}

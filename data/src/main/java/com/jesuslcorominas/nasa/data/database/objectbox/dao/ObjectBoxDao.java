package com.jesuslcorominas.nasa.data.database.objectbox.dao;

import com.jesuslcorominas.nasa.data.database.dao.Dao;

import java.util.List;

import io.objectbox.Property;
import io.objectbox.query.Query;

/**
 * @see <a href="http://objectbox.io/">ObjectBox</a>
 * <p>
 * @author Jesús López Corominas
 */
public interface ObjectBoxDao<VO, ENTITY> extends Dao<VO> {
    /**
     * Obtiene una lista de registros de base de datos filtrados por la columna y el valor pasados como parametros
     *
     * @param columnProperty La columna por la que filtrar
     * @param filterValue    El valor por el que filtrar
     * @return La lista de objetos persistidos
     */
    List<VO> find(Property columnProperty, String filterValue);

    /**
     * Obtiene una lista de registros de base de datos en funcion de la query y los parametros pasados
     *
     * @param query Query que quieras ejecutar
     * @return La lista de registros
     */
    List<VO> find(Query<ENTITY> query);

    /**
     * Elimina de base de datos todos los objetos filtrados por la columna y el valor pasados como parametros
     *
     * @param columnProperty La columna por la que filtrar
     * @param filterValue    El valor por el que filtrar
     */
    void delete(Property columnProperty, String filterValue);

    /**
     * Elimina de base de datos todos los objetos obtenidos en la query
     *
     * @param query La query por la que filtrar
     */
    void delete(Query<ENTITY> query);
}

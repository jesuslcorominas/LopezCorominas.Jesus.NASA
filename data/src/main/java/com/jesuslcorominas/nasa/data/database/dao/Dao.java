package com.jesuslcorominas.nasa.data.database.dao;

import java.util.List;

/**
 * Interface generica para el acceso a la base de datos. Expone metodos para obtener, almacenar y
 * eliminar registros de una tabla.
 *
 * @param <VO> El tipo de objeto VO asociado a un objeto de base de datos
 * <p>
 * @author Jesús López Corominas
 */
public interface Dao<VO> {
    /**
     * Obtiene una lista de registros de base de datos
     *
     * @return La lista de objetos persistidos
     */
    List<VO> getAll();

    /**
     * Obtiene el elemento cuyo id se pasa como parametro
     *
     * @param id El id del elemento a obtener
     * @return El elemento cuyo id se pasa como parametro
     */
    VO findById(long id);

    /**
     * Almacena un registro en base de datos
     *
     * @param vo El registro a almacenar
     * @return El objeto persistido
     */
    VO save(VO vo);

    /**
     * Almacena una lista de registros en base de datos
     *
     * @param vos Los registros a almacenar
     */
    void save(List<VO> vos);

    /**
     * Elimina un objeto de base de datos. Tiene que ser un objeto ya persistido, es decir, que tenga
     * establecido el campo Id
     *
     * @param vo El elemento a eliminar
     */
    void delete(VO vo);

    /**
     * Elimina una lista de registros de base de datos. TIenen que ser objetos ya persistidos, es decir,
     * que tengan establecido el campo id
     *
     * @param vo Los elementos a eliminar
     */
    void delete(List<VO> vo);

}
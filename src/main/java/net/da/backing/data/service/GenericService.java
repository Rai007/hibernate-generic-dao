/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.da.backing.data.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import net.da.backing.data.dao.hibernate.IGeneralDAO;
import net.da.backing.data.search.ExampleOptions;
import net.da.backing.data.search.Filter;
import net.da.backing.data.search.ISearch;
import net.da.backing.data.search.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ShadowWalker
 * @param <T>
 * @param <ID>
 */
public class GenericService<T extends Serializable, ID extends Serializable> 
        extends BaseService
        implements IGenericService<T, ID>{
    
    @Autowired
    protected IGeneralDAO generalDAO;
    
    private Class<T> getClassOfT(){
        final ParameterizedType type = (ParameterizedType) this.getClass()
                    .getGenericSuperclass();
        Class<T> clazzOfT = (Class<T>) type.getActualTypeArguments()[0];
        return clazzOfT;
    }
    
    @Override
    public T find(Serializable id) {
        debug("Find entity type[{}] with id[{}].", getClassOfT(), id );
        return generalDAO.find(getClassOfT(), id);
    }

    @Override
    public T[] find(Serializable... ids) {
        debug("Find entities type[{}] with ids[{}].", getClassOfT(), Arrays.toString(ids) );
        return generalDAO.find( getClassOfT(), ids);
    }

    @Override
    public T getReference(Serializable id) {
        debug("Find entity reference type[{}] with id[{}].", getClassOfT(), id);
        return generalDAO.getReference(getClassOfT(), id);
    }

    @Override
    public T[] getReferences(Serializable... ids) {
        debug("Find entity references type[{}] with ids[{}].", getClassOfT(), Arrays.toString(ids) );
        return generalDAO.getReferences(getClassOfT(), ids);
    }

    @Override
    public boolean save(T entity) {
        debug("Save entity type[{}].", getClassOfT());
        return generalDAO.save(entity);
    }

    @Override
    public boolean[] save(T... entities) {
        debug("Save entities type[{}].", getClassOfT());
        return generalDAO.save(entities);
    }

    @Override
    public boolean remove(T entity) {
        debug("Remove entity type[{}].", getClassOfT());
        return generalDAO.remove(entity);
    }

    @Override
    public void remove(T... entities) {
        debug("Remove entities type[{}]", getClassOfT());
        generalDAO.remove(entities);
    }

    @Override
    public boolean removeById(Serializable id) {
        debug("Remove entity type[{}] with id[{}]", getClassOfT(), id  );
        return generalDAO.removeById(getClassOfT(), id);
    }

    @Override
    public void removeByIds(Serializable... ids) {
        debug("Remove entity type[{}] with ids[{}].", getClassOfT(), Arrays.toString(ids) );
        generalDAO.removeByIds(getClassOfT(), ids);
    }

    @Override
    public List findAll() {
        debug("Find all entity type[{}]", getClassOfT());
        return generalDAO.findAll(getClassOfT());
    }

    @Override
    public List search(ISearch search) {
        debug("Search type[{}] with search parameter.", getClassOfT());
        return generalDAO.search(search);
    }

    @Override
    public Object searchUnique(ISearch search) {
        debug("Search unique type[{}] result from search.", getClassOfT());
        return generalDAO.searchUnique(search);
    }

    @Override
    public int count(ISearch search) {
        debug("Count type[{}] of search.", getClassOfT());
        return generalDAO.count(search);
    }

    @Override
    public SearchResult searchAndCount(ISearch search) {
        debug("Search and count type[{}].", getClassOfT());
        return generalDAO.searchAndCount(search);
    }

    @Override
    public boolean isAttached(T entity) {
        debug("Check isAttached of entity type[{}].", getClassOfT());
        return generalDAO.isAttached(entity);
    }

    @Override
    public void refresh(T... entities) {
        debug("Refresh {} type[{}].",( entities.length == 1 ? "entity":"entities"), getClassOfT());
        generalDAO.refresh(entities);
    }

    @Override
    public void flush() {
        debug("Flush operation.");
        generalDAO.flush();
    }

    @Override
    public Filter getFilterFromExample(T example) {
        debug("Filter type[{}] from example.", getClassOfT());
        return generalDAO.getFilterFromExample(example);
    }

    @Override
    public Filter getFilterFromExample(T example, ExampleOptions options) {
        debug("Filter type[{}] from example with options.", getClassOfT());
        return generalDAO.getFilterFromExample(example, options);
    }
    
}

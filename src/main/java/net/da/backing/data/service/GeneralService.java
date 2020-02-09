/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.da.backing.data.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import net.da.backing.data.dao.hibernate.IGeneralDAO;
import net.da.backing.data.search.ExampleOptions;
import net.da.backing.data.search.Filter;
import net.da.backing.data.search.ISearch;
import net.da.backing.data.search.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ShadowWalker
 */
@Service
public class GeneralService 
        extends BaseService 
        implements IGeneralService {
    @Autowired
    protected IGeneralDAO generalDAO;
    
    @Override
    public <T> T find(Class<T> type, Serializable id) {
        debug("Find entity type[{}] with id[{}].", type, id);
        return generalDAO.find(type, id);
    }

    @Override
    public <T> T[] find(Class<T> type, Serializable... ids) {
        debug("Find entities type[{}] with ids[{}].", type, Arrays.toString(ids));
        return generalDAO.find(type, ids);
    }

    @Override
    public <T> T getReference(Class<T> type, Serializable id) {
        debug("Find entity reference type[{}] with id[{}].", type, id);
        return generalDAO.getReference(type, id);
    }

    @Override
    public <T> T[] getReferences(Class<T> type, Serializable... ids) {
        debug("Find entity references type[{}] with ids[{}].", type, Arrays.toString(ids));
        return generalDAO.getReferences(type, ids);
    }

    @Override
    public boolean saveOrUpdateIsNew(Object entity) {
        debug("Save entity type[{}].", (entity != null ? entity.getClass() : "entity is null"));
        return generalDAO.saveOrUpdateIsNew(entity);
    }

    @Override
    public boolean[] saveOrUpdateIsNew(Object... entities) {
        debug("Save entities type[{}].", (entities.length != 0 ? entities[0].getClass() : "list is empty"));
        return generalDAO.saveOrUpdateIsNew(entities);
    }
    
    @Override
    public void update(Object entity){
        debug("Save entity type[{}].", (entity != null ? entity.getClass() : "entity is null"));
        generalDAO.update(entity);
    }

    @Override
    public boolean remove(Object entity) {
        debug("Remove entity type[{}].", (entity != null ? entity.getClass() : "entity is null"));
        return generalDAO.remove(entity);
    }

    @Override
    public void remove(Object... entities) {
        debug("Remove entities type[{}].", (entities.length != 0 ? entities[0].getClass() : "list is null") );
        generalDAO.remove(entities);
    }

    @Override
    public boolean removeById(Class<?> type, Serializable id) {
        debug("Remove entity type[{}] with id[{}]", type, id);
        return generalDAO.removeById(type, id);
    }

    @Override
    public void removeByIds(Class<?> type, Serializable... ids) {
        debug("Remove entity type[{}] with ids[{}].", type, Arrays.toString(ids));
        generalDAO.removeById(type, ids);
    }

    @Override
    public <T> List<T> findAll(Class<T> type) {
        debug("Find all entity type[{}]", type );
        return generalDAO.findAll(type);
    }

    @Override
    public List search(ISearch search) {
        debug("Search type[{}] with search parameter.", search.getSearchClass());
        return generalDAO.search(search);
    }

    @Override
    public Object searchUnique(ISearch search) {
        debug("Search unique result type[{}] from search.", search.getSearchClass());
        return generalDAO.searchUnique(search);
    }

    @Override
    public int count(ISearch search) {
        debug("Count type[{}] of search.", search.getSearchClass());
        return generalDAO.count(search);
    }

    @Override
    public SearchResult searchAndCount(ISearch search) {
        debug("Search and count type[{}].", search.getSearchClass());
        return generalDAO.searchAndCount(search);
    }

    @Override
    public boolean isAttached(Object entity) {
        debug("Check isAttached of entity type[{}].", (entity !=null ? entity.getClass(): "entity is null"));
        return generalDAO.isAttached(entity);
    }

    @Override
    public void refresh(Object... entities) {
        debug("Refresh type[{}].", (entities.length != 0 ? entities[0].getClass() : "list is empty" ));
        generalDAO.refresh(entities);
    }

    @Override
    public void flush() {
        debug("Flush operation.");
        generalDAO.flush();
    }

    @Override
    public Filter getFilterFromExample(Object example) {
        debug("Filter type[{}] from example.", (example != null ? example.getClass() : "example object is null" ));
        return generalDAO.getFilterFromExample(example);
    }

    @Override
    public Filter getFilterFromExample(Object example, ExampleOptions options) {
        debug("Filter type[{}] from example with options.", (example != null ? example.getClass() : "example object is null" ));
        return generalDAO.getFilterFromExample(example, options);
    }

}

/* Copyright 2013 David Wolverton
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.da.backing.data.dao.hibernate;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import net.da.backing.data.search.ExampleOptions;
import net.da.backing.data.search.Filter;
import net.da.backing.data.search.ISearch;
import net.da.backing.data.search.SearchResult;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Implementation of <code>IGeneralDAO</code> using Hibernate.
 * The SessionFactory property is annotated for automatic resource injection.
 * 
 * @author dwolverton
 */
@Repository
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class GeneralDAO extends HibernateBaseDAO implements IGeneralDAO {
    
    @Override
    public int count(ISearch search) {
        debug("Count type[{}] of search.", search.getSearchClass());
        return _count(search);
    }

    @Override
    public <T> T find(Class<T> type, Serializable id) {
        debug("Find entity type[{}] with id[{}].", type, id);
        return (T) _get(type, id);
    }

    @Override
    public <T> T[] find(Class<T> type, Serializable... ids) {
        debug("Find entities type[{}] with ids[{}].", type, Arrays.toString(ids));
        return _get(type, ids);
    }

    @Override
    public <T> List<T> findAll(Class<T> type) {
        debug("Find all entity type[{}]", type );
        return _all(type);
    }

    @Override
    public void flush() {
        debug("Flush operation.");
        _flush();
    }

    @Override
    public <T> T getReference(Class<T> type, Serializable id) {
        debug("Find entity reference type[{}] with id[{}].", type, id);
        return _load(type, id);
    }

    @Override
    public <T> T[] getReferences(Class<T> type, Serializable... ids) {
        debug("Find entity references type[{}] with ids[{}].", type, Arrays.toString(ids));
        return _load(type, ids);
    }

    @Override
    public boolean isAttached(Object entity) {
        debug("Check isAttached of entity type[{}].", (entity !=null ? entity.getClass(): "entity is null"));
        return _sessionContains(entity);
    }

    @Override
    public void refresh(Object... entities) {
        debug("Refresh type[{}].", (entities.length != 0 ? entities[0].getClass() : "list is empty" ));
        _refresh(entities);
    }

    @Override
    public boolean remove(Object entity) {
        debug("Remove entity type[{}].", (entity != null ? entity.getClass() : "entity is null"));
        return _deleteEntity(entity);
    }

    @Override
    public void remove(Object... entities) {
        debug("Remove entities type[{}].", (entities.length != 0 ? entities[0].getClass() : "list is null") );
        _deleteEntities(entities);
    }

    @Override
    public boolean removeById(Class<?> type, Serializable id) {
        debug("Remove entity type[{}] with id[{}]", type, id);
        return _deleteById(type, id);
    }

    @Override
    public void removeByIds(Class<?> type, Serializable... ids) {
        debug("Remove entity type[{}] with ids[{}].", type, Arrays.toString(ids));
        _deleteById(type, ids);
    }

    @Override
    public boolean save(Object entity) {
        debug("Save entity type[{}].", (entity != null ? entity.getClass() : "entity is null"));
        return _saveOrUpdateIsNew(entity);
    }

    @Override
    public boolean[] save(Object... entities) {
        debug("Save entities type[{}].", (entities.length != 0 ? entities[0].getClass() : "list is empty"));
        return _saveOrUpdateIsNew(entities);
    }

    @Override
    public List search(ISearch search) {
        debug("Search type[{}] with search parameter.", search.getSearchClass());
        return _search(search);
    }

    @Override
    public SearchResult searchAndCount(ISearch search) {
        debug("Search and count type[{}].", search.getSearchClass());
        return _searchAndCount(search);
    }

    @Override
    public Object searchUnique(ISearch search) {
        debug("Search unique result type[{}] from search.", search.getSearchClass());
        return _searchUnique(search);
    }

    @Override
    public Filter getFilterFromExample(Object example) {
        debug("Filter type[{}] from example.", (example != null ? example.getClass() : "example object is null" ));
        return _getFilterFromExample(example);
    }

    @Override
    public Filter getFilterFromExample(Object example, ExampleOptions options) {
        debug("Filter type[{}] from example with options.", (example != null ? example.getClass() : "example object is null" ));
        return _getFilterFromExample(example, options);
    }
}

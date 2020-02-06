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

import net.da.backing.data.dao.DAOUtil;
import net.da.backing.data.search.ExampleOptions;
import net.da.backing.data.search.Filter;
import net.da.backing.data.search.ISearch;
import net.da.backing.data.search.Search;
import net.da.backing.data.search.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Implementation of <code>IGenericDAO</code> using Hibernate.
 * The SessionFactory property is annotated for automatic resource injection.
 * 
 * @author dwolverton
 * 
 * @param <T>
 *            The type of the domain object for which this instance is to be
 *            used.
 * @param <ID>
 *            The type of the id of the domain object for which this instance is
 *            to be used.
 */
@Repository
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class GenericDAO<T, ID extends Serializable> extends
		HibernateBaseDAO implements IGenericDAO<T, ID> {
    
        protected Class<T> persistentClass = (Class<T>) DAOUtil.getTypeArguments(GenericDAO.class, this.getClass()).get(0);

        @Override
	public int count(ISearch search) {
            debug("Count type[{}] of search.", persistentClass);
            if (search == null)
                search = new Search();
            return _count(persistentClass, search);
	}

        @Override
	public T find(ID id) {
            debug("Find entity type[{}] with id[{}].", persistentClass, id );
            return _get(persistentClass, id);
	}

        @Override
	public T[] find(ID... ids) {
            debug("Find entities type[{}] with ids[{}].", persistentClass, Arrays.toString(ids) );
            return _get(persistentClass, ids);
	}

        @Override
	public List<T> findAll() {
            debug("Find all entity type[{}]", persistentClass);
            return _all(persistentClass);
	}

        @Override
	public void flush() {
            debug("Flush operation.");
            _flush();
	}

        @Override
	public T getReference(ID id) {
            debug("Find entity reference type[{}] with id[{}].", persistentClass, id);
            return _load(persistentClass, id);
	}

        @Override
	public T[] getReferences(ID... ids) {
            debug("Find entity references type[{}] with ids[{}].", persistentClass, Arrays.toString(ids) );
            return _load(persistentClass, ids);
	}

        @Override
	public boolean isAttached(T entity) {
            debug("Check isAttached of entity type[{}].", persistentClass);
            return _sessionContains(entity);
	}

        @Override
	public void refresh(T... entities) {
            debug("Refresh {} type[{}].",( entities.length == 1 ? "entity":"entities"), persistentClass);
            _refresh(entities);
	}

        @Override
	public boolean remove(T entity) {
            debug("Remove entity type[{}].", persistentClass);
            return _deleteEntity(entity);
	}

        @Override
	public void remove(T... entities) {
            debug("Remove entities type[{}]", persistentClass);
            _deleteEntities(entities);
	}

        @Override
	public boolean removeById(ID id) {
            debug("Remove entity type[{}] with id[{}]", persistentClass, id  );
            return _deleteById(persistentClass, id);
	}

        @Override
	public void removeByIds(ID... ids) {
            debug("Remove entity type[{}] with ids[{}].", persistentClass, Arrays.toString(ids) );
            _deleteById(persistentClass, ids);
	}

        @Override
	public boolean save(T entity) {
            debug("Save entity type[{}].", persistentClass);
            return _saveOrUpdateIsNew(entity);
	}

        @Override
	public boolean[] save(T... entities) {
            debug("Save entities type[{}].", persistentClass);
            return _saveOrUpdateIsNew(entities);
	}

        @Override
	public <RT> List<RT> search(ISearch search) {
            debug("Count type[{}] of search.", persistentClass);
            if (search == null)
                return (List<RT>) findAll();
            return _search(persistentClass, search);
	}

        @Override
	public <RT> SearchResult<RT> searchAndCount(ISearch search) {
            debug("Search and count type[{}].", persistentClass);
            if (search == null) {
                SearchResult<RT> result = new SearchResult<>();
                result.setResult((List<RT>) findAll());
                result.setTotalCount(result.getResult().size());
                return result;
            }
            return _searchAndCount(persistentClass, search);
	}

        @Override
	public <RT> RT searchUnique(ISearch search) {
            debug("Search unique type[{}] result from search.", persistentClass);
            return (RT) _searchUnique(persistentClass, search);
	}

        @Override
	public Filter getFilterFromExample(T example) {
            debug("Filter type[{}] from example.", persistentClass);
            return _getFilterFromExample(example);
	}

        @Override
	public Filter getFilterFromExample(T example, ExampleOptions options) {
            debug("Filter type[{}] from example with options.", persistentClass);
            return _getFilterFromExample(example, options);
	}	
}
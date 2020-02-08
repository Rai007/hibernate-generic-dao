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
package net.da.backing.data.dao.jpa;

import java.io.Serializable;
import java.util.List;

import net.da.backing.data.dao.DAOUtil;
import net.da.backing.data.search.ExampleOptions;
import net.da.backing.data.search.Filter;
import net.da.backing.data.search.ISearch;
import net.da.backing.data.search.Search;
import net.da.backing.data.search.SearchResult;

/**
 * Implementation of <code>GenericDAO</code> using Hibernate.
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
@SuppressWarnings("unchecked")
public class GenericDAOImpl<T, ID extends Serializable> extends
		JPABaseDAO implements GenericDAO<T, ID> {

	protected Class<T> persistentClass = (Class<T>) DAOUtil.getTypeArguments(GenericDAOImpl.class, this.getClass()).get(0);

        @Override
	public int count(ISearch search) {
		if (search == null)
			search = new Search();
		return _count(persistentClass, search);
	}

        @Override
	public T find(ID id) {
		return _find(persistentClass, id);
	}

        @Override
	public T[] find(ID... ids) {
		return _find(persistentClass, ids);
	}

        @Override
	public List<T> findAll() {
		return _all(persistentClass);
	}

        @Override
	public void flush() {
		_flush();
	}

        @Override
	public T getReference(ID id) {
		return _getReference(persistentClass, id);
	}

        @Override
	public T[] getReferences(ID... ids) {
		return _getReferences(persistentClass, ids);
	}

        @Override
	public boolean isAttached(T entity) {
		return _contains(entity);
	}

        @Override
	public void refresh(T... entities) {
		_refresh(entities);
	}

        @Override
	public boolean remove(T entity) {
		return _removeEntity(entity);
	}

        @Override
	public void remove(T... entities) {
		_removeEntities((Object[]) entities);
	}

        @Override
	public boolean removeById(ID id) {
		return _removeById(persistentClass, id);
	}

        @Override
	public void removeByIds(ID... ids) {
		_removeByIds(persistentClass, ids);
	}

        @Override
	public T merge(T entity) {
		return _merge(entity);
	}

        @Override
	public T[] merge(T... entities) {
		return _merge(persistentClass, entities);
	}

        @Override
	public void persist(T... entities) {
		_persist(entities);
	}
	
        @Override
	public T save(T entity) {
		return _persistOrMerge(entity);
	}

        @Override
	public T[] save(T... entities) {
		return _persistOrMerge(persistentClass, entities);
	}

        @Override
	public <RT> List<RT> search(ISearch search) {
		if (search == null)
			return (List<RT>) findAll();
		return _search(persistentClass, search);
	}

        @Override
	public <RT> SearchResult<RT> searchAndCount(ISearch search) {
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
		return (RT) _searchUnique(persistentClass, search);
	}

        @Override
	public Filter getFilterFromExample(T example) {
		return _getFilterFromExample(example);
	}

        @Override
	public Filter getFilterFromExample(T example, ExampleOptions options) {
		return _getFilterFromExample(example, options);
	}
}
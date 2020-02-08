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

import net.da.backing.data.search.ExampleOptions;
import net.da.backing.data.search.Filter;
import net.da.backing.data.search.ISearch;
import net.da.backing.data.search.SearchResult;

/**
 * Implementation of <code>GeneralDAO</code> using Hibernate.
 * The SessionFactory property is annotated for automatic resource injection.
 * 
 * @author dwolverton
 */
@SuppressWarnings("unchecked")
public class GeneralDAOImpl extends JPABaseDAO implements GeneralDAO {

        @Override
	public int count(ISearch search) {
		return _count(search);
	}

        @Override
	public <T> T find(Class<T> type, Serializable id) {
		return (T) _find(type, id);
	}

        @Override
	public <T> T[] find(Class<T> type, Serializable... ids) {
		return _find(type, ids);
	}

        @Override
	public <T> List<T> findAll(Class<T> type) {
		return _all(type);
	}

        @Override
	public void flush() {
		_flush();
	}

        @Override
	public <T> T getReference(Class<T> type, Serializable id) {
		return _getReference(type, id);
	}

        @Override
	public <T> T[] getReferences(Class<T> type, Serializable... ids) {
		return _getReferences(type, ids);
	}

        @Override
	public boolean isAttached(Object entity) {
		return _contains(entity);
	}

        @Override
	public void refresh(Object... entities) {
		_refresh(entities);
	}

        @Override
	public boolean remove(Object entity) {
		return _removeEntity(entity);
	}

        @Override
	public void remove(Object... entities) {
		_removeEntities(entities);
	}

        @Override
	public boolean removeById(Class<?> type, Serializable id) {
		return _removeById(type, id);
	}

        @Override
	public void removeByIds(Class<?> type, Serializable... ids) {
		_removeByIds(type, ids);
	}

        @Override
	public <T> T merge(T entity) {
		return _merge(entity);
	}

        @Override
	public Object[] merge(Object... entities) {
		return _merge(Object.class, entities);
	}

        @Override
	public void persist(Object... entities) {
		_persist(entities);
	}

        @Override
	public <T> T save(T entity) {
		return _persistOrMerge(entity);
	}

        @Override
	public Object[] save(Object... entities) {
		return _persistOrMerge(Object.class, entities);
	}

        @Override
	public List search(ISearch search) {
		return _search(search);
	}

        @Override
	public SearchResult searchAndCount(ISearch search) {
		return _searchAndCount(search);
	}

        @Override
	public Object searchUnique(ISearch search) {
		return _searchUnique(search);
	}

        @Override
	public Filter getFilterFromExample(Object example) {
		return _getFilterFromExample(example);
	}

        @Override
	public Filter getFilterFromExample(Object example, ExampleOptions options) {
		return _getFilterFromExample(example, options);
	}
}

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
package net.da.backing.data.search.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import net.da.backing.data.search.ExampleOptions;
import net.da.backing.data.search.Filter;
import net.da.backing.data.search.ISearch;
import net.da.backing.data.search.SearchFacade;
import net.da.backing.data.search.SearchResult;

/**
 * <p>
 * JPA implementation of {@link SearchFacade}.
 * 
 * <p>
 * The <code>SearchProcessor</code> and <code>EntityManager</code> must be set
 * in order for the SearchFacade to function. Generally, a single
 * SearchProcessor will be associated with an instance of JPASearchFacade for
 * the lifetime of the instance, while a new "current" EntityManager will be
 * injected as needed. Make sure that any EntityManager that is used is
 * associated with the same persistence unit (i.e. EntityManagerFactory) as the
 * SearchProcessor.
 * 
 * @author dwolverton
 */
public class JPASearchFacade implements SearchFacade {

	protected JPASearchProcessor processor;

	protected EntityManager entityManager;

	public void setSearchProcessor(JPASearchProcessor searchProcessor) {
		this.processor = searchProcessor;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
        @Override
	public List search(ISearch search) {
		return processor.search(entityManager, search);
	}

	@SuppressWarnings("unchecked")
        @Override
	public List search(Class<?> searchClass, ISearch search) {
		return processor.search(entityManager, searchClass, search);
	}

        @Override
	public int count(ISearch search) {
		return processor.count(entityManager, search);
	}

        @Override
	public int count(Class<?> searchClass, ISearch search) {
		return processor.count(entityManager, searchClass, search);
	}

	@SuppressWarnings("unchecked")
        @Override
	public SearchResult searchAndCount(ISearch search) {
		return processor.searchAndCount(entityManager, search);
	}

	@SuppressWarnings("unchecked")
        @Override
	public SearchResult searchAndCount(Class<?> searchClass, ISearch search) {
		return processor.searchAndCount(entityManager, searchClass, search);
	}

        @Override
	public Object searchUnique(ISearch search) {
		return processor.searchUnique(entityManager, search);
	}

        @Override
	public Object searchUnique(Class<?> searchClass, ISearch search) {
		return processor.searchUnique(entityManager, searchClass, search);
	}

        @Override
	public Filter getFilterFromExample(Object example) {
		return processor.getFilterFromExample(example);
	}

        @Override
	public Filter getFilterFromExample(Object example, ExampleOptions options) {
		return processor.getFilterFromExample(example, options);
	}
}

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
 * Interface for general Data Access Object that can be used for any type domain
 * object. A single instance implementing this interface can be used for
 * multiple types of domain objects.
 * 
 * @author dwolverton
 */
public interface GeneralDAO {

    /**
    * <p>
    * Get the entity with the specified type and id from the datastore.
    * 
    * <p>
    * If none is found, return null.
    * @param <T>
    * @param type
    * @param id
    * @return 
    */
    public <T> T find(Class<T> type, Serializable id);

    /**
     * Get all entities of the specified type from the datastore that have one
     * of these ids. An array of entities is returned that matches the same
     * order of the ids listed in the call. For each entity that is not found in
     * the datastore, a null will be inserted in its place in the return array.
     * @param <T>
     * @param type
     * @param ids
     * @return 
     */
    public <T> T[] find(Class<T> type, Serializable... ids);

    /**
     * <p>
     * Get a reference to the entity with the specified type and id from the
     * datastore.
     * 
     * <p>
     * This does not require a call to the datastore and does not populate any
     * of the entity's values. Values may be fetched lazily at a later time.
     * This increases performance if a another entity is being saved that should
     * reference this entity but the values of this entity are not needed.
     * 
     * @param <T>
     * @param type
     * @param id
     * @return 
     * @throws a
     *             HibernateException if no matching entity is found
     */
    public <T> T getReference(Class<T> type, Serializable id);

    /**
     * <p>
     * Get a reference to the entities of the specified type with the given ids
     * from the datastore. An array of entities is returned that matches the
     * same order of the ids listed in the call.
     * 
     * <p>
     * This does not require a call to the datastore and does not populate any
     * of the entities' values. Values may be fetched lazily at a later time.
     * This increases performance if a another entity is being saved that should
     * reference these entities but the values of these entities are not needed.
     * 
     * @param <T>
     * @param type
     * @param ids
     * @return 
     * @throws a
     *             HibernateException if any of the matching entities are not
     *             found.
     */
    public <T> T[] getReferences(Class<T> type, Serializable... ids);

    /**
     * <p>
     * Make a transient instance persistent and add it to the datastore. This
     * operation cascades to associated instances if the association is mapped
     * with cascade="persist". Throws an error if the entity already exists.
     * 
     * <p>
     * Does not guarantee that the object will be assigned an identifier
     * immediately. With <code>persist</code> a datastore-generated id may not
     * be pulled until flush time.
     * @param entities
     */
    public void persist(Object... entities);

    /**
     * <p>
     * Copy the state of the given object onto the persistent object with the
     * same identifier. If there is no persistent instance currently associated
     * with the session, it will be loaded. Return the persistent instance. If
     * the given instance is unsaved, save a copy and return it as a newly
     * persistent instance.
     * 
     * <p>
     * The instance that is passed in does not become associated with the
     * session. This operation cascades to associated instances if the
     * association is mapped with cascade="merge".
     * @param <T>
     * @param entity
     * @return 
     */
    public <T> T merge(T entity);

    /**
     * <p>
     * Copy the state of the given objects onto the persistent objects with the
     * same identifier. If there is no persistent instance currently associated
     * with the session, it will be loaded. Return the persistent instances. If
     * a given instance is unsaved, save a copy and return it as a newly
     * persistent instance.
     * 
     * <p>
     * The instances that are passed in do not become associated with the
     * session. This operation cascades to associated instances if the
     * association is mapped with cascade="merge".
     * @param entities
     * @return 
     */
    public Object[] merge(Object... entities);

    /**
     * If an entity with the same ID already exists in the database, merge the
     * changes into that entity. If not persist the given entity. In either
     * case, a managed entity with the changed values is returned. It may or may
     * not be the same object as was passed in.
     * @param <T>
     * @param entity
     * @return 
     */
    public <T> T save(T entity);

    /**
     * <p>
     * For each entity: If an entity with the same ID already exists in the
     * database, merge the changes into that entity. If not persist the given
     * entity. In either case, a managed entity with the changed values is
     * returned. It may or may not be the same object as was passed in.
     * 
     * @param entities
     * @return an array containing each managed entity corresponding to the
     *         entities passed in.
     */
    public Object[] save(Object... entities);

    /**
     * Remove the specified entity from the datastore.
     * 
     * @param entity
     * @return <code>true</code> if the entity is found in the datastore and
     *         removed, <code>false</code> if it is not found.
     */
    public boolean remove(Object entity);

    /**
     * Remove all of the specified entities from the datastore.
     * @param entities
     */
    public void remove(Object... entities);

    /**
     * Remove the entity with the specified type and id from the datastore.
     * 
     * @param type
     * @param id
     * @return <code>true</code> if the entity is found in the datastore and
     *         removed, <code>false</code> if it is not found.
     */
    public boolean removeById(Class<?> type, Serializable id);

    /**
     * Remove all the entities of the given type from the datastore that have
     * one of these ids.
     * @param type
     * @param ids
     */
    public void removeByIds(Class<?> type, Serializable... ids);

    /**
     * Get a list of all the objects of the specified type.
     * @param <T>
     * @param type
     * @return 
     */
    public <T> List<T> findAll(Class<T> type);

    /**
     * Search for objects given the search parameters in the specified
     * <code>ISearch</code> object.
     * @param search
     * @return 
     */
    @SuppressWarnings("unchecked")
    public List search(ISearch search);

    /**
     * Search for a single result using the given parameters.
     * @param search
     * @return 
     */
    public Object searchUnique(ISearch search);

    /**
     * Returns the total number of results that would be returned using the
     * given <code>ISearch</code> if there were no paging or maxResults limits.
     * @param search
     * @return 
     */
    public int count(ISearch search);

    /**
     * Returns a <code>SearchResult</code> object that includes both the list of
     * results like <code>search()</code> and the total length like
     * <code>count()</code>.
     * @param search
     * @return 
     */
    @SuppressWarnings("unchecked")
    public SearchResult searchAndCount(ISearch search);

    /**
     * Returns <code>true</code> if the object is connected to the current
     * Hibernate session.
     * @param entity
     * @return 
     */
    public boolean isAttached(Object entity);

    /**
     * Refresh the content of the given entity from the current datastore state.
     * @param entities
     */
    public void refresh(Object... entities);

    /**
     * Flushes changes in the Hibernate session to the datastore.
     */
    public void flush();

    /**
     * Generates a search filter from the given example using default options.
     * @param example
     * @return 
     */
    public Filter getFilterFromExample(Object example);

    /**
     * Generates a search filter from the given example using the specified
     * options.
     * @param example
     * @param options
     * @return 
     */
    public Filter getFilterFromExample(Object example, ExampleOptions options);

}

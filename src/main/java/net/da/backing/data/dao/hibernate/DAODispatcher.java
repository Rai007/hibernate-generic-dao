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
import java.util.List;

import net.da.backing.data.dao.BaseDAODispatcher;
import net.da.backing.data.dao.DAODispatcherException;
import net.da.backing.data.search.ExampleOptions;
import net.da.backing.data.search.Filter;
import net.da.backing.data.search.ISearch;
import net.da.backing.data.search.SearchResult;

/**
 * <p>This is an implementation of IGeneralDAO that delegates to other DAOs
 depending on what entity class is being processed.
 
 <p>Set the specificDAOs Map in order to configure which DAO will be used
 * for which entity class. If the map contains no entry for a given class,
 * the generalDAO is used.
 * 
 * <p>For example to dispatch operation on com.myproject.model.Customer to a DAO called customerDAO,
 * set the map like this. (Of course tools like Spring can be used to do this
 * configuration more elequently.)
 * <pre>
 * Map<String,Object> specificDAOs = new HashMap<String,Object>();
 * specificDAOs.put("com.myproject.model.Customer", customerDAO);
 * 
 * DAODispatcher dispatcher = new DAODispatcher();
 * dispatcher.setSpecificDAOs(specificDAOs);
 * </pre>
 * 
 * @author dwolverton
 *
 */
@SuppressWarnings("unchecked")
public class DAODispatcher extends BaseDAODispatcher implements IGeneralDAO {

    protected IGeneralDAO generalDAO;

    /**
     * IGeneralDAO has default implementations for the standard DAO methods.
     * Which model class it uses is specified when calling the particular
     * method.
     * @param generalDAO
     */
    public void setGeneralDAO(IGeneralDAO generalDAO) {
            this.generalDAO = generalDAO;
    }

    @Override
    public int count(ISearch search) {
            Object specificDAO = getSpecificDAO(search.getSearchClass().getName());
            if (specificDAO != null) {
                    if (specificDAO instanceof IGenericDAO) {
                            return ((IGenericDAO) specificDAO).count(search);
                    } else {
                            return (Integer) callMethod(specificDAO, "count", search);
                    }
            } else {
                    return generalDAO.count(search);
            }
    }

    @Override
    public <T> T find(Class<T> type, Serializable id) {
            Object specificDAO = getSpecificDAO(type.getName());
            if (specificDAO != null) {
                    if (specificDAO instanceof IGenericDAO) {
                            return (T) ((IGenericDAO) specificDAO).find(id);
                    } else {
                            return (T) callMethod(specificDAO, "find", id);
                    }
            } else {
                    return (T) generalDAO.find(type, id);
            }
    }

    @Override
    public <T> T[] find(Class<T> type, Serializable... ids) {
            Object specificDAO = getSpecificDAO(type.getName());
            if (specificDAO != null) {
                    if (specificDAO instanceof IGenericDAO) {
                            return (T[]) ((IGenericDAO) specificDAO).find(ids);
                    } else {
                            return (T[]) callMethod(specificDAO, "find", (Object[]) ids);
                    }
            } else {
                    return (T[]) generalDAO.find(type, ids);
            }
    }

    @Override
    public <T> List<T> findAll(Class<T> type) {
            Object specificDAO = getSpecificDAO(type.getName());
            if (specificDAO != null) {
                    if (specificDAO instanceof IGenericDAO) {
                            return ((IGenericDAO) specificDAO).findAll();
                    } else {
                            return (List) callMethod(specificDAO, "findAll");
                    }
            } else {
                    return generalDAO.findAll(type);
            }
    }

    /**
     * @deprecated use flush(Class<?>)
     */
    @Override
    public void flush() {
            throw new DAODispatcherException(
                            "The flush() method cannot be used with DAODispatcher because it could does not include a Class type to dispatch to. Use flush(Class<?>).");
    }

    public void flush(Class<?> klass) {
            Object specificDAO = getSpecificDAO(klass.getName());
            if (specificDAO != null) {
                    if (specificDAO instanceof IGenericDAO) {
                            ((IGenericDAO) specificDAO).flush();
                    } else {
                            callMethod(specificDAO, "flush");
                    }
            } else {
                    generalDAO.flush();
            }
    }

    @Override
    public <T> T getReference(Class<T> type, Serializable id) {
            Object specificDAO = getSpecificDAO(type.getName());
            if (specificDAO != null) {
                    if (specificDAO instanceof IGenericDAO) {
                            return (T) ((IGenericDAO) specificDAO).getReference(id);
                    } else {
                            return (T) callMethod(specificDAO, "getReference", id);
                    }
            } else {
                    return (T) generalDAO.getReference(type, id);
            }
    }

    @Override
    public <T> T[] getReferences(Class<T> type, Serializable... ids) {
            Object specificDAO = getSpecificDAO(type.getName());
            if (specificDAO != null) {
                    if (specificDAO instanceof IGenericDAO) {
                            return (T[]) ((IGenericDAO) specificDAO).getReferences(ids);
                    } else {
                            return (T[]) callMethod(specificDAO, "getReferences", (Object[]) ids);
                    }
            } else {
                    return generalDAO.getReferences(type, ids);
            }
    }

    @Override
    public boolean isAttached(Object entity) {
            Object specificDAO = getSpecificDAO(entity.getClass().getName());
            if (specificDAO != null) {
                    if (specificDAO instanceof IGenericDAO) {
                            return ((IGenericDAO) specificDAO).isAttached(entity);
                    } else {
                            return (Boolean) callMethod(specificDAO, "isAttached", entity);
                    }
            } else {
                    return generalDAO.isAttached(entity);
            }
    }

    @Override
    public void refresh(Object... entities) {
            Class<?> type = getUniformArrayType(entities);
            if (type == null) return;
            if (type.equals(Object.class)) {
                    //There are several different types of entities
                    for (Object entity : entities) {
                            refresh(entity);
                    }
                    return;
            }		

            Object specificDAO = getSpecificDAO(type.getName());
            if (specificDAO != null) {
                    if (specificDAO instanceof IGenericDAO) {
                            ((IGenericDAO) specificDAO).refresh(entities);
                    } else {
                            callMethod(specificDAO, "refresh", entities);
                    }
            } else {
                    generalDAO.refresh(entities);
            }
    }

    @Override
    public boolean remove(Object entity) {
            Object specificDAO = getSpecificDAO(entity.getClass().getName());
            if (specificDAO != null) {
                    if (specificDAO instanceof IGenericDAO) {
                            return ((IGenericDAO) specificDAO).remove(entity);
                    } else {
                            return (Boolean) callMethod(specificDAO, "remove", entity);
                    }
            } else {
                    return generalDAO.remove(entity);
            }
    }

    @Override
    public void remove(Object... entities) {
            Class<?> type = getUniformArrayType(entities);
            if (type == null) return;
            if (type.equals(Object.class)) {
                    //There are several different types of entities
                    for (Object entity : entities) {
                            remove(entity);
                    }
                    return;
            }

            Object specificDAO = getSpecificDAO(type.getName());
            if (specificDAO != null) {
                    if (specificDAO instanceof IGenericDAO) {
                            ((IGenericDAO) specificDAO).remove(entities);
                    } else {
                            callMethod(specificDAO, "remove", entities);
                    }
            } else {
                    generalDAO.remove(entities);
            }
    }

    @Override
    public boolean removeById(Class<?> type, Serializable id) {
            Object specificDAO = getSpecificDAO(type.getName());
            if (specificDAO != null) {
                    if (specificDAO instanceof IGenericDAO) {
                            return ((IGenericDAO) specificDAO).removeById(id);
                    } else {
                            return (Boolean) callMethod(specificDAO, "removeById", id);
                    }
            } else {
                    return generalDAO.removeById(type, id);
            }
    }

    @Override
    public void removeByIds(Class<?> type, Serializable... ids) {
            Object specificDAO = getSpecificDAO(type.getName());
            if (specificDAO != null) {
                    if (specificDAO instanceof IGenericDAO) {
                            ((IGenericDAO) specificDAO).removeByIds(ids);
                    } else {
                            callMethod(specificDAO, "removeByIds", (Object[]) ids);
                    }
            } else {
                    generalDAO.removeByIds(type, ids);
            }
    }

    @Override
    public boolean save(Object entity) {
            Object specificDAO = getSpecificDAO(entity.getClass().getName());
            if (specificDAO != null) {
                    if (specificDAO instanceof IGenericDAO) {
                            return ((IGenericDAO) specificDAO).save(entity);
                    } else {
                            return (Boolean) callMethod(specificDAO, "save", entity);
                    }
            } else {
                    return generalDAO.save(entity);
            }
    }

    @Override
    public boolean[] save(Object... entities) {
            if (entities == null)
                    return null;
            Class<?> type = getUniformArrayType(entities);
            if (type == null)
                    return new boolean[entities.length];
            if (type.equals(Object.class)) {
                    //There are several different types of entities
                    boolean[] isNew = new boolean[entities.length];
                    for (int i = 0; i < entities.length; i++) {
                            isNew[i] = save(entities[i]);
                    }
                    return isNew;
            }

            Object specificDAO = getSpecificDAO(type.getName());
            if (specificDAO != null) {
                    if (specificDAO instanceof IGenericDAO) {
                            return ((IGenericDAO) specificDAO).save(entities);
                    } else {
                            return (boolean[]) callMethod(specificDAO, "save", entities);
                    }
            } else {
                    return generalDAO.save(entities);
            }
    }

    @Override
    public List search(ISearch search) {
        Object specificDAO = getSpecificDAO(search.getSearchClass().getName());
        if (specificDAO != null) {
            if (specificDAO instanceof IGenericDAO) {
                return ((IGenericDAO) specificDAO).search(search);
            } else {
                return (List) callMethod(specificDAO, "search", search);
            }
        } else {
            return generalDAO.search(search);
        }
    }

    @Override
    public SearchResult searchAndCount(ISearch search) {
            Object specificDAO = getSpecificDAO(search.getSearchClass().getName());
            if (specificDAO != null) {
                    if (specificDAO instanceof IGenericDAO) {
                            return ((IGenericDAO) specificDAO).searchAndCount(search);
                    } else {
                            return (SearchResult) callMethod(specificDAO, "searchAndCount", search);
                    }
            } else {
                    return generalDAO.searchAndCount(search);
            }
    }

    @Override
    public Object searchUnique(ISearch search) {
            Object specificDAO = getSpecificDAO(search.getSearchClass().getName());
            if (specificDAO != null) {
                    if (specificDAO instanceof IGenericDAO) {
                            return ((IGenericDAO) specificDAO).searchUnique(search);
                    } else {
                            return callMethod(specificDAO, "searchUnique", search);
                    }
            } else {
                    return generalDAO.searchUnique(search);
            }
    }

    @Override
    public Filter getFilterFromExample(Object example) {
            Object specificDAO = getSpecificDAO(example.getClass().getName());
            if (specificDAO != null) {
                    if (specificDAO instanceof IGenericDAO) {
                            return ((IGenericDAO) specificDAO).getFilterFromExample(example);
                    } else {
                            return (Filter) callMethod(specificDAO, "getFilterFromExample", example);
                    }
            } else {
                    return generalDAO.getFilterFromExample(example);
            }
    }

    @Override
    public Filter getFilterFromExample(Object example, ExampleOptions options) {
            Object specificDAO = getSpecificDAO(example.getClass().getName());
            if (specificDAO != null) {
                    if (specificDAO instanceof IGenericDAO) {
                            return ((IGenericDAO) specificDAO).getFilterFromExample(example, options);
                    } else {
                            return (Filter) callMethod(specificDAO, "getFilterFromExample", example, options);
                    }
            } else {
                    return generalDAO.getFilterFromExample(example, options);
            }
    }

}

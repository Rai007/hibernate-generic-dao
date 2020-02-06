/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.da.backing.data.hibernate;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *  Class use for implements Hibernate-generic-dao GRUD. 
 * extends this class and inherit method from it. speed-up progress create DAO object
 * 
 * @author ShadowWalker
 * @param <T>
 */
public abstract class AbstractHibernateDao < T extends Serializable > {
    
    @Autowired
    private SessionFactory sessionFactory;

    private Class< T > clazz;
    
    /**
     * Use setClazz when user don't have time to create any specific DAO class for each object.
     * In service layer. We use "HibernateGenericDao" (autowired) and set class for that variable to get DAO object from concrete class
     * Example: private IGenericDao<User> userDao;
     *          @Autowired
     *          public void setUserDao(IGenericDao<User> userDao){
     *              this.userDao = userDao;
     *              userDao.setClazz( User.class ); // use this in constructor of service layer class
     *          }
     * 
     * @param clazzToSet 
    */ 
    public void setClazz( Class< T > clazzToSet ){
        this.clazz = clazzToSet;
    }
    
    /**
     * Function will return proxy object by specified identifier without hitting the database. But object is proxy
     * and its properties aren't initiated yet. it just look like a temporary fake object. (Lazy load)
     * If no row found , it will throws an ObjectNotFoundException. 
     * When object's properties is retrieved. Database will be hitting for getting data.
     * Equivalent to session.load() 
     * 
     * @param id
     * @return 
     */
    public T getReference(final Serializable id){
        return (T) getSession().getReference( getClassOfT(), id );
    }
    
    /**
     * Query and get object by id. Hitting the database immediately.
     * return null if object not found
     * 
     * @param id
     * @return 
     */
    public T find(final Serializable id) {
        return (T) getSession().get( getClassOfT(), id );
    }
    
    /**
     * Query and get objects by ids. Hitting the database immediately.
     * 
     * @param ids
     * @return 
     */
    public List<T> find(final Serializable... ids) {
        MultiIdentifierLoadAccess<T> multiLoadAccess = getSession().byMultipleIds( getClassOfT() );
        List<T> list = multiLoadAccess.multiLoad( ids );
        
        return list;
    }
    
    /**
     * Find all Object by given type T
     * 
     * @return 
     */
    public List< T > findAll(){
       return getSession().createQuery( "from " + getClassOfT().getName() ).list();
    }

    
    /**
     * Persist Object to database. Don't return identity
     * 
     * @param entity 
     */
    public void create( T entity ){
       getSession().persist( entity );
    }

    public T update( T entity ){
       return (T) getSession().merge( entity );
    }

    /**
     * Delete Object in database
     * 
     * @param entity 
     */
    public void delete(final T entity){
       getSession().delete( entity );
    }
    
    /**
     * Delete Object by given identity
     * 
     * @param entityId 
     */
    public void deleteById( final Serializable entityId ) {
       T entity = find( entityId );
       delete( entity );
    }
    
    /**
     * Delete Objects by given array of identities
     * 
     * @param entityIds 
     */
    public void deleteByIds( final Serializable... entityIds){
        List<T> list = find(entityIds);
        list.stream().forEach( (e) -> delete(e) );
    }
    
    /**
     * Get Hibernate session from SessionFactory
     * 
     * @return 
     */
    protected final Session getSession() {
       return sessionFactory.getCurrentSession();
    } 
    
    /**
     * Get class type of argument T
     * 
     * @return 
     */
    private Class< T > getClassOfT(){
        if(this.clazz == null ){
            final ParameterizedType type = (ParameterizedType) this.getClass()
                    .getGenericSuperclass();
            Class<T> clazzOfT = (Class<T>) type.getActualTypeArguments()[0];
            return clazzOfT;
        }else{
            return this.clazz;
        }
    }
    
}
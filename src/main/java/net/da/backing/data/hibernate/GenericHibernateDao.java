/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.da.backing.data.hibernate;

import java.io.Serializable;
import net.da.backing.data.IGenericDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
/**
 * Case 1: Extending this class for adding several function support data query
 * Example: public class AccountDao extends GenericHibernateDao < Account > { ... }   ### Class declaration
 * 
 * Case 2: Use as a helper class. Need to initiate dao object first.
 * Example: @Autowired IGenericDao accountDao;                          ### Class variable
 *          public void setAccountDao(AccountDao<Account> accountDao){  ### In constructor or @PostConsutrct annotation of classes
 *              this.accountDao = accountDao;
 *              AccountDao.setClazz( Account.class );
 *          }
 * 
 * @author ShadowWalker
 * @param <T>
 */
@Repository
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class GenericHibernateDao < T extends Serializable >
  extends AbstractHibernateDao< T > implements IGenericDao< T > {

}

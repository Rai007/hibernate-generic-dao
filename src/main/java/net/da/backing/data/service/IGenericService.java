/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.da.backing.data.service;

import java.io.Serializable;
import net.da.backing.data.dao.hibernate.IGenericDAO;

/**
 *
 * @author ShadowWalker
 * @param <T>
 * @param <ID>
 */
public interface IGenericService<T extends Serializable, ID extends Serializable> 
        extends IGenericDAO<T, ID>{
    // Mapping service to dao
}

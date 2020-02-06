/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.da.backing.data.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Base class for Service layer. Supporting methods and utilities 
 * to facilitate create, manipulate components in this layer.
 * Easy for debugging, tracing, 
 * 
 * @author ShadowWalker
 */
public class BaseService {
    
    /* LOGGING METHODS */
    private final String LAYER_NAME = "[SERVICE_L]";
    
    protected final Logger LOGGER = LoggerFactory.getLogger( getClass() );
    
    protected void info(String msg){
        LOGGER.info("{} " + msg, LAYER_NAME);
    }
    
    protected void info(String msg, Object... os){
        LOGGER.info("{} " + msg, LAYER_NAME, os);
    }
    
    protected void trace(String msg){
        LOGGER.trace("{} " + msg, LAYER_NAME);
    }
    
    protected void trace(String msg, Object... os){
        LOGGER.trace("{} " + msg, LAYER_NAME, os);
    }
    
    protected void debug(String msg){
        LOGGER.debug("{} " + msg, LAYER_NAME);
    }
    
    protected void debug(String msg, Object... os){
        LOGGER.debug("{} " + msg, LAYER_NAME, os);
    }
    
    protected void warn(String msg){
        LOGGER.warn("{} " + msg, LAYER_NAME);
    }
    
    protected void warn(String msg, Object... os){
        LOGGER.warn("{} " + msg, LAYER_NAME, os);
    }
    
    protected void error(String msg){
        LOGGER.error("{} " + msg, LAYER_NAME);
    }
    
    protected void error(String msg, Object... os){
        LOGGER.error("{} " + msg, LAYER_NAME, os);
    }
}

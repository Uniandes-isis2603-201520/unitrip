/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.providers;

/**
 *
 * @author n.vasquez10
 */
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.ws.rs.NameBinding;

@NameBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface StatusCreated {}

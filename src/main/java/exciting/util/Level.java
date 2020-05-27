/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exciting.util;

/**
 * Level describes all valid difficulty levels.
 *
 * @author Tsz Shing Tsoi
 * @version 1.1
 */
public enum Level {

    // enum constants are public, static and final by default
    NOVICE,
    INTERMEDIATE,
    ADVANCED;
    
    @Override
    public String toString(){
        return this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase();
    }

}

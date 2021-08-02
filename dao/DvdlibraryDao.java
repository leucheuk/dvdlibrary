/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.dvdlibrary.dao;

import com.sherlock.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author Sherlock
 */
public interface DvdlibraryDao {
        /**
     * Adds the given Dvd to the dvdlibrary . 
     *
     * @param dvd dvd to be added to the roster
     * @return the dvd object previously exits, null otherwise
     */
    Dvd addDvd(Dvd dvd)
            throws DvdLibraryDaoException ;

    /**
     * Returns a List of all dvds in the library.
     *
     * @return List containing all dvds in the roster.
     */
    List<Dvd> getAllDvds()
            throws DvdLibraryDaoException ;

    /**
     * Returns the Dvd object associated with the given title.
     * Returns null if no such DVD exists
     *
     * @param title of the dvd to retrieve
     * @return the Dvd object associated with the given title,  
     * null if no such DVD exists
     */
    Dvd getDvd(String title)
            throws DvdLibraryDaoException ;

    /**
     * Removes from the library the dvd associated with the title.
     * Returns the dvd object that is being removed or null if
     * there is no dvd associated with the given title
     *
     * @param title of dvd to be removed
     * @return Dvd object that was removed or null if no DVD
     * was associated with the given title
     */
    Dvd removeDvd(String title)
            throws DvdLibraryDaoException ;
    
    /**
     *
     * @param title
     * @param fieldID
     * @param field
     
     * @return
     */
    Dvd editDvd(String title, int fieldID, String field)
            throws DvdLibraryDaoException ;
}
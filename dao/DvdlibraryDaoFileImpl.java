/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.dvdlibrary.dao;

import com.sherlock.dvdlibrary.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


import java.util.List;
import java.util.Scanner;


/**
 *
 * @author Sherlock
 */
public class DvdlibraryDaoFileImpl implements DvdlibraryDao {
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    
    List<Dvd> dvdlibrary = new ArrayList<>();
    
    @Override
    public Dvd addDvd(Dvd dvd) 
            throws DvdLibraryDaoException {
        loadLibrary();
    //    if(dvd.getTitle()==null) 
    //    return null;               //no need to writeLibrary if null
    dvdlibrary.add(dvd);
    writeLibrary();
    return dvd;
    }

    @Override
    public List<Dvd> getAllDvds() 
            throws DvdLibraryDaoException  {
    loadLibrary();
    List<Dvd> dvds = new ArrayList<>();
    dvds.addAll(dvdlibrary);
    return dvds;
    }
    
    @Override
    public Dvd getDvd(String title)
            throws DvdLibraryDaoException  {
    Dvd dvd;  
    loadLibrary();
    for(Dvd currentDvd : dvdlibrary) {
        if (currentDvd.getTitle().equals(title)) {
            dvd= currentDvd;
            return dvd;
        }
    }
    return null; 
    }

    @Override
    public Dvd removeDvd(String title) 
            throws DvdLibraryDaoException {
    loadLibrary();
     Dvd removedDvd = getDvd(title);
     if (removedDvd != null)
         dvdlibrary.remove(removedDvd);
    writeLibrary(); 
    return removedDvd;
    }

    /**
     *
     * @param title
     * @param fieldID
     * @param field
     * @return
     */
    @Override
    public Dvd editDvd(String title, int fieldID, String field)
            throws DvdLibraryDaoException  {
      loadLibrary(); 
     
      Dvd removedDvd = getDvd(title);
      Dvd editedDvd = removedDvd;
      switch (fieldID) {
          case 1: 
              editedDvd.setTitle(field);
              break;
          case 2:
                editedDvd.setReleaseDate(field);
                break;
          case 3:
                editedDvd.setMpaaRating(field);
                break;
          case 4:
                editedDvd.setDirector(field);
                break;          
          case 5:
                editedDvd.setStudio(field);
                break;  
          case 6:
                editedDvd.setuserComment(field);
                break;  
                
      }

      //removeDvd(title);
      dvdlibrary.remove(removedDvd);
      //addDvd(editedDvd);
      dvdlibrary.add(editedDvd);
      writeLibrary();
      return editedDvd;
      }
        

    private Dvd unmarshallDvd( String dvdAsText ) {
    // dvdAsText is expecting a line read in from our file.
    // It look like this:
    // HULK::June 2003::PG13::Ang Lee::Warner Bros. Pictures::good movie
    //
    // We then split that line on our DELIMITER - which we are using as ::
    // Leaving us with an array of Strings, stored in dvdTokens.
    // Which should look like this:
    // ____________________________________________
    // |    |        |    |       |          |     |
    // |HULK|jun 2003|PG13|Ang Lee|WarnerBros|good
    // |    |        |    |                  |     |
    // --------------------------------------------
    //  [0] | [1]    | [2]|  [3]  |  _[4]    | [5] |
    
    String[] dvdTokens = dvdAsText.split(DELIMITER);

    // Given the pattern above, title is in index 0 of the array.
    String title = dvdTokens[0];

    // Which we can then use to create a new Dvd object to satisfy
    // the requirements of the Dvd constructor.
    Dvd dvdFromFile = new Dvd(title);

    // However, there are 5 remaining tokens that need to be set into the
    // new student object. Do this manually by using the appropriate setters.

    // Index 1 - release date
    dvdFromFile.setReleaseDate(dvdTokens[1]);

    // Index 2 - MPAA rating
    dvdFromFile.setMpaaRating(dvdTokens[2]);

    // Index 3 - Director
    dvdFromFile.setDirector(dvdTokens[3]);
    
    //index 4 = Studio
    dvdFromFile.setStudio(dvdTokens[4]);
    
     //index 5 = user comment
    dvdFromFile.setuserComment(dvdTokens[5]);   

    // We have now created a dvd! Return it!
    return dvdFromFile;
}
    private void loadLibrary() throws DvdLibraryDaoException {
   
    if (dvdlibrary.isEmpty() == true)
    { 
        Scanner scanner;
            try {
                // Create Scanner for reading the file
                scanner = new Scanner(
                        new BufferedReader(
                                new FileReader(LIBRARY_FILE)));
            } catch (FileNotFoundException e) {
                throw new DvdLibraryDaoException(
                        "-_- Could not load library data into memory.", e);
            }
            // currentLine holds the most recent line read from the file
            String currentLine;
            // currentDvd holds the most recent dvd unmarshalled
            Dvd currentDvd;
            // Go through LIBRARY_FILE line by line, decoding each line into a 
            // Dvd object by calling the unmarshallDvd method.
            // Process while we have more lines in the file
            while (scanner.hasNextLine()) {
                // get the next line in the file
                currentLine = scanner.nextLine();
                // unmarshall the line into a Dvd
                currentDvd = unmarshallDvd(currentLine);

                // We then add currentDvd to library
                dvdlibrary.add(currentDvd);

            }
            // close scanner

        scanner.close();
    }
}
    private String marshallDvd(Dvd aDvd){
    // We need to turn a Dvd object into a line of text for our file.
    // For example, we need an in memory object to end up like this:
    // HULK::June 2003::PG13::Ang Lee::Warner Bros. Pictures::good movie

    // It's not a complicated process. Just get out each property,
    // and concatenate with our DELIMITER as a kind of spacer. 

    // Start with the title, since that's supposed to be first.
    String dvdAsText = aDvd.getTitle() + DELIMITER;

    // add the rest of the properties in the correct order:

    // Release date
    dvdAsText += aDvd.getReleaseDate() + DELIMITER;

    // MPAA rating
    dvdAsText += aDvd.getMpaaRating() + DELIMITER;

    // Dairector's name
    dvdAsText += aDvd.getDirector() + DELIMITER;
    
    //Studio's name
    dvdAsText += aDvd.getStudio() + DELIMITER;
    
    //user comment
    dvdAsText += aDvd.getUserComment();

    // We have now turned a dvd to text! Return it!
    return dvdAsText;
}
    /**
 * Writes all dvds in the library out to a LIBRARY_FILE.  See loadLIBRARY
 * for file format.
 * 
 * @throws ClassLibraryDaoException if an error occurs writing to the file
 */
    private void writeLibrary() 
            throws DvdLibraryDaoException {
    // We are not handling the IOException - but
    // we are translating it to an application specific exception and 
    // then simple throwing it (i.e. 'reporting' it) to the code that
    // called us.  It is the responsibility of the calling code to 
    // handle any errors that occur.
    PrintWriter out;

    try {
        out = new PrintWriter(new FileWriter(LIBRARY_FILE));
    } catch (IOException e) {
        throw new DvdLibraryDaoException(
                "Could not save dvd data.", e);
    }

    // Write out the Dvd objects to the library file.
    // We could just grab the dvd library,
    // get the Collection of Dvds and iterate over them but we've
    // already created a method that gets a List of Dvds so
    // we'll reuse it.
    String dvdAsText;
    List<Dvd> dvd_library = this.getAllDvds();
    for (Dvd currentDvd : dvd_library) {
        // turn a Dvd into a String
        dvdAsText = marshallDvd(currentDvd);
        // write the Dvd object to the file
        out.println(dvdAsText);
        // force PrintWriter to write line to the file
        out.flush();
    }
    // Clean up
    out.close();
}
    
}
    

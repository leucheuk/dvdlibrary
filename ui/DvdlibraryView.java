/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.dvdlibrary.ui;

import com.sherlock.dvdlibrary.dto.Dvd;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Sherlock
 */
public class DvdlibraryView {
     
    private UserIO io;
    
    public DvdlibraryView(UserIO io) {
    this.io = io;
}
    
    //private UserIO io = new UserIOConsoleImpl();

    public int printMenuAndGetSelection() {
            io.print("Main Menu");
            io.print("1. Add a new DVD to the collection");
            io.print("2. Remove a DVD from the collection");
            io.print("3. Edit the information for an existing DVD");
            io.print("4. List all the DVDs in the collection");
            io.print("5. Display the information for a particular DVD");
            io.print("6. Search for a DVD by title");
            io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }
    
    
    public Dvd getNewDvdInfo() {
    String title = io.readString("Please enter Dvd title");
    String releaseDate = io.readString("Please enter release date");
    String mpaaRating = io.readString("Please enter MPAA Rating");
    String director = io.readString("Please enter Director's Name");
    String studio = io.readString("Please enter Studio");
    String userComment = io.readString("Please enter user comments");
    
    Dvd currentDvd = new Dvd(title);
    currentDvd.setTitle(title);
    currentDvd.setReleaseDate(releaseDate);
    currentDvd.setMpaaRating(mpaaRating);
    currentDvd.setDirector(director);
    currentDvd.setStudio(studio);
    currentDvd.setuserComment(userComment);
    return currentDvd;
    }
    
    public void displayCreateDvdBanner() {
    io.print("=== Create DVD ===");
}
    
    public void displayCreateSuccessBanner() {
    io.readString(
            "DVD successfully created.  Please hit enter to continue");
}
    
    public void displayCreateUnsuccessBanner() {
    io.readString(
            "Create new DVD Unsuccess.  Please hit enter to continue");
}
    
public void displayDvdList(List<Dvd> dvdList) {
    for (Dvd currentDvd : dvdList) {
        String dvdInfo = String.format("DVD: %s : %s %s",
              currentDvd.getTitle(),
              currentDvd.getReleaseDate(),
              currentDvd.getDirector()
        );
        io.print(dvdInfo);
    }
    io.readString("Please hit enter to continue.");
}
   
public void displayDisplayAllBanner() {
    io.print("=== Display All DVDs ===");
}

public void displayDisplayDvdBanner () {
    io.print("=== Display DVD ===");
}

public String getDvdTitleChoice() {
    return io.readString("Please enter the DVD title: ");
}

public int getDvdFieldChoice(){
    int fieldID;
    boolean isvalid=false;
    Scanner myScanner = new Scanner(System.in);
    

            System.out.println("Please select field to edit ");
            System.out.println("1. Title ");
            System.out.println("2. Release Date ");
            System.out.println("3. MPAA rating ");
            System.out.println("4. Director's name ");
            System.out.println("5. Studio ");
            System.out.println("6. User comment ");
            System.out.println("7. Exit EDIT DVD ");
            
               //   String input = myScanner.nextLine();
               //   fieldID = Integer.parseInt(input);
               
               fieldID=io.readInt("Please select from the above choices.", 1, 7);

                if (fieldID==7)
                      fieldID=0;
    return fieldID;
}

public void displayDvd(Dvd dvd) {
    if (dvd != null) {
        io.print("Title: " + dvd.getTitle());
        io.print("Release date: " + dvd.getReleaseDate());
        io.print("MPAA Rating: " + dvd.getMpaaRating());
        io.print("Director: " + dvd.getDirector());
        io.print("Studio: " + dvd.getStudio());
        io.print("User comments: " + dvd.getUserComment());
        io.print("");
    } else {
        io.print("No such title.");
    }
    io.readString("Please hit enter to continue.");
}
public void displayRemoveDvdBanner () {
    io.print("=== Remove DVD ===");
}

public void displayRemoveResult(Dvd dvdRecord) {
    if(dvdRecord != null){
      io.print("DVD successfully removed.");
    }else{
      io.print("No such DVD.");
    }
    io.readString("Please hit enter to continue.");
}

public void displayEditDvdBanner () {
    io.print("=== Edit DVD ===");
}

public void displayEditResult(Dvd dvdRecord) {
    if(dvdRecord != null){
      io.print("DVD successfully edited.");
    }else{
      io.print("No such DVD.");
    }
    io.readString("Please hit enter to continue.");
}



public void displayExitBanner() {
    io.print("Good Bye!!!");
}

public void displayUnknownCommandBanner() {
    io.print("Unknown Command!!!");
}

public String getDvdField(int fieldID){
       String dvdfield = null;
        switch(fieldID){
          case 1: 
              dvdfield = io.readString("Please enter Dvd title");
            // currentDvd.setTitle(title);
              break; 
             case 2:  
                  dvdfield = io.readString("Please enter release date");
              //   currentDvd.setReleaseDate(releaseDate);
                 break;
             case 3:
                  dvdfield = io.readString("Please enter MPAA Rating");
              //   currentDvd.setMpaaRating(mpaaRating);
                 break;
             case 4:
                  dvdfield = io.readString("Please enter Director's Name");
              //   currentDvd.setDirector(director);
                 break;
             case 5:
                  dvdfield = io.readString("Please enter Studio");
             //    currentDvd.setStudio(studio);
                 break;
                       
             case 6:
                  dvdfield = io.readString("Please enter user comment");
              //   currentDvd.setuserComment(userComment);
                 break;
            
        }
    return dvdfield;
}

public void displayErrorMessage(String errorMsg) {
    io.print("=== ERROR ===");
    io.print(errorMsg);
}
}

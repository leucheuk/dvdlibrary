/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.dvdlibrary.controller;

import com.sherlock.dvdlibrary.dao.DvdLibraryDaoException;
//import com.sherlock.dvdlibrary.dao.DvdlibraryDaoFileImpl;
import com.sherlock.dvdlibrary.dao.DvdlibraryDao;
import com.sherlock.dvdlibrary.dto.Dvd;
//import com.sherlock.dvdlibrary.ui.UserIO;
//import com.sherlock.dvdlibrary.ui.UserIOConsoleImpl;
import com.sherlock.dvdlibrary.ui.DvdlibraryView;
import java.util.List;
/**
 *
 * @author Sherlock
 */
public class DvdlibraryController {
        private DvdlibraryView view;
        private DvdlibraryDao dao;
        
     //private DvdlibraryView view = new DvdlibraryView();
     //private DvdlibraryDao dao = new DvdlibraryDaoFileImpl();
   //  private UserIO io = new UserIOConsoleImpl();

     public DvdlibraryController(DvdlibraryDao dao, DvdlibraryView view) {
                this.dao = dao;
                this.view = view;
             }
     
     
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
                while (keepGoing) {

                    menuSelection = getMenuSelection();

                    switch (menuSelection) {
                        case 1:
                           // io.print("ADD A NEW DVD");
                            createDvd();
                            break;
                        case 2:
                           // io.print("REMOVE A DVD");
                            removeDvd();
                            break;
                        case 3:
                        //    io.print("EDIT DVD");
                            editDVD();
                            break;
                        case 4:
                            //io.print("LIST ALL DVDS");
                            listDvds();
                            break;
                        case 5:
                           //  io.print("DISPLAY DVD INFO.");
                            viewDvd();
                            break;
                         case 6:
                           //  io.print("SEARCH DVD");
                             viewDvd();
                            break;    
                        case 7:
                            keepGoing = false;
                            break;
                        default:
                             unknownCommand();
                    }

                }
                exitMessage();
            } catch (DvdLibraryDaoException e) {
             view.displayErrorMessage(e.getMessage());
             }
        }

    
     private int getMenuSelection() {
        return view.printMenuAndGetSelection();
     }
     
   private void createDvd() throws DvdLibraryDaoException  {
    view.displayCreateDvdBanner();
    Dvd newDvd = view.getNewDvdInfo();
    String newdvdtitle = newDvd.getTitle();
    if ((newdvdtitle.isEmpty() == false)
            &&(dao.getDvd(newdvdtitle) == null)
            ){ 
        dao.addDvd(newDvd);
        view.displayCreateSuccessBanner();
    } else 
        view.displayCreateUnsuccessBanner();
}
   
   private void listDvds() throws DvdLibraryDaoException  {
    view.displayDisplayAllBanner();
    List<Dvd> dvdList = dao.getAllDvds();
    view.displayDvdList(dvdList);
}
   
   private void viewDvd() throws DvdLibraryDaoException  {
    view.displayDisplayDvdBanner();
    String dvdtitle = view.getDvdTitleChoice();
    Dvd dvd = dao.getDvd(dvdtitle);
    view.displayDvd(dvd);
}
   
   private void removeDvd() throws DvdLibraryDaoException {
    Dvd removedDvd = null;
    view.displayRemoveDvdBanner();
    String title = view.getDvdTitleChoice();
    if (title.isEmpty() == false) {
        removedDvd = dao.removeDvd(title);
    }
    view.displayRemoveResult(removedDvd);
}
   
   private void editDVD() throws DvdLibraryDaoException {
            
       view.displayEditDvdBanner();
       int fieldID ;
       //String title = view.getDvdTitleChoice();
       
       Dvd editedDvd = dao.getDvd(view.getDvdTitleChoice());
       
       if (editedDvd != null){
         do{
             fieldID = view.getDvdFieldChoice();
             if (fieldID !=0){
              //String fieldContent = view.getDvdField(fieldID);
                editedDvd = dao.editDvd(editedDvd.getTitle(),fieldID,view.getDvdField(fieldID));
                view.displayEditResult(editedDvd);
              }
           } while(fieldID != 0);
       }
       else view.displayEditResult(null);
   }
   
   
   private void unknownCommand() {
    view.displayUnknownCommandBanner();
}

private void exitMessage() {
    view.displayExitBanner();
}
}

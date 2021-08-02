/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.dvdlibrary;

import com.sherlock.dvdlibrary.controller.DvdlibraryController;
import com.sherlock.dvdlibrary.dao.DvdlibraryDao;
import com.sherlock.dvdlibrary.dao.DvdlibraryDaoFileImpl;
import com.sherlock.dvdlibrary.ui.DvdlibraryView;
import com.sherlock.dvdlibrary.ui.UserIO;
import com.sherlock.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author Sherlock
 */
public class APP {
    public static void main(String[] args) {
    //    DvdlibraryController controller = new DvdlibraryController();
    
    UserIO myIo = new UserIOConsoleImpl();
    DvdlibraryView myView = new DvdlibraryView(myIo);
    DvdlibraryDao myDao = new DvdlibraryDaoFileImpl();
    DvdlibraryController controller = new DvdlibraryController(myDao, myView);
    controller.run();
    }   
    
}

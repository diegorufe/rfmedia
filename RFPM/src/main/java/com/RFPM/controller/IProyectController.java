package com.RFPM.controller;

import com.RFPM.dao.IProyectDao;
import com.RFPM.entities.Proyect;
import com.RFPM.service.IProyectService;
import com.RFRest.controller.IBaseController;

/**
 * 
 * @author diego
 *
 */
public interface IProyectController extends IBaseController<IProyectService, IProyectDao, Proyect, Integer> {

}

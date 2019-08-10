package com.RFPM.controller.impl;
/**
 * 
 * @author diego
 *
 */

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.RFPM.constants.IRFPMConstantsURL;
import com.RFPM.controller.IProyectController;
import com.RFPM.dao.IProyectDao;
import com.RFPM.entities.Proyect;
import com.RFPM.service.IProyectService;
import com.RFRest.beans.RequestResponse;
import com.RFRest.constants.IConstantsRest;
import com.RFRest.controller.impl.BaseControllerImpl;

@CrossOrigin(origins = IConstantsRest.REST_URL_CROSS_ORIGIN, maxAge = IConstantsRest.MAX_AGE_CROSS_ORIGIN)
@RestController
@RequestMapping(IRFPMConstantsURL.REST_URL_PROYECTS)
public class ProyectControllerImpl extends BaseControllerImpl<IProyectService, IProyectDao, Proyect, Integer>
		implements IProyectController {

	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	public ResponseEntity<?> findAll() {
		List<Proyect> proyects = this.getService().findAll();
		return ResponseEntity.ok(new RequestResponse(proyects, null));
	}

}

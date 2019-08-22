package jpa;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.RFData.beans.Filter;
import com.RFData.constants.EnumConditionFilter;
import com.RFData.constants.EnumOperatorFilter;
import com.RFERP.RFERPApplication;
import com.RFPM.entities.Proyect;
import com.RFPM.service.IProyectService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RFERPApplication.class)
public class TestJpa {

	@Autowired
	IProyectService proyectService;

	@Test
	public void testFindAll() {
		List<Filter> filters = new ArrayList<>();
		List<Filter> filtersBis = new ArrayList<>();
		List<Filter> filtersBisBis = new ArrayList<>();
		filtersBisBis.add(new Filter("", EnumOperatorFilter.OR.getValue(), EnumConditionFilter.IS_NOT_NULL.getValue(), "user.id", null));
		filtersBisBis.add(new Filter(2, EnumOperatorFilter.AND.getValue(),  EnumConditionFilter.EQUAL.getValue(), "user.id", null));
		filtersBis.add(new Filter("E", EnumConditionFilter.LIKE.getValue(), "code"));
		filtersBis.add(new Filter("EA", EnumOperatorFilter.AND.getValue(), EnumConditionFilter.LIKE.getValue(), "code", filtersBisBis));
		filters.add(new Filter(filtersBis));
		filters.add(new Filter("EA", EnumOperatorFilter.OR.getValue(), EnumConditionFilter.LIKE.getValue(),  "name", null));
		List<Proyect> proyects = this.proyectService.find(null, filters, null, null);
		
		if(proyects != null) {
			System.out.println(proyects);
		}
	}
}

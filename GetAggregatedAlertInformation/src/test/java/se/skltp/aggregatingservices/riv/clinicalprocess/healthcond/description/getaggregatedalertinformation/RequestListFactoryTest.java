package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregatedalertinformation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.Ignore;

import se.skltp.agp.riv.itintegration.engagementindex.findcontentresponder.v1.FindContentResponseType;
import se.skltp.agp.service.api.QueryObject;
import se.skltp.agp.service.api.RequestListFactory;

public class RequestListFactoryTest {
	
	private RequestListFactory testObject = new RequestListFactoryImpl();
	
	@Test
	public void isPartOf(){
		List<String> careUnitIdList = Arrays.asList("UNIT1","UNIT2");
		assertTrue(new RequestListFactoryImpl().isPartOf(careUnitIdList, "UNIT2"));
		assertTrue(new RequestListFactoryImpl().isPartOf(careUnitIdList, "UNIT1"));
		
		careUnitIdList = new ArrayList<String>();
		assertTrue(new RequestListFactoryImpl().isPartOf(careUnitIdList, "UNIT1"));
		
		careUnitIdList = null;
		assertTrue(new RequestListFactoryImpl().isPartOf(careUnitIdList, "UNIT1"));
	}
	
	@Test
	public void isNotPartOf(){
		List<String> careUnitIdList = Arrays.asList("UNIT1","UNIT2");
		assertFalse(new RequestListFactoryImpl().isPartOf(careUnitIdList, "UNIT3"));
		assertFalse(new RequestListFactoryImpl().isPartOf(careUnitIdList, null));
	}
	
}

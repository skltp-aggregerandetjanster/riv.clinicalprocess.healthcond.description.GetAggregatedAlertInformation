package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregatedalertinformation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import riv.clinicalprocess.healthcond.description.getalertinformationresponder.v2.GetAlertInformationType;
import riv.clinicalprocess.healthcond.description.v2.PersonIdType;
import se.skltp.agp.riv.itintegration.engagementindex.findcontentresponder.v1.FindContentResponseType;
import se.skltp.agp.riv.itintegration.engagementindex.v1.EngagementType;
import se.skltp.agp.service.api.QueryObject;

public class RequestListFactoryTest {
	
	private RequestListFactoryImpl testObject = new RequestListFactoryImpl();
	private static final GetAlertInformationType validRequest = new GetAlertInformationType();
	private static final GetAlertInformationType invalidRequest = new GetAlertInformationType();
	
	private static final String SUBJECT_OF_CARE = UUID.randomUUID().toString();
	private static final String SOURCE_SYSTEM_HSAID = UUID.randomUUID().toString();
	private static final String OTHER_SOURCE_SYSTEM_HSAID = UUID.randomUUID().toString();
	
	@BeforeClass
	public static void init() {
		final PersonIdType person = new PersonIdType();
		person.setId(SUBJECT_OF_CARE);
		validRequest.setPatientId(person);
		validRequest.setSourceSystemHSAId(SOURCE_SYSTEM_HSAID);
		invalidRequest.setPatientId(person);
		invalidRequest.setSourceSystemHSAId(OTHER_SOURCE_SYSTEM_HSAID);
	}
	
	@Test
	public void testCreateRequestList() {
		QueryObject validQo = Mockito.mock(QueryObject.class);
		Mockito.when(validQo.getExtraArg()).thenReturn(validRequest);
		QueryObject invalidQo = Mockito.mock(QueryObject.class);
		Mockito.when(invalidQo.getExtraArg()).thenReturn(invalidRequest);
		
		final FindContentResponseType findContent = new FindContentResponseType();
		final EngagementType eng = new EngagementType();
		eng.setLogicalAddress(SOURCE_SYSTEM_HSAID);
		eng.setSourceSystem(SOURCE_SYSTEM_HSAID);
		eng.setRegisteredResidentIdentification(SUBJECT_OF_CARE);
		findContent.getEngagement().add(eng);
		
		List<Object[]> validRequestList = testObject.createRequestList(validQo, findContent);
		List<Object[]> invalidRequestList = testObject.createRequestList(invalidQo, findContent);
		
		assertFalse(validRequestList.isEmpty());
		assertTrue(invalidRequestList.isEmpty());
	}
		
	@Test
	public void testIsPartOf() {
		assertTrue(new RequestListFactoryImpl().isPartOf("TEST", "TEST"));
		assertFalse(new RequestListFactoryImpl().isPartOf("TEST_1", "TEST_2"));
	}
	
}

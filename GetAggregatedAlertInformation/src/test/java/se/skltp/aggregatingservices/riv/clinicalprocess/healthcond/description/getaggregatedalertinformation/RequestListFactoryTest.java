package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregatedalertinformation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
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
	
    @Test
    public void testIsBetweenNulls() {
        Date fromRequestDate = null;
        Date toRequestDate = null;
        String mostRecentContentTimestamp = "";
        assertTrue (testObject.isBetween(fromRequestDate, toRequestDate, mostRecentContentTimestamp));
    }

    @Test
    public void testIsBetweenFalse1() {
        Date   fromRequestDate = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime("20130131").toDate();
        Date   toRequestDate   = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime("20130131").toDate();
        String mostRecentContentTimestamp                                          = "20140617153000";
        assertFalse (testObject.isBetween(fromRequestDate, toRequestDate, mostRecentContentTimestamp));
    }

    @Test
    public void testIsBetweenFalse2() {
        Date   fromRequestDate = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime("20130131").toDate();
        Date   toRequestDate   = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime("20130131").toDate();
        String mostRecentContentTimestamp                                          = "20130131095832";
        assertFalse (testObject.isBetween(fromRequestDate, toRequestDate, mostRecentContentTimestamp));
    }
    
    @Test
    public void testIsBetweenFalse3() {
        Date   fromRequestDate = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime("20130131").toDate();
        Date   toRequestDate   = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime("20130131").toDate();
        String mostRecentContentTimestamp                                          = "20130131095830";
        assertFalse (testObject.isBetween(fromRequestDate, toRequestDate, mostRecentContentTimestamp));
    }
    
    @Test
    public void testIsBetweenTrue1() {
        Date   fromRequestDate = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime("18880131").toDate();
        Date   toRequestDate   = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime("20990131").toDate();
        String mostRecentContentTimestamp                                          = "20130131095831";
        assertTrue (testObject.isBetween(fromRequestDate, toRequestDate, mostRecentContentTimestamp));
    }
    
    @Test
    public void testIsBetweenTrue2() {
        Date   fromRequestDate = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime("20130131").toDate();
        Date   toRequestDate   = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime("20130201").toDate();
        String mostRecentContentTimestamp                                          = "20130131095831";
        assertTrue (testObject.isBetween(fromRequestDate, toRequestDate, mostRecentContentTimestamp));
    }
    
    @Test
    public void testIsBetweenTrue3() {
        Date   fromRequestDate = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime("20110131").toDate();
        Date   toRequestDate   = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime("20150131").toDate();
        String mostRecentContentTimestamp                                          = "20130131095831";
        assertTrue (testObject.isBetween(fromRequestDate, toRequestDate, mostRecentContentTimestamp));
    }
}

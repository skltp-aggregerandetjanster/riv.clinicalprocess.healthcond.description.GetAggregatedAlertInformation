package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregatedalertinformation;

import static org.junit.Assert.*;

import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;
import org.soitoolkit.commons.mule.jaxb.JaxbUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import riv.clinicalprocess.healthcond.description.getalertinformationresponder.v2.GetAlertInformationResponseType;
import riv.clinicalprocess.healthcond.description.getalertinformationresponder.v2.GetAlertInformationType;
import riv.clinicalprocess.healthcond.description.getalertinformationresponder.v2.ObjectFactory;
import riv.clinicalprocess.healthcond.description.v2.PersonIdType;
import se.skltp.agp.riv.itintegration.engagementindex.findcontentresponder.v1.FindContentType;
import se.skltp.agp.service.api.QueryObjectFactory;


public class QueryObjectFactoryTest {

	private static final QueryObjectFactoryImpl testObject = new QueryObjectFactoryImpl();
	private static final ObjectFactory objFactory = new ObjectFactory();
	
	private static final String CATEGORIZATION = UUID.randomUUID().toString();
	private static final String SERVICE_DOMAIN = UUID.randomUUID().toString();
	private static final String SUBJECTOFCARE = UUID.randomUUID().toString();
	private static final String SOURCESYSTEMHSAID = UUID.randomUUID().toString();
	
	
	@BeforeClass
	public static void init() {
		testObject.setEiCategorization(CATEGORIZATION);
		testObject.setEiServiceDomain(SERVICE_DOMAIN);
	}
	
	@Test
	public void testQueryObjectFactory() throws Exception {
		final GetAlertInformationType type = new GetAlertInformationType();
		type.setSourceSystemHSAId(SOURCESYSTEMHSAID);
		final PersonIdType person = new PersonIdType();
		person.setId(SUBJECTOFCARE);
		type.setPatientId(person);
		
		final Node node = createNode(type);
		final FindContentType findContent = testObject.createQueryObject(node).getFindContent();

		assertEquals(CATEGORIZATION, findContent.getCategorization());
		assertEquals(SERVICE_DOMAIN, findContent.getServiceDomain());
		assertEquals(SUBJECTOFCARE, findContent.getRegisteredResidentIdentification());
		assertEquals(SOURCESYSTEMHSAID, findContent.getSourceSystem());
		assertNull(findContent.getBusinessObjectInstanceIdentifier());
		assertNull(findContent.getClinicalProcessInterestId());
		assertNull(findContent.getDataController());
		assertNull(findContent.getMostRecentContent());
		assertNull(findContent.getOwner());
	}
	
	private Node createNode(final GetAlertInformationType req) throws Exception {
		JAXBElement<GetAlertInformationType> jaxb = objFactory.createGetAlertInformation(req);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document node = db.newDocument();
		
		JAXBContext jc = JAXBContext.newInstance(GetAlertInformationType.class);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.marshal(jaxb, node);
		return node;
	}
}

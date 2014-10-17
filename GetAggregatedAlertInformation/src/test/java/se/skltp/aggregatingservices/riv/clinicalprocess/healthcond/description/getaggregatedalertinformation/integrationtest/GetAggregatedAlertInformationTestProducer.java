package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregatedalertinformation.integrationtest;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import riv.clinicalprocess.healthcond.description.getalertinformation.v2.rivtabp21.GetAlertInformationResponderInterface;
import riv.clinicalprocess.healthcond.description.getalertinformationresponder.v2.GetAlertInformationResponseType;
import riv.clinicalprocess.healthcond.description.getalertinformationresponder.v2.GetAlertInformationType;
import se.skltp.agp.test.producer.TestProducerDb;

@WebService(serviceName = "GetAlertInformationResponderService", portName = "GetAlertInformationResponderPort", targetNamespace = "urn:riv:clinicalprocess:healthcond:description:GetAlertInformation:2:rivtabp21", name = "GetAlertInformationInteraction")
public class GetAggregatedAlertInformationTestProducer implements GetAlertInformationResponderInterface {

	private static final Logger log = LoggerFactory.getLogger(GetAggregatedAlertInformationTestProducer.class);

	private TestProducerDb testDb;
	public void setTestDb(TestProducerDb testDb) {
		this.testDb = testDb;
	}

	public GetAlertInformationResponseType getAlertInformation(String logicalAddress, GetAlertInformationType request) {
		System.out.println("TESTPROD: " + testDb);
		Object response = testDb.processRequest(logicalAddress, request.getPatientId().getId());
		
		
		if(response == null) {
			return new GetAlertInformationResponseType();
		}
		
		return (GetAlertInformationResponseType) response;
		//return new GetAlertInformationResponseType();
	}
}
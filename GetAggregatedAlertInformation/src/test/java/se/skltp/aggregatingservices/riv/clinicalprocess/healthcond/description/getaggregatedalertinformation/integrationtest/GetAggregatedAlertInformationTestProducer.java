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
		GetAlertInformationResponseType response = null;


        // TODO: CHANGE GENERATED SAMPLE CODE - START
        if (1==1) throw new UnsupportedOperationException("Not yet implemented");
        /*

		log.info("### Virtual service for GetAlertInformation call the source system with logical address: {} and patientId: {}", logicalAddress, request.getSubjectOfCareId());

		response = (GetAlertInformationResponseType)testDb.processRequest(logicalAddress, request.getSubjectOfCareId());
        if (response == null) {
        	// Return an empty response object instead of null if nothing is found
        	response = new GetAlertInformationResponseType();
        }

		log.info("### Virtual service got {} booknings in the reply from the source system with logical address: {} and patientId: {}", new Object[] {response.getRequestActivity().size(), logicalAddress, request.getSubjectOfCareId()});

        */
        // TODO: CHANGE GENERATED SAMPLE CODE - END


		// We are done
        return response;
	}
}
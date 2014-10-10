package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregatedalertinformation.integrationtest;

import static se.skltp.agp.test.producer.TestProducerDb.TEST_RR_ID_ONE_HIT;

import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import riv.clinicalprocess.healthcond.description.getalertinformation.v2.rivtabp21.GetAlertInformationResponderInterface;
import riv.clinicalprocess.healthcond.description.getalertinformationresponder.v2.GetAlertInformationResponseType;
import riv.clinicalprocess.healthcond.description.getalertinformationresponder.v2.GetAlertInformationType;
import se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregatedalertinformation.GetAggregatedAlertInformationMuleServer;
import se.skltp.agp.test.consumer.AbstractTestConsumer;
import se.skltp.agp.test.consumer.SoapHeaderCxfInterceptor;
import se.skltp.agp.riv.interoperability.headers.v1.ProcessingStatusType;

public class GetAggregatedAlertInformationTestConsumer extends AbstractTestConsumer<GetAlertInformationResponderInterface> {

	private static final Logger log = LoggerFactory.getLogger(GetAggregatedAlertInformationTestConsumer.class);

	public static void main(String[] args) {
		String serviceAddress = GetAggregatedAlertInformationMuleServer.getAddress("SERVICE_INBOUND_URL");
		String personnummer = TEST_RR_ID_ONE_HIT;

		GetAggregatedAlertInformationTestConsumer consumer = new GetAggregatedAlertInformationTestConsumer(serviceAddress, SAMPLE_SENDER_ID, SAMPLE_ORIGINAL_CONSUMER_HSAID);
		Holder<GetAlertInformationResponseType> responseHolder = new Holder<GetAlertInformationResponseType>();
		Holder<ProcessingStatusType> processingStatusHolder = new Holder<ProcessingStatusType>();

		consumer.callService("logical-adress", personnummer, processingStatusHolder, responseHolder);


        // TODO: CHANGE GENERATED SAMPLE CODE - START
        if (1==1) throw new UnsupportedOperationException("Not yet implemented");
        /*

		log.info("Returned #timeslots = " + responseHolder.value.getRequestActivity().size());

		*/
        // TODO: CHANGE GENERATED SAMPLE CODE - END

	}

	public GetAggregatedAlertInformationTestConsumer(String serviceAddress, String senderId, String originalConsumerHsaId) {
	    
		// Setup a web service proxy for communication using HTTPS with Mutual Authentication
		super(GetAlertInformationResponderInterface.class, serviceAddress, senderId, originalConsumerHsaId);
	}

	public void callService(String logicalAddress, String registeredResidentId, Holder<ProcessingStatusType> processingStatusHolder, Holder<GetAlertInformationResponseType> responseHolder) {

		log.debug("Calling GetAlertInformation-soap-service with Registered Resident Id = {}", registeredResidentId);
		
		GetAlertInformationType request = new GetAlertInformationType();


        // TODO: CHANGE GENERATED SAMPLE CODE - START
        if (1==1) throw new UnsupportedOperationException("Not yet implemented");
        /*

		request.setSubjectOfCareId(registeredResidentId);

        */
        // TODO: CHANGE GENERATED SAMPLE CODE - END

		GetAlertInformationResponseType response = _service.getAlertInformation(logicalAddress, request);
		responseHolder.value = response;
		
		processingStatusHolder.value = SoapHeaderCxfInterceptor.getLastFoundProcessingStatus();
	}
}
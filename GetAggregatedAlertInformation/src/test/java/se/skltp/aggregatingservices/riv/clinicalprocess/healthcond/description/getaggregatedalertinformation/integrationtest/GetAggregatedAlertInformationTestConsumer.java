/**
 * Copyright (c) 2014 Inera AB, <http://inera.se/>
 *
 * This file is part of SKLTP.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregatedalertinformation.integrationtest;

import static se.skltp.agp.test.producer.TestProducerDb.TEST_RR_ID_ONE_HIT;

import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import riv.clinicalprocess.healthcond.description.getalertinformation.v2.rivtabp21.GetAlertInformationResponderInterface;
import riv.clinicalprocess.healthcond.description.getalertinformationresponder.v2.GetAlertInformationResponseType;
import riv.clinicalprocess.healthcond.description.getalertinformationresponder.v2.GetAlertInformationType;
import riv.clinicalprocess.healthcond.description.v2.PersonIdType;
import se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregatedalertinformation.GetAggregatedAlertInformationMuleServer;
import se.skltp.agp.test.consumer.AbstractTestConsumer;
import se.skltp.agp.test.consumer.SoapHeaderCxfInterceptor;
import se.skltp.agp.riv.interoperability.headers.v1.ProcessingStatusType;

public class GetAggregatedAlertInformationTestConsumer extends AbstractTestConsumer<GetAlertInformationResponderInterface> {

	private static final Logger log = LoggerFactory.getLogger(GetAggregatedAlertInformationTestConsumer.class);

	public static void main(String[] args) {
		String serviceAddress = GetAggregatedAlertInformationMuleServer.getAddress("SERVICE_INBOUND_URL");
		String personnummer = TEST_RR_ID_ONE_HIT;

		GetAggregatedAlertInformationTestConsumer consumer = new GetAggregatedAlertInformationTestConsumer(serviceAddress, SAMPLE_SENDER_ID, SAMPLE_ORIGINAL_CONSUMER_HSAID, SAMPLE_CORRELATION_ID);
		Holder<GetAlertInformationResponseType> responseHolder = new Holder<GetAlertInformationResponseType>();
		Holder<ProcessingStatusType> processingStatusHolder = new Holder<ProcessingStatusType>();

		consumer.callService("logical-adress", personnummer, processingStatusHolder, responseHolder);



		log.info("Returned #timeslots = " + responseHolder.value.getAlertInformation().size());

	}

	public GetAggregatedAlertInformationTestConsumer(String serviceAddress, String senderId, String originalConsumerHsaId, String correlationId) {
	    
		// Setup a web service proxy for communication using HTTPS with Mutual Authentication
		super(GetAlertInformationResponderInterface.class, serviceAddress, senderId, originalConsumerHsaId, correlationId);
	}

	public void callService(String logicalAddress, String registeredResidentId, Holder<ProcessingStatusType> processingStatusHolder, Holder<GetAlertInformationResponseType> responseHolder) {

		log.debug("Calling GetAlertInformation-soap-service with Registered Resident Id = {}", registeredResidentId);
		
		GetAlertInformationType request = new GetAlertInformationType();

		final PersonIdType personIdType = new PersonIdType();
		personIdType.setId(registeredResidentId);
		personIdType.setType("1.2.752.129.2.1.3.1");
		request.setPatientId(personIdType);

		GetAlertInformationResponseType response = _service.getAlertInformation(logicalAddress, request);
		responseHolder.value = response;
		
		processingStatusHolder.value = SoapHeaderCxfInterceptor.getLastFoundProcessingStatus();
	}
}
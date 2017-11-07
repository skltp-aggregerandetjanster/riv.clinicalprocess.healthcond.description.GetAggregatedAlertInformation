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
		Object response = testDb.processRequest(logicalAddress, request.getPatientId().getId());
		
		
		if(response == null) {
			return new GetAlertInformationResponseType();
		}
		
		return (GetAlertInformationResponseType) response;
	}
}
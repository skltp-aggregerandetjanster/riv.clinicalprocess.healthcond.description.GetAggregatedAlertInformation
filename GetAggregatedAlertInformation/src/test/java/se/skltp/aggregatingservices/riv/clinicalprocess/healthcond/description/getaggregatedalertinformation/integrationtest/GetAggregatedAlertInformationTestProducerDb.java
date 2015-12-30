package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregatedalertinformation.integrationtest;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soitoolkit.commons.mule.util.ThreadSafeSimpleDateFormat;

import riv.clinicalprocess.healthcond.description.getalertinformationresponder.v2.GetAlertInformationResponseType;
import riv.clinicalprocess.healthcond.description.v2.AlertInformationBodyType;
import riv.clinicalprocess.healthcond.description.v2.AlertInformationType;
import riv.clinicalprocess.healthcond.description.v2.CVType;
import riv.clinicalprocess.healthcond.description.v2.HealthcareProfessionalType;
import riv.clinicalprocess.healthcond.description.v2.HyperSensitivityType;
import riv.clinicalprocess.healthcond.description.v2.OrgUnitType;
import riv.clinicalprocess.healthcond.description.v2.PatientSummaryHeaderType;
import riv.clinicalprocess.healthcond.description.v2.PersonIdType;
import riv.clinicalprocess.healthcond.description.v2.TimePeriodType;
import se.skltp.agp.test.producer.TestProducerDb;

public class GetAggregatedAlertInformationTestProducerDb extends TestProducerDb {

	private static final Logger log = LoggerFactory
			.getLogger(GetAggregatedAlertInformationTestProducerDb.class);
	private static final ThreadSafeSimpleDateFormat df = new ThreadSafeSimpleDateFormat("yyyyMMddhhmmss");
	
	@Override
	public Object createResponse(Object... responseItems) {
		log.info("Creates a response with {} items", responseItems.length);
		GetAlertInformationResponseType response = new GetAlertInformationResponseType();
		for (int i = 0; i < responseItems.length; i++) {
			response.getAlertInformation().add((AlertInformationType) responseItems[i]);
		}
		return response;
	}

	@Override
	public Object createResponseItem(String logicalAddress,
			String registeredResidentId, String businessObjectId, String time) {
		if (log.isDebugEnabled()) {
			log.debug(
					"Created one response item for logical-address {}, registeredResidentId {} and businessObjectId {}",
					new Object[] { logicalAddress, registeredResidentId,
							businessObjectId });
		}
		final AlertInformationType alertInformation = new AlertInformationType();

		final PatientSummaryHeaderType header = new PatientSummaryHeaderType();
		header.setDocumentId(UUID.randomUUID().toString());
		header.setSourceSystemHSAId(logicalAddress);
		header.setDocumentTime(df.format(new Date()));
		
		final PersonIdType pp = new PersonIdType();
		pp.setId(registeredResidentId);
		pp.setType("1.2.752.129.2.1.3.1");
		header.setPatientId(pp);
		
		final HealthcareProfessionalType hp = new HealthcareProfessionalType();
		hp.setAuthorTime(df.format(new Date()));
		hp.setHealthcareProfessionalCareGiverHSAId(logicalAddress);
		
		final OrgUnitType ou = new OrgUnitType();
		ou.setOrgUnitAddress("Address");
		ou.setOrgUnitEmail("email@email.com");
		ou.setOrgUnitHSAId(logicalAddress);
		ou.setOrgUnitLocation("Location");
		ou.setOrgUnitName("Sjukhuset");
		ou.setOrgUnitTelecom("00-00000000");
		hp.setHealthcareProfessionalOrgUnit(ou);
		header.setAccountableHealthcareProfessional(hp);
		
		alertInformation.setAlertInformationHeader(header);
		
		final AlertInformationBodyType body = new AlertInformationBodyType();
		body.setValidityTimePeriod(generateTimePeriod());
		body.setTypeOfAlertInformation(generateCVType("OO=12 SE=12", "1.2.752.129.2.2.2.1"));
		body.setHypersensitivity(hyper());
		body.setAlertInformationComment("AlertComment");
		body.setObsoleteComment("ObsoleteComment");

		alertInformation.setAlertInformationBody(body);
		
		return alertInformation;
	}
	
	protected HyperSensitivityType hyper() {
		final HyperSensitivityType type = new HyperSensitivityType();
		type.setTypeOfHypersensitivity(generateCVType("Läkemedelsöverkännslighet", "1.2.4.5.6", "Läkemedelsöverkännslighet"));
		type.setDegreeOfSeverity(generateCVType("Livshotande", "1.2.752.129.2.2.3.3", "Livshotande"));
		type.setDegreeOfCertainty(generateCVType("Konstaterad", "1.2.752.129.2.2.2.3.3", "Konstaterad"));
		return type;
	}

	protected CVType generateCVType(final String code, final String codeSystem, final String displayName) {
		final CVType type = generateCVType(code, codeSystem);
		type.setDisplayName(displayName);
		return type;
	}
	
	protected CVType generateCVType(final String code, final String codeSystem) {
		final CVType cvType = new CVType();
		cvType.setCode(code);
		cvType.setCodeSystem(codeSystem);
		return cvType;
	}
	
	protected TimePeriodType generateTimePeriod() {
		final TimePeriodType type = new TimePeriodType();
		type.setEnd(df.format(new Date()));
		type.setStart(df.format(new Date()));
		return type;
	}
}
package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregatedalertinformation;

import lombok.extern.log4j.Log4j2;
import org.apache.cxf.message.MessageContentsList;
import org.springframework.stereotype.Service;
import riv.clinicalprocess.healthcond.description.getalertinformationresponder.v2.GetAlertInformationResponseType;
import riv.clinicalprocess.healthcond.description.getalertinformationresponder.v2.GetAlertInformationType;
import riv.clinicalprocess.healthcond.description.v2.AlertInformationBodyType;
import riv.clinicalprocess.healthcond.description.v2.AlertInformationType;
import riv.clinicalprocess.healthcond.description.v2.PatientSummaryHeaderType;
import riv.clinicalprocess.healthcond.description.v2.PersonIdType;
import se.skltp.aggregatingservices.data.TestDataGenerator;

@Log4j2
@Service
public class ServiceTestDataGenerator extends TestDataGenerator {

  @Override
  public String getPatientId(MessageContentsList messageContentsList) {
    GetAlertInformationType request = (GetAlertInformationType) messageContentsList.get(1);
    return request.getPatientId().getId();
  }

  @Override
  public Object createResponse(Object... responseItems) {
    log.info("Creating a response with {} items", responseItems.length);
    GetAlertInformationResponseType response = new GetAlertInformationResponseType();
    for (int i = 0; i < responseItems.length; i++) {
        response.getAlertInformation().add((AlertInformationType) responseItems[i]);
    }

    log.info("response.toString:" + response.toString());

    return response;
  }

  @Override
  public Object createResponseItem(String logicalAddress, String registeredResidentId,
      String businessObjectId, String time) {
    log.debug(
        "Created ResponseItem for logical-address {}, registeredResidentId {} and businessObjectId {}",
        new Object[]{logicalAddress, registeredResidentId, businessObjectId});


    final AlertInformationType resp = new AlertInformationType();

    resp.setAlertInformationBody(new AlertInformationBodyType());
    resp.setAlertInformationHeader(new PatientSummaryHeaderType());


    PersonIdType personIdType = new PersonIdType();
    personIdType.setId(registeredResidentId);
    resp.getAlertInformationHeader().setPatientId(personIdType);

    return resp;
  }

  public Object createRequest(String patientId, String sourceSystemHSAId) {
    GetAlertInformationType request = new GetAlertInformationType();
    request.setSourceSystemHSAId(sourceSystemHSAId);
    final PersonIdType person = new PersonIdType();
    person.setId(patientId);
    request.setPatientId(person);
    return request;
  }
}

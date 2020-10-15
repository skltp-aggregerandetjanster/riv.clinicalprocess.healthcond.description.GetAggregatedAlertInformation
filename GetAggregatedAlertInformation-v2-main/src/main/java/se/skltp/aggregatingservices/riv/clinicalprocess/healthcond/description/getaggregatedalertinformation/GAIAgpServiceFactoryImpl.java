package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregatedalertinformation;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import riv.clinicalprocess.healthcond.description.getalertinformationresponder.v2.GetAlertInformationResponseType;
import riv.clinicalprocess.healthcond.description.getalertinformationresponder.v2.GetAlertInformationType;
import se.skltp.aggregatingservices.AgServiceFactoryBase;

@Log4j2
public class GAIAgpServiceFactoryImpl extends
    AgServiceFactoryBase<GetAlertInformationType, GetAlertInformationResponseType> {

  @Override
  public String getPatientId(GetAlertInformationType queryObject) {
    return queryObject.getPatientId().getId();
  }

  @Override
  public String getSourceSystemHsaId(GetAlertInformationType queryObject) {
    return queryObject.getSourceSystemHSAId();
  }

  @Override
  public GetAlertInformationResponseType aggregateResponse(
      List<GetAlertInformationResponseType> aggregatedResponseList) {
    GetAlertInformationResponseType aggregatedResponse = new GetAlertInformationResponseType();
    for (GetAlertInformationResponseType response : aggregatedResponseList) {
      aggregatedResponse.getAlertInformation().addAll(response.getAlertInformation());
    }
    return aggregatedResponse;
  }
}


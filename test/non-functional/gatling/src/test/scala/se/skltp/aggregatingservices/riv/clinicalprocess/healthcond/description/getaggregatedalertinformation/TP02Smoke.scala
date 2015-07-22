package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregatedalertinformation

import se.skltp.agp.testnonfunctional.TP02SmokeAbstract

/**
 * smoke test VP:GetAggregatedAlertInformation.
 */
class TP02Smoke extends TP02SmokeAbstract with CommonParameters {
   // override skltp-box with qa
  if (baseUrl.startsWith("http://33.33.33.33")) {
      baseUrl = "http://ine-sit-app03.sth.basefarm.net:9015/GetAggregatedAlertInformation/service/v2"
  }
  setUp(setUpAbstract(serviceName, urn, responseElement, responseItem, baseUrl))
}

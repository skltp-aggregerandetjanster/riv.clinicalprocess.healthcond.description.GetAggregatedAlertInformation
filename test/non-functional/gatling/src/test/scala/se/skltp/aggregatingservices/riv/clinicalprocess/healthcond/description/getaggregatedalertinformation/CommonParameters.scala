package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregatedalertinformation

trait CommonParameters {
  val serviceName:String     = "AlertInformation"
  val urn:String             = "urn:riv:clinicalprocess:activityprescription:actoutcome:GetAlertInformationResponder:2"
  val responseElement:String = "GetAlertInformationResponse"
  val responseItem:String    = "medicationMedicalRecord"
  var baseUrl:String         = if (System.getProperty("baseUrl") != null && !System.getProperty("baseUrl").isEmpty()) {
                                   System.getProperty("baseUrl")
                               } else {
                                   "http://33.33.33.33:8081/GetAggregatedAlertInformation/service/v2"
                               }
}

package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregatedalertinformation;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import riv.clinicalprocess.healthcond.description.getalertinformation.v2.rivtabp21.GetAlertInformationResponderInterface;
import riv.clinicalprocess.healthcond.description.getalertinformation.v2.rivtabp21.GetAlertInformationResponderService;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "getaggregatedalertinformation.v2")
public class GAIAgpServiceConfiguration extends se.skltp.aggregatingservices.configuration.AgpServiceConfiguration {

public static final String SCHEMA_PATH = "/schemas/TD_CLINICALPROCESS_HEALTHCOND_DESCRIPTION_2.1/interactions/GetAlertInformationInteraction/GetAlertInformationInteraction_2.0_RIVTABP21.wsdl";

  public GAIAgpServiceConfiguration() {

    setServiceName("GetAggregatedAlertInformation-v2");
    setTargetNamespace("urn:riv:clinicalprocess:healthcond:description:GetAlertInformation:2:rivtabp21");
    //setReceiveTimeout(1000);

    // Set inbound defaults
    setInboundServiceURL("http://localhost:8081/GetAggregatedAlertInformation/service/v2");
    setInboundServiceWsdl(SCHEMA_PATH);
    setInboundServiceClass(GetAlertInformationResponderInterface.class.getName());
    setInboundPortName(GetAlertInformationResponderService.GetAlertInformationResponderPort.toString());

    // Set outbound defaults
    setOutboundServiceWsdl(SCHEMA_PATH);
    setOutboundServiceClass(GetAlertInformationResponderInterface.class.getName());
    setOutboundPortName(GetAlertInformationResponderService.GetAlertInformationResponderPort.toString());

    // FindContent
    setEiServiceDomain("riv:clinicalprocess:healthcond:description");
    setEiCategorization("upp");

    // TAK
    setTakContract("urn:riv:clinicalprocess:healthcond:description:GetAlertInformationResponder:2");

    // Set service factory
    setServiceFactoryClass(GAIAgpServiceFactoryImpl.class.getName());
    }


}

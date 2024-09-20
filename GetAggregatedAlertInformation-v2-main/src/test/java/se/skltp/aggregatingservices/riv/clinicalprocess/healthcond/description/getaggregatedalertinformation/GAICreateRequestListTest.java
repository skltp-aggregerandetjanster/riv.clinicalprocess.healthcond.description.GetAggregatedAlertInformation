package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregatedalertinformation;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import riv.clinicalprocess.healthcond.description.getalertinformationresponder.v2.GetAlertInformationResponseType;
import se.skltp.aggregatingservices.api.AgpServiceFactory;
import se.skltp.aggregatingservices.tests.CreateRequestListTest;


@ExtendWith(SpringExtension.class)
public class GAICreateRequestListTest extends CreateRequestListTest {

  private static GAIAgpServiceConfiguration configuration = new GAIAgpServiceConfiguration();
  private static AgpServiceFactory<GetAlertInformationResponseType> agpServiceFactory = new GAIAgpServiceFactoryImpl();
  private static ServiceTestDataGenerator testDataGenerator = new ServiceTestDataGenerator();


  public GAICreateRequestListTest() {
    super(testDataGenerator, agpServiceFactory, configuration);
  }
}
package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregatedalertinformation;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import riv.clinicalprocess.healthcond.description.getalertinformationresponder.v2.GetAlertInformationResponseType;
import se.skltp.aggregatingservices.api.AgpServiceFactory;
import se.skltp.aggregatingservices.tests.CreateAggregatedResponseTest;


@RunWith(SpringJUnit4ClassRunner.class)
public class GAICreateAggregatedResponseTest extends CreateAggregatedResponseTest {

  private static GAIAgpServiceConfiguration configuration = new GAIAgpServiceConfiguration();
  private static AgpServiceFactory<GetAlertInformationResponseType> agpServiceFactory = new GAIAgpServiceFactoryImpl();
  private static ServiceTestDataGenerator testDataGenerator = new ServiceTestDataGenerator();

  public GAICreateAggregatedResponseTest() {
      super(testDataGenerator, agpServiceFactory, configuration);
  }

  @Override
  public int getResponseSize(Object response) {
      GetAlertInformationResponseType responseType = (GetAlertInformationResponseType)response;
      return responseType.getAlertInformation().size();
  }
}
package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregatedalertinformation;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import riv.clinicalprocess.healthcond.description.getalertinformationresponder.v2.GetAlertInformationResponseType;
import se.skltp.aggregatingservices.api.AgpServiceFactory;
import se.skltp.aggregatingservices.tests.CreateFindContentTest;

@RunWith(SpringJUnit4ClassRunner.class)
public class GAICreateRequestListTest extends CreateFindContentTest {

  private static GAIAgpServiceConfiguration configuration = new GAIAgpServiceConfiguration();
  private static AgpServiceFactory<GetAlertInformationResponseType> agpServiceFactory = new GAIAgpServiceFactoryImpl();
  private static ServiceTestDataGenerator testDataGenerator = new ServiceTestDataGenerator();


  public GAICreateRequestListTest() {
    super(testDataGenerator, agpServiceFactory, configuration);
  }

  @BeforeClass
  public static void before() {
    configuration = new GAIAgpServiceConfiguration();
    agpServiceFactory = new GAIAgpServiceFactoryImpl();
    agpServiceFactory.setAgpServiceConfiguration(configuration);
  }
}
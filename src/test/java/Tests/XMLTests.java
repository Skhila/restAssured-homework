package Tests;

import XMLTask.Steps.ContinentsAPISteps;
import XMLTask.Steps.FindPersonSteps;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

import static XMLTask.Data.CommonData.*;

public class XMLTests {

    ContinentsAPISteps countryInfoAPISteps;
    FindPersonSteps findPersonSteps;

    @BeforeClass
    public void initiateSteps(){
        countryInfoAPISteps = new ContinentsAPISteps();
        findPersonSteps = new FindPersonSteps();
    }

    @Test
    public void runCountryTests(){
        Response responseFromContinents = countryInfoAPISteps.generateAPIResponse(apiURI);
        XmlPath continentsXmlPath = responseFromContinents.xmlPath();

        countryInfoAPISteps.validateCount(continentsXmlPath).validatesNames(continentsXmlPath).validateANValue(continentsXmlPath)
                .validateLastContinent(continentsXmlPath);
    }

    @Test
    public void runFindPersonSteps() throws URISyntaxException {
        Response responseFromFindPerson = findPersonSteps.sendPostRequest();
        XmlPath findPersonXmlPath = responseFromFindPerson.xmlPath();

        findPersonSteps.validateStreet(findPersonXmlPath).validateZip(findPersonXmlPath);
    }
}

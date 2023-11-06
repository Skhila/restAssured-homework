package XMLTask.Steps;

import io.qameta.allure.Step;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.util.List;

import static XMLTask.Data.CommonData.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class ContinentsAPISteps {

    @Step("Get Response")
    public Response generateAPIResponse(String url){
        Response response = given()
                .when().get(url)
                .then().statusCode(200)
                .extract().response();

        return response;
    }

    @Step("Validate sName Count")
    public ContinentsAPISteps validateCount(XmlPath responseXmlPath){
        assertThat(responseXmlPath.get("ArrayOftContinent.tContinent.sName.size()"), equalTo(sNameNodeCount));

        System.out.println("sName count validated successfully!");
        return this;
    }

    @Step("Validate sNames")
    public ContinentsAPISteps validatesNames(XmlPath responseXmlPath){
        List<String> actualContinentsNames = responseXmlPath.getList("ArrayOftContinent.tContinent.sName");
        for (String continentsName : actualContinentsNames) {
            assertThat(expectedContinents, hasItem(continentsName));

        }

        System.out.println("Continents names validated successfully!");
        return this;
    }

    @Step("Validate Antarctica Value")
    public ContinentsAPISteps validateANValue(XmlPath responseXmlPath){
        String nodeAN = responseXmlPath.get("ArrayOftContinent.tContinent.findAll { tContinent -> def sCode = tContinent.sCode; sCode=='AN' }.sName");
        assertThat(nodeAN, equalTo(anValue));

        System.out.println("Antarctica value validated successfully!");
        return this;
    }

    @Step("Validate Last Continent Name")
    public ContinentsAPISteps validateLastContinent(XmlPath responseXmlPath){
        String expectedLastContinent = expectedContinents.get(expectedContinents.size() - 1);
        String actualLastContinent = responseXmlPath.getString("ArrayOftContinent.tContinent[-1].sName");
        assertThat(actualLastContinent, equalTo(expectedLastContinent));

        System.out.println("Last Continent's name validated successfully!");
        return this;
    }
}

package XMLTask.Steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static XMLTask.Data.CommonData.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FindPersonSteps {
    @Step("Get Response From API")
    public Response sendPostRequest() throws URISyntaxException {
        URI findPersonXmlFile = FindPersonSteps.class.getClassLoader().getResource("findPerson.xml").toURI();

        Response response = given()
                                .contentType(ContentType.XML)
                                .header("SOAPAction", "https://tempuri.org/SOAP.Demo.FindPerson")
                                .header("Content-Type", "text/xml; charset=utf-8")
                                .body(new File(findPersonXmlFile))
                            .post(findPersonURI).then().statusCode(200).extract().response();

        return response;
    }

    @Step("Validate Home Street")
    public FindPersonSteps validateStreet(XmlPath responseXmlPath){
        String actualStreet = responseXmlPath.getString("Envelope.Body.FindPersonResponse.FindPersonResult.Home.Street");
        assertThat(actualStreet, equalTo(expectedHomeStreet));

        System.out.println("Home Street validated successfully!");
        return this;
    }

    @Step("Validate Office Zip Code")
    public FindPersonSteps validateZip(XmlPath responseXmlPath){
        String actualZipCode = responseXmlPath.getString("Envelope.Body.FindPersonResponse.FindPersonResult.Office.Zip");
        assertThat(actualZipCode, equalTo(expectedOfficeZip));

        System.out.println("Office Zip validated successfully!");
        return this;
    }
}

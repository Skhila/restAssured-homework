package SpecBuilders;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecs {

    public static ResponseSpecification createDeleteResponseSpec(){
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    }
}

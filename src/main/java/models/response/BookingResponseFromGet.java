package models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import models.request.BookingDates;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "firstname",
        "lastname",
        "totalprice",
        "depositpaid",
        "bookingdates",
        "additionalneeds"
})
public class BookingResponseFromGet {
    @JsonProperty("bookingdates")
    private BookingDates bookingDates;

    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("additionalneeds")
    private String additionalNeeds;

    @JsonProperty("depositpaid")
    private boolean depositPaid;

    @JsonProperty("totalprice")
    private int totalPrice;
}


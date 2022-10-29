package com.slack.stepDefs;

import com.slack.utils.CommonUtils;
import com.slack.utils.PayloadUtils;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ListMessagesStepDefs {

    Response response;
    @When("user list messages")
    public void userListMessages() {

        //https://slack.com/api/conversations.history?channel=C044QH2SS3U

        RestAssured.basePath = "api/conversations.history";

        response = RestAssured.given().accept(ContentType.JSON)
                .header("Authorization", CommonUtils.readProp("token"))
                .queryParam("channel", CommonUtils.readProp("channel"))
                .when().get().then().log().all().extract().response();

       // Map<String, List<Map<String,Object>>> map=response.as(new TypeRef<Map<String, List<Map<String, Object>>>>() {
      //  });
    }
    @Then("status code should be  {int}")
    public void statusCodeShouldBe(Integer expectedStatusCode) {

        Integer actualStatusCode=response.getStatusCode();
        Assert.assertEquals(expectedStatusCode,actualStatusCode);
    }
    @Then("user's message is in the list of messages")
    public void usersMessageIsInTheListOfMessages() {


        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        List<Map<String, Object>> list = (List<Map<String, Object>>) parsedResponse.get("messages");
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            if (map.get("text").equals("Samet:hello from API framework from scratch:)")) {
                System.out.println(map.get("text"));
            }
        }
    }
    }



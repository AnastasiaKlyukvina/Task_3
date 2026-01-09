package api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserApiClient {

    private static final String BASE_URL = "https://stellarburgers.education-services.ru/api";

    public static String createUser(String email, String password, String name) {
        String jsonBody = "{\"email\":\"" + email +
                "\",\"password\":\"" + password +
                "\",\"name\":\"" + name + "\"}";

        Response response = given()
                .header("Content-type", "application/json")
                .body(jsonBody)
                .post(BASE_URL + "/auth/register");

        return response.jsonPath().getString("accessToken");
    }

    public static void deleteUser(String token) {
        given()
                .header("Authorization", token)
                .delete(BASE_URL + "/auth/user");
    }
}
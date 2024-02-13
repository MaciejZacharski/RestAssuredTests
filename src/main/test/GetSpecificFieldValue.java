import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class GetSpecificFieldValue {


    @Test
    public void checkSpecificField() {

        Response response = RestAssured.get("http://localhost:3000/posts/{postId}", 1);
        String author = response.path("author");

        String author2 = RestAssured.get("http://localhost:3000/posts/{postId}", 1).path("author");

        Assert.assertEquals(author, "Jane");
        Assert.assertEquals(author2, "Jane");

    }
    @Test
    public void checkSpecificFieldJsonPath() {

        Response response = RestAssured.get("http://localhost:3000/posts/{postId}", 1);
        JsonPath jsonPath = new JsonPath(response.asString());
        String author = jsonPath.get("author");

        Assert.assertEquals(author, "Jane");

        String stringResponse = RestAssured.get("http://localhost:3000/posts/{postId}", 1).asString();
        String author2 = JsonPath.from(stringResponse).get("author");
        Assert.assertEquals(author2, "Jane");

    }
}

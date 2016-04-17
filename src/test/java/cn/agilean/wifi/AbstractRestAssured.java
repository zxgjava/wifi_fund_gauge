package cn.agilean.wifi;

import static com.jayway.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.specification.RequestSpecification;
/**
 * 
 * @author zxg(Walt)
 *
 */
public class AbstractRestAssured {
	private static final Logger logger = LoggerFactory.getLogger(AbstractRestAssured.class);
	
	protected JsonPath showJsonPath (String url, String method, Map<String, String> headers, Map<String, String> cookies, Object body) {
		return wifiRestAssured(url, method, headers, cookies, body).extract().jsonPath();
	}
	protected JsonPath showJsonPath (String url, String method, Object body) {
		return wifiRestAssured(url, method, body).extract().jsonPath();
	}
	protected ValidatableResponse wifiRestAssured(String url, String method, Map<String, String> headers, Map<String, String> cookies, Object body) {
		ValidatableResponse vr = null;
		RequestSpecification rs = given();
		if (headers != null && headers.size() > 0) {
			rs = rs.headers(headers);
		}
		if (cookies != null && cookies.size() > 0) {
			rs = rs.cookies(cookies);
		}
		if (body != null) {
			rs = rs.body(body);
		}
		switch (method) {
			case "GET": {
				vr = rs.when().get(url).then();
				break;
			}
			case "POST": {
				vr = rs.when().post(url).then();
				break;
			}
			case "PUT": {
				vr = rs.when().put(url).then();
				break;
			}
			case "DELETE": {
				vr = rs.when().delete(url).then();
				break;
			}
			case "PATCH": {
				vr = rs.when().patch(url).then();
				break;
			}
			case "HEAD": {
				vr = rs.when().head(url).then();
				break;
			}
			case "OPTION": {
				vr = rs.when().options(url).then();
				break;
			}
			default: {
				logger.error("Unknown method type:{};", method);
			}
		}
		return vr;
	}
	protected ValidatableResponse wifiRestAssured(String url, String method, Object body) {
		ValidatableResponse vr = null;
		RequestSpecification rs = given();
		if (body != null) {
			rs.body(body);
		}
		switch (method) {
			case "GET": {
				vr = rs.when().get(url).then();
				break;
			}
			case "POST": {
				vr = rs.when().post(url).then();
				break;
			}
			case "PUT": {
				vr = rs.when().put(url).then();
				break;
			}
			case "DELETE": {
				vr = rs.when().delete(url).then();
				break;
			}
			case "PATCH": {
				vr = rs.when().patch(url).then();
				break;
			}
			case "HEAD": {
				vr = rs.when().head(url).then();
				break;
			}
			case "OPTION": {
				vr = rs.when().options(url).then();
				break;
			}
			default: {
				logger.error("Unknown method type:{};", method);
			}
		}
		return vr;
	}
	
	protected ValidatableResponse post(String url, Object body) {
		return given().header("foo", "bar").and()
				.contentType("application/json;charset=utf-8").body(body)
				.when().post(url).then();
	}

	protected ValidatableResponse put(String url, Object body) {
		return given().header("foo", "bar")
				.contentType("application/json;charset=utf-8").body(body)
				.when().put(url).then();
	}

	protected ValidatableResponse delete(String url) {
		return given().header("foo", "bar")
				.contentType("application/json;charset=utf-8").when()
				.delete(url).then();
	}

	protected JsonPath postAndReturnJson(String url, Object body) {
		ValidatableResponse response = post(url, body);
		return response.extract().jsonPath();
	}

	protected ValidatableResponse postTextAndReturnJson(String url, String body) {
		return given().header("foo", "bar").and()
				.contentType("text/plain;charset=utf-8").body(body).when()
				.post(url).then();
	}

	protected JsonPath putAndReturnJson(String url, Object body) {
		ValidatableResponse response = put(url, body);
		return response.extract().jsonPath();
	}

	protected JsonPath deleteAndReturnJson(String url) {
		ValidatableResponse response = delete(url);
		return response.extract().jsonPath();
	}

	protected ValidatableResponse getFrom(String url) {
		return given().header("foo", "bar").when().get(url).then();
	}

	protected JsonPath getAsJson(String url) {
		return getFrom(url).extract().body().jsonPath();
	}

	protected String getAsString(String url) {
		return getFrom(url).extract().body().asString();
	}
	
	protected HashMap<String, String> setCookies(String... strArray) {
		List<String> data = new ArrayList<String>();
		for (String str : strArray) {
			data.add(str != null ? str : "");
		}
		HashMap<String, String> setcookies = new HashMap<String, String>();
		if (data.size() > 0) {
			setcookies.put(data.get(0), data.get(1));
			for (int i = 0; i < data.size() - 1; i++) {
				if (i > 1 && i % 2 == 0) {
					setcookies.put(data.get(i), data.get(i + 1));
				}
			}
		}
		return setcookies;
	}
	
	protected HashMap<String, String> setHearders(String... strArray) {
		List<String> data = new ArrayList<String>();
		for (String str : strArray) {
			data.add(str != null ? str : "");
		}
		HashMap<String, String> sethearders = new HashMap<String, String>();
		sethearders.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		sethearders.put("Content-Type", "application/json;charset=UTF-8");
		sethearders.put("User-Agent", "Jakarta Commons-HttpClient/3.1");
		if (data.size() > 0) {
			sethearders.put(data.get(0), data.get(1));
			for (int i = 0; i < data.size() - 1; i++) {
				if (i > 1 && i % 2 == 0) {
					sethearders.put(data.get(i), data.get(i + 1));
				}
			}
		}
		return sethearders;
	}
	
	
}

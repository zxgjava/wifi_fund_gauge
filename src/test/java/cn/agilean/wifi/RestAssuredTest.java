package cn.agilean.wifi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cn.agilean.util.DataDriveTool;

import com.alibaba.fastjson.JSONObject;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.path.json.JsonPath;

import static com.jayway.restassured.RestAssured.given;
/**
 * 
 * @author zxg(Walt)
 *	PS:TestNG、登录认证form方式
 *		
 *	
 */
public class RestAssuredTest extends AbstractRestAssured{
	private static final Logger logger = LoggerFactory.getLogger(RestAssuredTest.class);
	private JSONObject testDataStore;

	@BeforeClass
	public void beforeClass() {
		logger.info("执行TestSuite：{};", this.getClass().getName());
		
		RestAssured.baseURI = "http://mock-api.com/";
		RestAssured.port = 80;
		RestAssured.basePath = "/FPfXxIeuuSQV0oJq.mock";
		RestAssured.rootPath = "";
		testDataStore = DataDriveTool.showJsonStoreByClassName(this.getClass().getSimpleName());
	}

	@Test
	public void should_return_json_str_and_check_code_name_by_get() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info("执行TestCase:{};", methodName);
		logger.info("jsonStr:{};", testDataStore.getString(methodName));
		Reporter.log("执行TestCase:" + methodName);
		Reporter.log("jsonStr:" + testDataStore.getString(methodName));
		JsonPath jsonStr = getAsJson("/rest/json/001");
		Assert.assertEquals(jsonStr.getString("code"), "001");
		Assert.assertEquals(jsonStr.getString("name"), "001_name");
	}
	
	@Test
	public void should_return_json_str_and_check_code_name_by_get_wifi() {
		
		JsonPath jsonStr = wifiRestAssured("/rest/json/001", "GET", null, null, null).extract().jsonPath();
		Assert.assertEquals(jsonStr.getString("code"), "001");
		Assert.assertEquals(jsonStr.getString("name"), "001_name");
	}
	
	@Test
	public void should_return_json_str_and_check_code_name_by_post() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info("执行TestCase:{};", methodName);
		logger.info("jsonStr:{};", testDataStore.getString(methodName));
		
		JSONObject json = new JSONObject();
		json.put("code", "002");
		JsonPath jsonStr = postAndReturnJson("/rest/json/002", json);
		Assert.assertEquals(jsonStr.getString("code"), "002");
		Assert.assertEquals(jsonStr.getString("name"), "002_name");
	}

	@Test
	public void should_return_json_str_and_check_code_name_by_put() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info("执行TestCase:{};", methodName);
		logger.info("jsonStr:{};", testDataStore.getString(methodName));
		
		JSONObject json = new JSONObject();
		json.put("code", "003");
		json.put("name", "003_name");
		JsonPath jsonStr = putAndReturnJson("/rest/json/003", json);
		Assert.assertEquals(jsonStr.getString("code"), "003");
		Assert.assertEquals(jsonStr.getString("name"), "003_name");
	}

	@Test
	public void should_return_json_str_and_check_result_by_delete() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info("执行TestCase:{};", methodName);
		JSONObject jsonObj = DataDriveTool.showJsonStoreByClassAndMethod(this.getClass().getSimpleName(), methodName);
		logger.info("jsonStr:{};", testDataStore.getString(methodName));
		logger.info("jsonObj.username:{};jsonObj.password:{}", jsonObj.getString("username"), jsonObj.getString("password"));
		
		JsonPath jsonStr = deleteAndReturnJson("/rest/json/004");
		Assert.assertEquals(jsonStr.getString("result"), "delete success");
	}
	
	@Test
	public void should_return_json_str_and_check_result_by_get_with_headers() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info("执行TestCase:{};", methodName);
		JSONObject jsonObj = DataDriveTool.showJsonStoreByClassAndMethod(this.getClass().getSimpleName(), methodName);
		logger.info("jsonStr:{};", testDataStore.getString(methodName));
		logger.info("jsonObj.header1:{};jsonObj.header2:{}", jsonObj.getString("header1"), jsonObj.getString("header2"));
		
		JsonPath jsonStr = wifiRestAssured("/rest/json/005", "GET", setHearders("header1", jsonObj.getString("header1"), "header2", jsonObj.getString("header2")), null, null).extract().jsonPath();
		Assert.assertEquals(jsonStr.getString("code"), "005");
		Assert.assertEquals(jsonStr.getString("name"), "005_name");
	}

	@Test
	public void should_return_status_200() {
		FormAuthConfig formConfig = new FormAuthConfig("http://mock-api.com/login", "username",
				"password");
		given().contentType("application/json;charset=utf-8")
				.auth()
				.form("admin", "admin", formConfig.withLoggingEnabled()).when()
				.get("http://mock-api.com/mockers/147").then().assertThat().statusCode(401);
	}
	
//	@Test
//	public void should_return_mocker_json() {
//		JSONObject json = new JSONObject();
//		json.put("name", "mocker_01");
//		json.put("desc", "desc");
//		json.put("type", "Private");
//		json.put("host", "mock-api.com");
//		FormAuthConfig formConfig = new FormAuthConfig("http://mock-api.com/login", "username",
//				"password");
//		given().contentType("application/json;charset=utf-8")
//				.auth()
//				.form("admin", "admin", formConfig.withLoggingEnabled()).body(json.toJSONString()).when()
//				.post("http://mock-api.com/mockers").then().assertThat().statusCode(200);
//	}

	@Test
	public void should_return_status_401() {
		given().contentType("application/json;charset=utf-8")
				.when()
				.get("http://mock-api.com/mockers/147").then().assertThat().statusCode(401);
	}
	
	@Parameters({ "header1", "header2" })
	@Test
	public void should_return_json_str_and_check_result_by_get_with_headers_param(String header1, String header2) {
		JsonPath jsonStr = wifiRestAssured("/rest/json/005", "GET", setHearders("header1", header1, "header2", header2), null, null).extract().jsonPath();
		Assert.assertEquals(jsonStr.getString("code"), "005");
		Assert.assertEquals(jsonStr.getString("name"), "005_name");
	}
}

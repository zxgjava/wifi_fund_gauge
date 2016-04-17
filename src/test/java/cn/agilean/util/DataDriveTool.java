package cn.agilean.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
/**
 * 
 * @author zxg(Walt)
 *
 */
public class DataDriveTool {
	private static final Logger logger = LoggerFactory.getLogger(DataDriveTool.class);

	public static JSONObject showJsonStoreByClassName(String className) {
		String jsonStr = convertStreamToString(DataDriveTool.class.getResourceAsStream(File.separator + className + ".json"));
		jsonStr = jsonStr.replaceAll("/n", "");
		logger.info("showJsonStoreByClassName--className:{};jsonStr:{}", className, jsonStr);
		if (JsonUtils.isGoodJson(jsonStr)) {
			return JSONObject.parseObject(jsonStr);
		} else {
			return new JSONObject();
		}
	}
	
	public static JSONObject showJsonStoreByClassAndMethod(String className, String methodName) {
		JSONObject jsonObj = showJsonStoreByClassName(className);
		logger.info("showJsonStoreByClassAndMethod--className:{};methodName:{};jsonStr:{}", className, methodName, jsonObj.getString(methodName));
		return JSONObject.parseObject(jsonObj.getString(methodName));
	}
	
	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "/n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}
}

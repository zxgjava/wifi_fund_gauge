package cn.agilean.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class JsonUtils {
	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	public static boolean isBadJson(String json) {
		return !isGoodJson(json);
	}

	public static boolean isGoodJson(String json) {
		if (StringUtils.isBlank(json)) {
			return false;
		}
		try {
			new JsonParser().parse(json);
			return true;
		} catch (JsonParseException e) {
			logger.error("Bad json format: {};", json);
			return false;
		}
	}
}
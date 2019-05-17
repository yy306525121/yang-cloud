package cn.codeyang.common.utils;

import java.util.HashMap;
import java.util.Map;

import static cn.codeyang.common.utils.FileUtils.generateFileName;

/**
 * 分块上传工具类
 *
 * @author akafra
 */
public class UploadUtils {
	/**
	 * 内部类记录上传文件信息
	 */
	private static class Value {
		/**
		 * 随机文件名
		 */
		String name;
		/**
		 * 存储分块上传的情况， 已上传的设置为true
		 */
		boolean[] status;

		Value(int n) {
			this.name = generateFileName();
			this.status = new boolean[n];
		}
	}

	/**
	 * 用来存储分块上传信息
	 * key为文件唯一标识， 由前端发送， 这里采用md5
	 */
	private static Map<String, Value> chunkMap = new HashMap<>();

	/**
	 * 判断文件所有分块是否已上传完毕
	 *
	 * @param key
	 * @return
	 */
	public static boolean isUploaded(String key) {
		if (isExitKey(key)) {
			for (boolean success : chunkMap.get(key).status) {
				if (!success) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 判断文件是否有分块已上传
	 *
	 * @param key
	 * @return
	 */
	private static boolean isExitKey(String key) {
		return chunkMap.containsKey(key);
	}

	/**
	 * 为文件添加上传分块记录
	 *
	 * @param key
	 * @param chunk
	 */
	public static void addChunk(String key, int chunk) {
		chunkMap.get(key).status[chunk] = true;
	}

	/**
	 * 从map中删除记录
	 *
	 * @param key
	 */
	public static void removeKey(String key) {
		if (isExitKey(key)) {
			chunkMap.remove(key);
		}
	}

	public static String getFileName(String key, int chunks) {
		if (!isExitKey(key)) {
			synchronized (UploadUtils.class) {
				if (!isExitKey(key)) {
					chunkMap.put(key, new Value(chunks));
				}
			}
		}

		return chunkMap.get(key).name;
	}
}

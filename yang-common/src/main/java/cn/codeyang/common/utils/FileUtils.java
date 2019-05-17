package cn.codeyang.common.utils;

import ch.qos.logback.core.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 文件操作工具类
 *
 * @author akafra
 */
@Slf4j
public class FileUtils {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 写入文件
	 *
	 * @param target
	 * @param src
	 * @throws IOException
	 */
	public static void write(String target, InputStream src) throws IOException {
		FileOutputStream os = new FileOutputStream(target);
		byte[] buf = new byte[1024];
		int len;
		while (-1 != (len = src.read(buf))) {
			os.write(buf, 0, len);
		}

		os.flush();
		os.close();
	}

	/**
	 * @param target
	 * @param targetSize 文件大小
	 * @param src
	 * @param srcSize
	 * @param chunks     总分块数
	 * @param chunk      分块号
	 * @throws IOException
	 */
	public static void writeWithBlock(String target, Long totalSize, InputStream src, Long currentSize, Integer chunks, Integer chunk) throws IOException {
		RandomAccessFile randomAccessFile = new RandomAccessFile(target, "rw");
		randomAccessFile.setLength(totalSize);

		if (chunk.equals(chunks - 1) && chunk != 0) {
			log.info("写入结尾： {}", chunk * (totalSize - currentSize) / chunk);
			randomAccessFile.seek(totalSize);
		} else {
			log.info("写入中间： {}", chunk * currentSize);
			randomAccessFile.seek(chunk * currentSize);
		}

		byte[] buf = new byte[1024];
		int len;
		while (-1 != (len = src.read(buf))) {
			randomAccessFile.write(buf, 0, len);
		}
		randomAccessFile.close();
	}

	public static String generateFileName(){
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 计算文件保存的路径
	 * @TODO 这里存在并发问题
	 * @param contentType 文件类型
	 * @param extName 文件扩展名
	 * @return
	 */
	public static String generateFilePath(String contentType, String extName){
		return File.separator + sdf.format(new Date()) + File.separator + contentType + File.separator;
	}
}

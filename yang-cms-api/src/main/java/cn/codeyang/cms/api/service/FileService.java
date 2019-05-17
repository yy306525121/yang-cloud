package cn.codeyang.cms.api.service;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件上传服务
 *
 * @author akafra
 */
public interface FileService {

	/**
	 * 普通文件上传
	 * @param name 文件名
	 * @param md5 文件md5
	 * @param src 文件流
	 */
	void upload(String name, String contentType, String md5, Long size, InputStream src) throws IOException;

	/**
	 * 分块上传文件
	 * @param name 文件名
	 * @param md5 文件md5
	 * @param size 源文件大小
	 * @param chunks 总块数
	 * @param chunk 块号
	 * @param src 当前上传块的流
	 * @param srcSize 当前上传块的大小
	 * @throws IOException
	 */
	void uploadWithBlock(String name, String contentType, String md5, Long size, Integer chunks, Integer chunk, InputStream src, Long srcSize) throws IOException;

	/**
	 * 判断文件是否已上传
	 * @param md5 文件的md5
	 * @return
	 */
	boolean checkMd5(String md5);


}

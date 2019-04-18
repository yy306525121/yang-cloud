package cn.codeyang.cms.web.rest;

import cn.codeyang.common.http.utils.HttpResult;
import cn.hutool.core.io.FileUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

/**
 * @author yangzhongyang
 */
@Api(value = "文件上传", description = "文件上传")
@RestController()
@RequestMapping("/api/upload")
@Slf4j
public class UploadController {

	@Value("${file.upload-folder}")
	private String uploadFolder;

	@PostMapping("")
	public ResponseEntity<HttpResult> upload(MultipartFile file){
		log.debug("Rest Upload file : {}", file);

		if (Objects.isNull(file) || file.isEmpty()) {
			log.error("文件不能为空");
			return ResponseEntity.badRequest().build();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			byte[] bytes = file.getBytes();
			//原始文件名
			String originalFilename = file.getOriginalFilename();
			//原始文件大小
			long size = file.getSize();
			String contentType = file.getContentType();
			String filePath = File.separator + sdf.format(new Date()) + File.separator +
					contentType + File.separator + UUID.randomUUID().toString().replace("-", "") +
					"." + FileUtil.extName(originalFilename);

			Path path = Paths.get(uploadFolder + filePath);
			if (!Files.isWritable(path)) {
				Files.createDirectories(path.getParent());
			}

			Files.write(path, bytes);
			log.debug("文件写入成功");

			HttpResult result = HttpResult.ok("上传成功");
			HashMap<String, Object> dataMap = new HashMap<>(4);
			dataMap.put("name", originalFilename.substring(0, originalFilename.indexOf(".")));
			dataMap.put("path", filePath);
			dataMap.put("size", size);
			dataMap.put("type", contentType);
			result.setData(dataMap);
			return ResponseEntity.ok(result);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.ok(HttpResult.fail("文件上传失败"));
		}
	}
}

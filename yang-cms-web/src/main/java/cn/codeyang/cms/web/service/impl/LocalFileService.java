package cn.codeyang.cms.web.service.impl;

import cn.codeyang.cms.api.entity.Resource;
import cn.codeyang.cms.api.service.FileService;
import cn.codeyang.cms.api.service.ResourceService;
import cn.codeyang.cms.web.mapper.ResourceMapper;
import cn.codeyang.common.utils.FileUtils;
import cn.codeyang.common.utils.UploadUtils;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author akafra
 */
@Service
@Slf4j
public class LocalFileService implements FileService {
	@Value("${file.upload-folder}")
	private String uploadFolder;

	@Autowired
	private ResourceService resourceService;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


	@Override
	public void upload(String name, String contentType, String md5, Long size, InputStream src) throws IOException {
		String path = FileUtils.generateFilePath(contentType, FileUtil.extName(name));
		FileUtils.write(uploadFolder + path, src);
		Resource resource = new Resource();
		resource.setName(name);
		resource.setMd5(md5);
		resource.setPath(path);
		resource.setSize(size);

		resourceService.save(resource);
	}

	@Override
	public void uploadWithBlock(String name, String contentType, String md5, Long size, Integer chunks, Integer chunk, InputStream src, Long srcSize) throws IOException {
		String fileName = UploadUtils.getFileName(md5, chunks);

		String path = FileUtils.generateFilePath(contentType, FileUtil.extName(name));

		FileUtils.writeWithBlock(uploadFolder + path + fileName + "." + FileUtil.extName(name), size, src, srcSize, chunks, chunk);
		UploadUtils.addChunk(md5, chunk);
		if (UploadUtils.isUploaded(md5)) {
			log.info("上传完毕");
			UploadUtils.removeKey(md5);
			Resource resource = new Resource();
			resource.setName(name);
			resource.setMd5(md5);
			resource.setPath(fileName);
			resource.setSize(size);
			resource.setType(contentType);
			resourceService.save(resource);
		}
	}

	@Override
	public boolean checkMd5(String md5) {
		Wrapper<Resource> queryWrapper = new QueryWrapper<>();
		queryWrapper.getEntity().setMd5(md5);
		Resource resource = resourceService.getOne(queryWrapper);
		return resource == null;
	}
}

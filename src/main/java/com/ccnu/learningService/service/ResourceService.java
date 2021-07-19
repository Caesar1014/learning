package com.ccnu.learningService.service;

import com.ccnu.learningService.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 资源 服务类
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
public interface ResourceService extends IService<Resource> {

    void saveResource(String content, String courseId);

    List<Resource> getResourceByCourseId(String courseId);
}

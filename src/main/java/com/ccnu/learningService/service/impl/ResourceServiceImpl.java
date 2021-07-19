package com.ccnu.learningService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccnu.learningService.entity.Resource;
import com.ccnu.learningService.mapper.ResourceMapper;
import com.ccnu.learningService.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Override
    public void saveResource(String content, String courseId) {
        Resource resource = new Resource();
        resource.setContent(content);
        resource.setCourseId(courseId);
        baseMapper.insert(resource);
    }

    @Override
    public List<Resource> getResourceByCourseId(String courseId) {
        return list(new QueryWrapper<Resource>().eq("course_id", courseId));
    }
}

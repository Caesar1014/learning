package com.ccnu.learningService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccnu.learningService.entity.Text;
import com.ccnu.learningService.mapper.TextMapper;
import com.ccnu.learningService.service.TextService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 短文本 服务实现类
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class TextServiceImpl extends ServiceImpl<TextMapper, Text> implements TextService {

    @Override
    public String getTextByTextId(String textId) {
        QueryWrapper<Text> wrapper = new QueryWrapper<>();
        wrapper.eq("id", textId);
        Text text = baseMapper.selectOne(wrapper);

        if (StringUtils.isEmpty(text)) {
            return null;
        } else {
            return text.getContent();
        }
    }
}

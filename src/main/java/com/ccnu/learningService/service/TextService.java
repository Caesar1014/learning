package com.ccnu.learningService.service;

import com.ccnu.learningService.entity.Text;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 短文本 服务类
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
public interface TextService extends IService<Text> {

    String getTextByTextId(String textId);
}

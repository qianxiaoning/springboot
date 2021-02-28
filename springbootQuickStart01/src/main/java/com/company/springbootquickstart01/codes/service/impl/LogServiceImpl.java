package com.company.springbootquickstart01.codes.service.impl;

import com.company.springbootquickstart01.codes.entity.Log;
import com.company.springbootquickstart01.codes.mapper.LogMapper;
import com.company.springbootquickstart01.codes.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author Shon Qian
 * @since 2021-02-28
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}

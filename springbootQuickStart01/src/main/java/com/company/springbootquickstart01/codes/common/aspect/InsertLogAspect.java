package com.company.springbootquickstart01.codes.common.aspect;

import com.company.springbootquickstart01.codes.common.annotation.InsertLog;
import com.company.springbootquickstart01.codes.common.util.IPUtil;
import com.company.springbootquickstart01.codes.common.util.ObjectMapperUtil;
import com.company.springbootquickstart01.codes.common.util.ThreadLocalUtil;
import com.company.springbootquickstart01.codes.common.vo.JsonResult;
import com.company.springbootquickstart01.codes.entity.Log;
import com.company.springbootquickstart01.codes.mapper.LogMapper;
import com.company.springbootquickstart01.libs.loginRegisterProcess.normalWay.LoginVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//切面
@Aspect
@Component
public class InsertLogAspect {
    @Autowired
    private LogMapper logMapper;

    //切点
    @Pointcut("@annotation(com.company.springbootquickstart01.codes.common.annotation.InsertLog)")
    public void logPointCut() {}
    //通知
    @Around("logPointCut()")
    public Object aroundAdvice(ProceedingJoinPoint jp)throws Throwable{
        //调用下一次切面或目标方法
        JsonResult result = (JsonResult)jp.proceed();
        if(result.getCode() == 200){
            //插入日志
            insertLog(jp,result);
        }
        return result;
    }

    private void insertLog(ProceedingJoinPoint jp, JsonResult result) throws NoSuchMethodException {
        Class<?> targetCls = jp.getTarget().getClass();
        String targetClsName = targetCls.getName();
        MethodSignature ms = (MethodSignature) jp.getSignature();
        //方法名称
        String targetObjectMethodName = targetClsName + '.' + ms.getName();
        Method targetMethod = targetCls.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
        InsertLog insertLog = targetMethod.getAnnotation(InsertLog.class);
        //注解里的值
        String operation = insertLog.value();
        String targetMethodParams = Arrays.toString(jp.getArgs());

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();

        LoginVo userInfo = ThreadLocalUtil.get("userInfo");

        //请求方法
        String httpMethod = request.getMethod();
        //请求路径
        String servletPath = request.getServletPath();
        //请求参数
        Map<String, Object> fieldsName = getFieldsName(jp);

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        int responseStatus = response.getStatus();

        //返回值
        String resultJson = ObjectMapperUtil.toJSON(result);

        Log log = new Log();
        //模块标题
        log.setTitle(operation)
                //描述？
                .setDescription(operation)
                //业务类型（0其它 1新增 2修改 3删除）
//                .businessType(httpEnum.getCode())
                //方法名称
                .setMethod(targetObjectMethodName)
                //请求方式
                .setRequestMethod(httpMethod)
                //操作类别（0其它 1后台用户 2手机端用户）
                .setOperatorType(1)
                //操作人员
                .setOperName(userInfo.getAccount())
                //部门名称
//                .setDeptName()
                //请求URL
                .setOperUrl(servletPath)
                //主机地址
                .setOperIp(IPUtil.getIpAddr(request))
                //操作人员角色
//                .setOperRole(userInfo.getAccount())
                //操作地点
//                .setOperLocation()
                //请求参数
                .setOperParam(fieldsName.toString())
                //返回值
                .setJsonResult(resultJson)
                //操作状态（0正常 1异常）
                .setStatus(responseStatus == 200 ? 0 : 1)
                //错误消息
//                .setErrorMsg()
                //操作时间
                .setOperTime(LocalDateTime.now());
        logMapper.insert(log);
    }
    //获取请求参数
    private static Map<String, Object> getFieldsName(ProceedingJoinPoint joinPoint) {
        // 参数值
        Object[] args = joinPoint.getArgs();
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String[] parameterNames = pnd.getParameterNames(method);
        Map<String, Object> paramMap = new HashMap<>(32);
        for (int i = 0; i < parameterNames.length; i++) {
            if(parameterNames[i].equals("param")){
                paramMap.put(parameterNames[i], args[i]);
            }
        }
        return paramMap;
    }
}

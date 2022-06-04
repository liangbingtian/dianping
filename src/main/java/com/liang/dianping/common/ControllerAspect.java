package com.liang.dianping.common;

import com.liang.dianping.controller.admin.AdminController;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author liangbingtian
 * @date 2022/06/04 下午2:56
 */
@Aspect
@Configuration
public class ControllerAspect {

  @Autowired
  private HttpServletRequest request;

  @Autowired
  private HttpServletResponse response;

  /**
   * 被requestMapping注解修饰的方法可以进入该切面
   *
   * @param joinPoint
   * @return
   */
  @Around("execution(* com.liang.dianping.controller.admin.*.*(..)) && @annotation(org.springframework.web.bind.annotation.GetMapping)")
  public Object adminControllerBeforeValidation(ProceedingJoinPoint joinPoint) throws Throwable {
    Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
    AdminPermission adminPermission = method.getAnnotation(AdminPermission.class);
    if (adminPermission == null) {
      //公共方法，无需登录
      Object result = joinPoint.proceed();
      return result;
    }
    //判断当前管理员是否登录
    String email = (String) request.getSession()
        .getAttribute(AdminController.CURRENT_ADMIN_SESSION);
    if (email == null) {
      if (adminPermission.produceType().equals("text/html")) {
        response.sendRedirect("/admin/admin/loginpage");
        return null;
      }else {
        CommonError commonError = new CommonError(EmBusinessError.ADMIN_SHOULD_LOGIN);
        return CommonRes.create(commonError, "fail");
      }

    } else {
      Object result = joinPoint.proceed();
      return result;
    }
  }
}

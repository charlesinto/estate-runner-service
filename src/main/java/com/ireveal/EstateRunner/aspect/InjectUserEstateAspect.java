package com.ireveal.EstateRunner.aspect;

import com.ireveal.EstateRunner.annotations.EstateParam;
import com.ireveal.EstateRunner.exception.BadRequestException;
import com.ireveal.EstateRunner.exception.ResourceNotFoundException;
import com.ireveal.EstateRunner.service.EstateService;
import com.ireveal.EstateRunner.service.EstateUserRoleService;
import com.ireveal.EstateRunner.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.aspect
 * @Date 09/02/2025
 */

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class InjectUserEstateAspect {
    private final EstateService estateService;
    private final EstateUserRoleService estateUserRoleService;
    private final UserService userService;

    @Pointcut("execution(public * *(..)) && @annotation(com.ireveal.EstateRunner.annotations.InjectUserEstate)")
    public void anyAnnotatedMethod() {

    }

    @Around("anyAnnotatedMethod()")
    public Object InjectEstate(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = requestAttributes.getRequest();
        String estateCode = httpServletRequest.getHeader("estateCode");
        if (StringUtils.isEmpty(estateCode))
            throw new BadRequestException("Estate code is required");

        var user = userService.getCurrentlyLoggedInUser();

        var estate = estateService.findByCode(estateCode).orElseThrow(() -> new ResourceNotFoundException(String.format("Estate not found: %s", estateCode)));

        estateUserRoleService.findByEstateAndUser(estate, user).orElseThrow(() -> new BadRequestException("Action not permitted. You are not a member of this estate. Please contact administrator"));

        Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();

        Object[] args = joinPoint.getArgs();

        Annotation[][] annotations = method.getParameterAnnotations();

        for (int arg = 0; arg < args.length; arg++) {
            for (Annotation annotation : annotations[arg]) {
                if (annotation instanceof EstateParam) {
                    args[arg] = estate;
                    break;
                }
            }
        }

        return joinPoint.proceed();
    }
}

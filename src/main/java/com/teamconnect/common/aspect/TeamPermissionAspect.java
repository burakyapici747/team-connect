package com.teamconnect.common.aspect;

import com.teamconnect.common.annotation.RequireTeamPermission;
import com.teamconnect.security.CustomUserDetails;
import com.teamconnect.service.PermissionService;
import com.teamconnect.service.SecurityService;
import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TeamPermissionAspect {
    private final PermissionService permissionService;
    private final SecurityService securityService;

    public TeamPermissionAspect(PermissionService permissionService, SecurityService securityService) {
        this.permissionService = permissionService;
        this.securityService = securityService;
    }

    @Around("@annotation(requireTeamPermission)")
    public Object checkPermission(ProceedingJoinPoint joinPoint, RequireTeamPermission requireTeamPermission) throws Throwable {
        String teamId = extractTeamId(joinPoint);
        CustomUserDetails userDetails = securityService.getCurrentUser();
        String userId = userDetails.getId();

//        permissionService.validateTeamPermission(
//            teamId,
//            userId,
//            Arrays.asList(requireTeamPermission.value()),
//            requireTeamPermission.requireAll(),
//            requireTeamPermission.isSelfUserPermission(),
//            requireTeamPermission.justTeamMember()
//        );

        return joinPoint.proceed();
    }

    private String extractTeamId(ProceedingJoinPoint joinPoint) {
        return Arrays.stream(joinPoint.getArgs())
                .filter(arg -> arg instanceof String)
                .map(String.class::cast)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("TeamId not found in method parameters"));
    }
}

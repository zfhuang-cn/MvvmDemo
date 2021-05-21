package com.bird.android.aspect;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.bird.android.annotation.PermissionNeed;
import com.permissionx.guolindev.PermissionCollection;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

@Aspect
public class PermissionAspect {

    @Pointcut("execution(@com.bird.android.annotation.PermissionNeed * *(..)) && @annotation(permission)")
    public void pointActionMethod(PermissionNeed permission) {
        //定义切入点
    }

    @Around("pointActionMethod(permission)")
    public void aProceedingJoinPoint(ProceedingJoinPoint joinPoint, PermissionNeed permission) throws Throwable {
        PermissionCollection permissionX = null;
        final Object obj = joinPoint.getThis();
        if (obj instanceof FragmentActivity) {// 如果切入点是一个类？那么这个类的对象是不是FragmentActivity？
            permissionX = PermissionX.init((FragmentActivity) obj);
        } else if (obj instanceof Fragment) {// 如果切入点是一个类？那么这个类的对象是不是Fragment？
            permissionX = PermissionX.init((Fragment) obj);
        } else {// 如果切入点不是Context的子类呢？ //jointPoint.getThis，其实是得到切入点所在类的对象
            Object[] args = joinPoint.getArgs();
            if (args.length > 0) {
                if (args[0] instanceof FragmentActivity) {
                    permissionX = PermissionX.init((FragmentActivity) obj);
                } else if (args[0] instanceof Fragment) {
                    permissionX = PermissionX.init((Fragment) obj);
                }
            }
        }
        if (permissionX == null) {
            return;
        }
        permissionX.permissions(permission.value())
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, List<String> grantedList,
                                         List<String> deniedList) {
                        try {
                            if (allGranted) {
                                //所有申请的权限都已通过
                                joinPoint.proceed();
                            } else {
                                // 拒绝了权限：$deniedList
                            }
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                });
    }
}

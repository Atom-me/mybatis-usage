package com.atom.mybatis.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * scan class by packages : {use ResolverUtil instead}
 *
 * @author Atom
 * @see org.apache.ibatis.io.ResolverUtil
 */
@Slf4j
@Deprecated
public final class ClassUtil {

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> clazz;
        try {
            clazz = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            log.error("load class failure", e);
            throw new RuntimeException(e);
        }
        return clazz;
    }

    public static Set<Class<?>> getClassSet(String... packageNames) {
        Set<Class<?>> classSet = new HashSet<>();
        for (String packageName : packageNames) {
            try {
                Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
                while (urls.hasMoreElements()) {
                    URL url = urls.nextElement();
                    if (Objects.nonNull(url)) {
                        if ("file".equals(url.getProtocol())) {
                            addClass(classSet, url.getPath(), packageName);
                        }
                    }
                }
            } catch (IOException e) {
                log.error("get class set failure .", e);
            }
        }
        return classSet;
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        Collection<File> files = FileUtils.listFiles(new File(packagePath), new String[]{"class"}, true);
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (StringUtils.isNotBlank(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {
                String subPackagePath = fileName;
                if (StringUtils.isNotBlank(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (StringUtils.isNotBlank(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }

        }
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> aClass = loadClass(className, false);
        classSet.add(aClass);
    }

    public static Set<Class<?>> scanClassesByPackage(String... packageNames) {
        Set<Class<?>> allClassSet = getClassSet(packageNames);
        return allClassSet;
    }


}

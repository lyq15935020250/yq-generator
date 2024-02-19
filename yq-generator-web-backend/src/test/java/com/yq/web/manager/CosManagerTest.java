package com.yq.web.manager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lyq
 * @description:
 * @date 2024/2/19 17:10
 */
@SpringBootTest
class CosManagerTest {

    @Resource
    private CosManager cosManager;

    @Test
    void deleteObject() {
        cosManager.deleteObject("/test/logo.ico");
    }

    @Test
    void deleteObjects() {
        cosManager.deleteObjects(Arrays.asList("test/logo.png","test/李永青-Java开发实习生.png"));
    }

    @Test
    void deleteDir() {
        cosManager.deleteDir("/test/");
    }
}
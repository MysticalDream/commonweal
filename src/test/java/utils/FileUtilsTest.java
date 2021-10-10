package utils;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.utils.FileUtils;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    @Test
    void getFile() {
    }

    @Test
    void touch() {
    }

    @Test
    void deleteQuietly() {
    }

    @Test
    void getFileName() {
    }

    @Test
    void getRandomFileName() {
        String randomFileName = FileUtils.getRandomFileName("1231.png");
        System.out.println(randomFileName);
    }

}
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
        String[] s = {".png", ".jpg"};
        System.out.println(FileUtils.isAcceptedSuffix("p1.png", s));
    }

    @Test
    void getRandomFileName() {
        String randomFileName = FileUtils.getRandomFileName("1312.png");
        System.out.println(randomFileName);
    }

}
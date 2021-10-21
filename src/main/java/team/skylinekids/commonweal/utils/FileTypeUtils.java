package team.skylinekids.commonweal.utils;

public class FileTypeUtils {

    /**
     * 图片格式判断
     *
     * @param perfix 后缀
     * @return
     */
    public static boolean imageType(String perfix) {
        if (perfix.equalsIgnoreCase("JPG") || perfix.equalsIgnoreCase("JPEG") || perfix.equalsIgnoreCase("GIF")
                || perfix.equalsIgnoreCase("PNG") || perfix.equalsIgnoreCase("BMP") || perfix.equalsIgnoreCase("PCX")
                || perfix.equalsIgnoreCase("TGA") || perfix.equalsIgnoreCase("PSD") || perfix.equalsIgnoreCase("TIFF")) {
            return true;
        }
        return false;
    }


    /**
     * 音频格式判断
     *
     * @param perfix 后缀
     * @return
     */
    public static boolean audioType(String perfix) {
        if (perfix.equalsIgnoreCase("mp3") || perfix.equalsIgnoreCase("OGG") || perfix.equalsIgnoreCase("WAV")
                || perfix.equalsIgnoreCase("REAL") || perfix.equalsIgnoreCase("APE") || perfix.equalsIgnoreCase("MODULE")
                || perfix.equalsIgnoreCase("MIDI") || perfix.equalsIgnoreCase("VQF") || perfix.equalsIgnoreCase("CD")) {
            return true;
        }
        return false;
    }


    /**
     * 视频格式判断
     *
     * @param perfix 后缀
     * @return
     */
    public static boolean videoType(String perfix) {
        if (perfix.equalsIgnoreCase("mp4") || perfix.equalsIgnoreCase("avi") || perfix.equalsIgnoreCase("MPEG-1")
                || perfix.equalsIgnoreCase("RM") || perfix.equalsIgnoreCase("ASF") || perfix.equalsIgnoreCase("WMV")
                || perfix.equalsIgnoreCase("qlv") || perfix.equalsIgnoreCase("MPEG-2") || perfix.equalsIgnoreCase("MPEG4")
                || perfix.equalsIgnoreCase("mov") || perfix.equalsIgnoreCase("3gp")) {
            return true;
        }
        return false;
    }

    /**
     * 文档格式判断
     *
     * @param perfix 后缀
     * @return
     */
    public static boolean fileType(String perfix) {
        if (perfix.equalsIgnoreCase("txt") || perfix.equalsIgnoreCase("doc") || perfix.equalsIgnoreCase("docx")
                || perfix.equalsIgnoreCase("pdf") || perfix.equalsIgnoreCase("wps") || perfix.equalsIgnoreCase("rtf")
                || perfix.equalsIgnoreCase("pptx") || perfix.equalsIgnoreCase("ppt")
                || perfix.equalsIgnoreCase("xls") || perfix.equalsIgnoreCase("xlsx")) {
            return true;
        }
        return false;

    }

}
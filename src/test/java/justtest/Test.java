package justtest;

import java.text.MessageFormat;

public class Test {
    @org.junit.jupiter.api.Test
    void test(){
        String str="<div style=\"color:red\">79737237</div><div>213123</div>";

        String str2="<div style=\"color:red\">{0}</div><div>{1}</div>";

        String str3="<font>7</font>9737237,213123";

        String format = MessageFormat.format(str2, str3.split(","));
        System.out.println(format);
    }
}

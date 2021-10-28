package justtest;

public class FaTest {
    public static void main(String[] args) {
        Fa fa=new Son();
        Fa fa1=new Son();
        System.out.println(fa.getClass().equals(fa1.getClass()));
    }
}
class Fa{

}
class Son extends Fa{

}

/**
 * Created with IntelliJ IDEA.
 * Date: 21-1-13
 * Time: 下午4:25
 * Description:
 */
public class test {

  public static void main(String[] args) {
      String url = "http://www.baidu.com";
      String s = Integer.toHexString(url.hashCode());
      System.out.println(s);
    }

}

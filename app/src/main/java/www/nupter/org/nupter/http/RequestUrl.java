package www.nupter.org.nupter.http;

import www.nupter.org.nupter.model.ClassBean;

/**
 * Created by fangzhenyi on 15/11/11.
 */
public  class RequestUrl {
    public static String commen="http://nupter.sleepan.com/";
    public static String reading=commen+"getArticleList.php";
    public static String addUser=commen+"insertUser.php";
    public static String secondHand =commen+ "getSecondHandList.php";
    public static String lost =commen+ "getLost.php";
    public static String baseimgurl = "http://7xn1yf.com1.z0.glb.clouddn.com/";


    public static String readingOne=commen+"getOneReading.php";
    public static String uploadtoken = commen+"getQiniuToken.php";
    public static String insertLost = commen+"insertLost.php";


    public static String cookieZhengFang=commen+"getZhenfangCookie.php";
    public static String verifyImageUrl=commen+"getZhenfangVerifyCode.php?cookie=";
    public static String loginZhengFangUrl=commen+"loginZhenfang.php";

    public static String getBookUrl=commen+"getBook.php";

    public static String getBookInfo=commen+"getBookDetail.php";

    public static String loginLibrary=commen+"loginLibrary.php";

    public static String borrowBook=commen+"xujieBook.php";

    public static String getQiniu=commen+"getQiniuToken.php";



}

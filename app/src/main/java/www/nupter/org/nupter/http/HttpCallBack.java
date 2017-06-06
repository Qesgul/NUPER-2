package www.nupter.org.nupter.http;

/**
 * Created by fangzhenyi on 15/11/11.
 */
public interface HttpCallBack<T> {
    public void success(T data);
    public void erro(String res);
}

package www.nupter.org.nupter.event;

/**
 * Created by fangzhenyi on 16/3/6.
 */
public class PushScreenMessageEvent {
    private String content;
    public PushScreenMessageEvent(String content){
        this.content=content;
    }
    public String getContent(){
        return content;
    }
}

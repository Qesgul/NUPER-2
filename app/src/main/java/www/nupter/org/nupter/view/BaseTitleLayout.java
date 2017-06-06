package www.nupter.org.nupter.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import www.nupter.org.nupter.R;
import www.nupter.org.nupter.activity.PublishLostActivity;


/**
 * Created by Xuri on 2015/4/3.
 */
public class BaseTitleLayout extends LinearLayout {

    public TextView title;

    public ImageView left_image;
    public ImageView right_image;

    public TextView centerText;

    public TextView rightText;


    private View titleBar;

    public BaseTitleLayout(Context context, int layoutId) {
        super(context);
        this.setOrientation(LinearLayout.VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //titleBar
        titleBar = inflater.inflate(R.layout.titlebar_layout, this, false);
        addView(titleBar);

        //content
        View content = inflater.inflate(layoutId, null);
        LayoutParams contentParam = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(content, contentParam);

      //  FontManager.changeFonts(this);
        title = getView(R.id.header_title);
        left_image = getView(R.id.image_left);
        right_image = getView(R.id.image_right);
        centerText=getView(R.id.centerText);
        rightText=getView(R.id.rightText);


    }

    public View getTitleBar() {
        return titleBar;
    }

    private <T extends View> T getView(int id) {
        return (T) titleBar.findViewById(id);
    }
}
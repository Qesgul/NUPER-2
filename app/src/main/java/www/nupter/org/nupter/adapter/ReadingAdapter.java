package www.nupter.org.nupter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.nereo.multi_image_selector.bean.Image;
import www.nupter.org.nupter.R;
import www.nupter.org.nupter.http.HttpResquestUtil;
import www.nupter.org.nupter.model.ReadingBean;

/**
 * Created by fangzhenyi on 16/4/11.
 */
public class ReadingAdapter extends BaseAdapter {
    private List<ReadingBean.InfoEntity> readingBeen;
    private LayoutInflater mLayoutInflater;

    static class OneImageHolder {
        public TextView authorText;
        public ImageView authorImage;
        public TextView tagText;
        public TextView titleText;
        public TextView desText;
        public ImageView firstImage;

        public OneImageHolder(View itemView) {
            authorText = (TextView) itemView.findViewById(R.id.author_name);
            authorImage = (ImageView) itemView.findViewById(R.id.author_img);
            tagText = (TextView) itemView.findViewById(R.id.tag);
            titleText = (TextView) itemView.findViewById(R.id.title);
            desText = (TextView) itemView.findViewById(R.id.des);
            firstImage = (ImageView) itemView.findViewById(R.id.firstImage);
        }
    }

    static class TwoImageHolder {
        public TextView authorText;
        public ImageView authorImage;
        public TextView tagText;
        public TextView titleText;
        public ImageView firstImage;
        public ImageView secondImage;

        public TwoImageHolder(View itemView) {

            authorText = (TextView) itemView.findViewById(R.id.author_name);
            authorImage = (ImageView) itemView.findViewById(R.id.author_img);
            tagText = (TextView) itemView.findViewById(R.id.tag);
            titleText = (TextView) itemView.findViewById(R.id.title);
            firstImage = (ImageView) itemView.findViewById(R.id.firstImage);
            secondImage = (ImageView) itemView.findViewById(R.id.secondImage);
        }
    }

    static class ThreeImageHolder {
        public TextView authorText;
        public ImageView authorImage;
        public TextView tagText;
        public TextView titleText;
        public ImageView firstImage;
        public ImageView secondImage;
        public ImageView threeImage;

        public ThreeImageHolder(View itemView) {
            authorText = (TextView) itemView.findViewById(R.id.author_name);
            authorImage = (ImageView) itemView.findViewById(R.id.author_img);
            tagText = (TextView) itemView.findViewById(R.id.tag);
            titleText = (TextView) itemView.findViewById(R.id.title);
            firstImage = (ImageView) itemView.findViewById(R.id.firstImage);
            secondImage = (ImageView) itemView.findViewById(R.id.secondImage);
            threeImage = (ImageView) itemView.findViewById(R.id.threeImage);
        }
    }

    static class NoImageHolder {
        public TextView authorText;
        public ImageView authorImage;
        public TextView tagText;
        public TextView titleText;
        public TextView desText;

        public NoImageHolder(View itemView) {
            authorText = (TextView) itemView.findViewById(R.id.author_name);
            authorImage = (ImageView) itemView.findViewById(R.id.author_img);
            tagText = (TextView) itemView.findViewById(R.id.tag);
            titleText = (TextView) itemView.findViewById(R.id.title);
            desText = (TextView) itemView.findViewById(R.id.des);
        }
    }


    public ReadingAdapter(List<ReadingBean.InfoEntity> readingBeen, Context context) {
        super();
        this.readingBeen = readingBeen;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public int getCount() {
        return readingBeen.size();
    }

    @Override
    public int getItemViewType(int position) {
        return Integer.valueOf(readingBeen.get(position).getImg_num());
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        NoImageHolder noImageHolder = null;
        OneImageHolder oneImageHolder = null;
        TwoImageHolder twoImageHolder = null;
        ThreeImageHolder threeImageHolder = null;
        int viewType = getItemViewType(i);
        if (view == null) {
            if (viewType == 0) {
                view = mLayoutInflater.inflate(R.layout.list_reading_item_none, parent, false);
                noImageHolder = new NoImageHolder(view);
                view.setTag(noImageHolder);
                setNoneImage(noImageHolder,i);
            } else if (viewType == 1) {
                view = mLayoutInflater.inflate(R.layout.list_reading_item_first, parent, false);
                oneImageHolder = new OneImageHolder(view);
                view.setTag(oneImageHolder);
                setOneImage(oneImageHolder,i);
                return view;

            } else if (viewType == 2) {
                view = mLayoutInflater.inflate(R.layout.list_reading_item_second, parent, false);
                twoImageHolder = new TwoImageHolder(view);
                view.setTag(twoImageHolder);
                setTwoImage(twoImageHolder,i);

            } else {
                view = mLayoutInflater.inflate(R.layout.list_reading_item_three, parent, false);
                threeImageHolder = new ThreeImageHolder(view);
                view.setTag(threeImageHolder);
                setThreeImage(threeImageHolder,i);
            }
        } else {
            if (viewType == 0) {
               setNoneImage((NoImageHolder) view.getTag(),i);
            } else if (viewType == 1) {
               setOneImage((OneImageHolder)view.getTag(),i);
            } else if (viewType == 2) {
               setTwoImage((TwoImageHolder)view.getTag(),i);
            } else if (viewType == 3) {
               setThreeImage((ThreeImageHolder)view.getTag(),i);
            }
        }
        return view;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    public void setNoneImage(NoImageHolder holder, int position) {
        holder.authorText.setText(readingBeen.get(position).getAuthor());
        HttpResquestUtil.initImage(holder.authorImage, readingBeen.get(position).getAuthor_img());
        holder.titleText.setText(readingBeen.get(position).getTitle());
        holder.desText.setText(readingBeen.get(position).getDes());
    }

    public void setOneImage(OneImageHolder holder, int position) {
        holder.authorText.setText(readingBeen.get(position).getAuthor());
        HttpResquestUtil.initImage(holder.authorImage, readingBeen.get(position).getAuthor_img());
        holder.titleText.setText(readingBeen.get(position).getTitle());
        holder.desText.setText(readingBeen.get(position).getDes());
        HttpResquestUtil.initImage(holder.firstImage, readingBeen.get(position).getImg_url1());
    }
    public void setTwoImage(TwoImageHolder holder, int position) {
        holder.authorText.setText(readingBeen.get(position).getAuthor());
        HttpResquestUtil.initImage(holder.authorImage, readingBeen.get(position).getAuthor_img());
        holder.titleText.setText(readingBeen.get(position).getTitle());
        HttpResquestUtil.initImage(holder.firstImage, readingBeen.get(position).getImg_url1());
        HttpResquestUtil.initImage(holder.secondImage, readingBeen.get(position).getImg_url2());
    }
    public void setThreeImage(ThreeImageHolder holder, int position) {
        holder.authorText.setText(readingBeen.get(position).getAuthor());
        HttpResquestUtil.initImage(holder.authorImage, readingBeen.get(position).getAuthor_img());
        holder.titleText.setText(readingBeen.get(position).getTitle());
        HttpResquestUtil.initImage(holder.firstImage, readingBeen.get(position).getImg_url1());
        HttpResquestUtil.initImage(holder.secondImage, readingBeen.get(position).getImg_url2());
        HttpResquestUtil.initImage(holder.threeImage, readingBeen.get(position).getImg_url3());
    }
}











package www.nupter.org.nupter.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import www.nupter.org.nupter.R;


public class PublishLostSecondFragment extends Fragment {


    private EditText timeEdit;
    private EditText phoneEdit;
    private EditText nameEdit;
    private EditText siteEdit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_publish_lost_second, container, false);
        nameEdit=(EditText)view.findViewById(R.id.person);
        phoneEdit=(EditText)view.findViewById(R.id.phone);
        timeEdit=(EditText)view.findViewById(R.id.time);
        siteEdit=(EditText)view.findViewById(R.id.site);
        return view;
    }

    public String getTime(){
        return timeEdit.getText().toString();
    }

    public String getPhone(){
        return phoneEdit.getText().toString();
    }

    public String getName(){
        return nameEdit.getText().toString();
    }

    public String getSite(){
        return siteEdit.getText().toString();
    }

}

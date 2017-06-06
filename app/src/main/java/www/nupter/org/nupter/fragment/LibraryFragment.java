package www.nupter.org.nupter.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.annotation.IncompleteAnnotationException;

import www.nupter.org.nupter.R;
import www.nupter.org.nupter.activity.LoginLibraryActivity;
import www.nupter.org.nupter.activity.SearchBookActivity;


public class LibraryFragment extends Fragment {

    private ImageView searchImage;

    private ImageView myImage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_library, container, false);
        searchImage=(ImageView)view.findViewById(R.id.library_seach);
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SearchBookActivity.class);
                startActivity(intent);
            }
        });
        myImage=(ImageView)view.findViewById(R.id.library_my);
        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), LoginLibraryActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


}

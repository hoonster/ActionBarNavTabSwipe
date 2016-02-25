package video;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.wptrafficanalyzer.actionbarnavtabswipe.R;

/**
 * Created by hlee on 2/23/16.
 */
public class MyFragment extends Fragment {

    public static final String EXTRA_PAGE = "EXTRA_PAGE";
    int intPage = 0;

    public static final MyFragment newInstance(String pages) {
        System.out.println("$$MyFragment$$");
        MyFragment f = new MyFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_PAGE, pages);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("MyFragment");

        Bundle b = getActivity().getIntent().getExtras(); // Getting the Bundle object that pass from another activity
        String SelectedProperty = b.getString("SelectedProperty");
        Log.e("SelectedProperty", SelectedProperty);

        List<String> url = extractUrls(SelectedProperty);
        String listString = "";

        for (String s : url) {
            listString += s + "\t";
        }

        System.out.println("listString " + listString);

        VideoView vidView = (VideoView)getActivity().findViewById(R.id.myVideo);
        String vidAddress = listString;
        Uri vidUri = Uri.parse(vidAddress);
        vidView.setVideoURI(vidUri);
        vidView.start();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.myfragment_layout, container, false);
        String strPage = getArguments().getString(EXTRA_PAGE);
        TextView messageTextView = (TextView) rootView.findViewById(R.id.textView);
        messageTextView.setText(String.valueOf(strPage));

        return rootView;
    }

    public static List<String> extractUrls(String text) {
        List<String> containedUrls = new ArrayList<String>();
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find()) {
            containedUrls.add(text.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }

        return containedUrls;
    }
}

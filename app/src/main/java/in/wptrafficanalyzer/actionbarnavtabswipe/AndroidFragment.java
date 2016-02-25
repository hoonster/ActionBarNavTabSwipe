package in.wptrafficanalyzer.actionbarnavtabswipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import video.VideoPage;


public class AndroidFragment extends ListFragment {
    public ArrayList<String> lines;
    ArrayAdapter<String> adapter;
    public String line = "";
    public int aaa = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            // Create a URL for the desired page
            URL url = new URL("http://ahmed.wetube.org/channels.txt");

            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            lines = new ArrayList<String>();
            while ((line = in.readLine()) != null) {
                //String[] myArray = line.split(",");
                lines.add(line);
                System.out.println("$$$ line $$$" + line);
                aaa++;
            }
            in.close();
            /** Creating array adapter to set data in listview */
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, lines);
            System.out.println("$$$ lines $$$"+lines);

            /** Setting the array adapter to the listview */
            setListAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        String selectedValue = (String) getListAdapter().getItem(position);

        Toast.makeText(getActivity(), selectedValue, Toast.LENGTH_SHORT).show();
        Intent moreDetailsIntent = new Intent(getActivity(), VideoPage.class);

        Bundle dataBundle = new Bundle();
        dataBundle.putString("SelectedProperty", selectedValue);
        dataBundle.putStringArrayList("channels", lines);
        moreDetailsIntent.putExtras(dataBundle);
        startActivity(moreDetailsIntent);

    }

    @Override
    public void onStart() {
        super.onStart();
        /** Setting the multiselect choice mode for the listview */
        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

    }
}

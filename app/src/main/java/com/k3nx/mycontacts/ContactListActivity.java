package com.k3nx.mycontacts;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ContactListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        ArrayList<Contact> contacts = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            Contact contact1 = new Contact();
            if (i % 2 == 0) {
                contact1.setName("Ben Wilcox");
            }else {
                contact1.setName("Ken Wilcox");
            }
            contacts.add(contact1);
        }

        ListView listView = (ListView)findViewById(R.id.contact_list_view);
        listView.setAdapter(new ContactAdapter(contacts));
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            int previousFirstItem = 0;
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // Ignore for now
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem > previousFirstItem) {
                    getSupportActionBar().hide();
                } else if (firstVisibleItem < previousFirstItem) {
                    getSupportActionBar().show();
                }
                previousFirstItem = firstVisibleItem;
            }
        });
    }

    private class ContactAdapter extends ArrayAdapter<Contact>{
        ContactAdapter(ArrayList<Contact> contacts) {
            super(ContactListActivity.this, R.layout.contact_list_row, R.id.contact_row_name, contacts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = super.getView(position, convertView, parent);
            Contact contact = getItem(position);
            TextView nameTextView = (TextView)convertView.findViewById(R.id.contact_row_name);
            nameTextView.setText(contact.getName());
            return convertView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

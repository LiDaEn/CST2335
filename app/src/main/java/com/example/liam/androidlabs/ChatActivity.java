package com.example.liam.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatActivity extends Activity {

    private String ACTIVITY_NAME = "ChatActivity";
    private Button sendbutton;
    private ListView chatview;
    private View chatParent;
    private EditText msgPane;
    private ArrayList<String> msgContainer = new ArrayList<>();
    private ChatAdapter msgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sendbutton = (Button) findViewById(R.id.sendbutton);
        chatview = (ListView) findViewById(R.id.msgView);
        msgPane = (EditText) findViewById(R.id.msgPane);
        //chatParent = findViewById(R.layout.activity_chat);
        msgAdapter = new ChatAdapter(this);
        chatview.setAdapter(msgAdapter);

        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msgContainer.add(msgPane.getText().toString());
                msgAdapter.notifyDataSetChanged();
                msgPane.setText("");
            }
        });

    }

    public class ChatAdapter extends ArrayAdapter<String> {

        public int position = 0;

        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        @Override
        public int getCount() {
            return msgContainer.size();
        }
        @Override
        public String getItem(int position) {
            return msgContainer.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatActivity.this.getLayoutInflater();



            View result;
            if (position%2 == 0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            } else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            }

            TextView message = (TextView)result.findViewById(R.id.message_text);
            message.setText(getItem(position)); // get the string at position
            return result;

        }

       public long getId(int position) {
           return position;
       }
    }



    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //onActivityResult(1, 2, takePictureIntent);
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}

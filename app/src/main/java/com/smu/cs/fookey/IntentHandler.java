package com.smu.cs.fookey;

import android.content.Context;
import android.content.Intent;

/**
 * Created by LG on 2017-08-18.
 */

public class IntentHandler {
    public static void mainToSearch(Context context){
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }
    public static void mainToHistory(Context context){
        Intent intent = new Intent(context, HistoryActivity.class);
        context.startActivity(intent);
    }
    public static void historyToSpecific(Context context, int position){
        Intent intent = new Intent(context, SpecificActivity.class);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }
}
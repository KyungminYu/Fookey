package com.smu.cs.fookey;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.smu.cs.fookey.Network.Description;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    public static void historyToSpecific(Context context, Description description, String path){
        Intent intent = new Intent(context, SpecificActivity.class);
        Bundle bundle = new Bundle();
        List<Description> descriptionList = new ArrayList<>();
        descriptionList.add(description);
        bundle.putSerializable("description", (Serializable) descriptionList);
        intent.putExtra("path", path);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    public static void SearchToResult(Context context, String imgUri){
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra("imgUri", imgUri);
        context.startActivity(intent);
    }
    public static void ResultToSpecific(Context context, Description description, String path){
        Intent intent = new Intent(context, SpecificActivity.class);
        Bundle bundle = new Bundle();
        List<Description> descriptionList = new ArrayList<>();
        descriptionList.add(description);
        bundle.putSerializable("description", (Serializable) descriptionList);
        intent.putExtra("path", path);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
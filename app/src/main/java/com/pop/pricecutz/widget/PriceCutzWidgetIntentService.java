/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pop.pricecutz.widget;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.RemoteViews;

import com.google.gson.Gson;
import com.pop.pricecutz.R;
import com.pop.pricecutz.activities.main.MainActivity;

/**
 * IntentService which handles updating all Today widgets with the latest data
 */
public class PriceCutzWidgetIntentService extends IntentService {

    AppWidgetManager appWidgetManager;
    int[] appWidgetIds;

    int discountCount;

    private final String LOG_TAG = PriceCutzWidgetIntentService.class.getSimpleName();

    public PriceCutzWidgetIntentService() {
        super("PriceCutzWidgetIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        appWidgetManager = AppWidgetManager.getInstance(this);
        appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, PriceCutzWidgetProvider.class));

        //Log.d(LOG_TAG, Integer.toString(appWidgetIds.length));
        //Log.d(LOG_TAG, new Gson().toJson(appWidgetManager));

        //Get Discounts
        discountCount = 12;

        updateUI();
    }

    private void updateUI() {
        for (int appWidgetId : appWidgetIds) {
            // Find the correct layout based on the widget's width
            int widgetWidth = getWidgetWidth(appWidgetManager, appWidgetId);
            int defaultWidth = getResources().getDimensionPixelSize(R.dimen.widget_default_width);
            int largeWidth = getResources().getDimensionPixelSize(R.dimen.widget_large_width);
            int layoutId;
            if (widgetWidth >= largeWidth) {
                layoutId = R.layout.widget_large;
            } else if (widgetWidth >= defaultWidth) {
                layoutId = R.layout.widget;
            } else {
                layoutId = R.layout.widget_small;
            }
            RemoteViews views = new RemoteViews(getPackageName(), layoutId);

            int logoResourceId = R.mipmap.ic_launcher;
            views.setImageViewResource(R.id.widget_icon, logoResourceId);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                setRemoteContentDescription(views, "Logo");
            }
            views.setTextViewText(R.id.widget_discount_count, Integer.toString(discountCount));

            // Create an Intent to launch MainActivity
            Intent launchIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, launchIntent, 0);
            views.setOnClickPendingIntent(R.id.widget, pendingIntent);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    private int getWidgetWidth(AppWidgetManager appWidgetManager, int appWidgetId) {
        // Prior to Jelly Bean, widgets were always their default size
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return getResources().getDimensionPixelSize(R.dimen.widget_default_width);
        }
        // For Jelly Bean and higher devices, widgets can be resized - the current size can be
        // retrieved from the newly added App Widget Options
        return getWidgetWidthFromOptions(appWidgetManager, appWidgetId);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private int getWidgetWidthFromOptions(AppWidgetManager appWidgetManager, int appWidgetId) {
        Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);
        if (options.containsKey(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH)) {
            int minWidthDp = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
            // The width returned is in dp, but we'll convert it to pixels to match the other widths
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, minWidthDp,
                    displayMetrics);
        }
        return  getResources().getDimensionPixelSize(R.dimen.widget_default_width);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    private void setRemoteContentDescription(RemoteViews views, String description) {
        views.setContentDescription(R.id.widget_icon, description);
    }

}

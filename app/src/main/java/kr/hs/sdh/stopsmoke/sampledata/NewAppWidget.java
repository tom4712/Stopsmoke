package kr.hs.sdh.stopsmoke.sampledata;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import kr.hs.sdh.stopsmoke.DBhelper;
import kr.hs.sdh.stopsmoke.R;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {
    TextView txv;
    static DBhelper dbhelper;
    static Cursor all_cursor;
    static int num = 40;
    static ArrayList<String> list = new ArrayList(num);
    static int a;
    static int y2;
    static int m2;
    static int d2;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
        dbhelper = new DBhelper(context);
        dbhelper.open();

        Cursul();


        y2 = Integer.parseInt(list.get(0).substring(0,4));
        m2 = Integer.parseInt(list.get(0).substring(4,6));
        d2 = Integer.parseInt(list.get(0).substring(6,8));

        a  = cdountday2(y2,m2,d2);

        Log.d("widget",""+a);

       views.setTextViewText(R.id.widgettext, String.valueOf(a));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void Cursul() {
        list.clear();
        dbhelper.open();
        all_cursor = dbhelper.AllRows();
        all_cursor.moveToFirst();
        while (true) {
            try {
                list.add(all_cursor.getString(all_cursor.getColumnIndex("SDATE")));
                Log.d("DB", "시작날자 받아옴"+list.get(0));
                list.add(all_cursor.getString(all_cursor.getColumnIndex("ONOFF")));
                Log.d("DB", "실행여부 받아옴"+list.get(1));
                list.add(all_cursor.getString(all_cursor.getColumnIndex("ENDDATE")));
                Log.d("DB", "목표날자 받아옴"+list.get(2));
                list.add(all_cursor.getString(all_cursor.getColumnIndex("MANY")));
                Log.d("DB", "몇개 받아옴"+list.get(3));
                if (!all_cursor.moveToNext())
                    break;
            } catch (Exception e) {

            }

        }
    }//DB검색
    static int cdountday2(int year, int mmonth, int mday) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Calendar startdayCal = Calendar.getInstance(); //시작날자
            Calendar ddayCal = Calendar.getInstance(); //오늘날자 가져오기ㅁㄴㅇㅁㄴㅇㅁ
            Log.d("timesss","todaCal"+startdayCal);
            Log.d("timesss","todaCal"+ddayCal);

            mmonth -= 1; // 받아온날자에서 -1을 해줘야함.

            startdayCal.set(year, mmonth, mday);// D-day의 날짜를 입력
            Log.d("테스트으응",""+year+"[]"+mmonth+"[]"+mday);
            Log.d("테스트", simpleDateFormat.format(startdayCal.getTime()) + "");
            Log.d("테스트2", simpleDateFormat.format(ddayCal.getTime()) + "");

            long today = startdayCal.getTimeInMillis() / 86400000; //->(24 * 60 * 60 * 1000) 24시간 60분 60초 * (ms초->초 변환 1000)
            long dday = ddayCal.getTimeInMillis() / 86400000;
            long count = dday - today; // 오늘 날짜에서 dday 날짜를 빼주게 됩니다.
            num = (int) count * -1;
            Log.d("테스트","씨이ㅇ이이이발"+num);

            return (int) count;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }//전체
}


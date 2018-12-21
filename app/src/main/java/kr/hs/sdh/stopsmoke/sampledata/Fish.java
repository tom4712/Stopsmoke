package kr.hs.sdh.stopsmoke.sampledata;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import kr.hs.sdh.stopsmoke.DBhelper;
import kr.hs.sdh.stopsmoke.R;

import static android.graphics.Color.argb;

public class Fish extends AppCompatActivity {
    String nnn = "";
    CalendarView calendar;
    final Context context = this;
    View view;
    int mYear;
    long now = System.currentTimeMillis();

    private DBhelper dbhelper;
    private Cursor all_cursor;
    int num = 40;
    private ArrayList<String> list = new ArrayList(num);


    Date date = new Date(now);

    SimpleDateFormat sdf = new SimpleDateFormat("dd");
    SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
    SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");

    String getday = sdf.format(date);
    int day = Integer.parseInt(getday);


    String getM=sdf2.format(date);
    int Mmonth = Integer.parseInt(getM);
    String getY=sdf3.format(date);
    int yyyy = Integer.parseInt(getY);

    int mMonth,mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish);
        calendar = (CalendarView)findViewById(R.id.calenda);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth=month;
                mMonth+=1;
                mDay=dayOfMonth;

                Toast.makeText(Fish.this, year+"/"+(month+1)+"/"+dayOfMonth, Toast.LENGTH_SHORT).show();
                Toast.makeText(Fish.this, ""+mMonth, Toast.LENGTH_SHORT).show();

                if(yyyy<=mYear) {

                    if(yyyy==mYear){
                        if(Mmonth<mMonth){
                            nnn=""+mYear+mMonth+mDay;
                        }

                        else if(Mmonth==mMonth){
                            if(day<mDay){
                                nnn=""+mYear+mMonth+mDay;
//여기다 씨발 여기다가 디비 하면됌개새끼야
                            }
                            else{
                                show();
                            }
                        }


                        else{
                        show();
                        }
                    }
                Toast.makeText(Fish.this,"개꾸울",Toast.LENGTH_SHORT).show();

                    if(yyyy<mYear){
                        nnn=""+mYear+mMonth+mDay;

                    }
                }
                else{
                    nnn=""+mYear+mMonth+mDay;
                    Log.d("nnn",""+nnn);






                    Toast.makeText(Fish.this,"먼지모름",Toast.LENGTH_SHORT).show();
                }



            }

        });


    }

    public void show(){
        view = this.getLayoutInflater().inflate(R.layout.dig, null);
        TextView txtTitle = (TextView) view.findViewById(R.id.title);
        txtTitle.setTextSize(30);
        txtTitle.setTextColor(Color.BLACK);
        txtTitle.setText("경고!");
// 내용 넣기
        TextView message = (TextView) view.findViewById(R.id.message);
        message.setTextSize(25);
        txtTitle.setTextColor(Color.BLACK);
        message.setText("날짜를 확인해주세요!");

        AlertDialog.Builder alt = new AlertDialog.Builder(context);

        alt.setView(view)
                .setCancelable(false);

        AlertDialog alert = alt.create();
        alert.setCanceledOnTouchOutside(true);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(argb(255,255,116,115)));

        alert.show();
    }
    public void onClick(View view) {

        if(mYear!=0){
            insert();


        }
        else{
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // 제목셋팅
            alertDialogBuilder.setTitle("경고!");

            // AlertDialog 셋팅
            alertDialogBuilder
                    .setMessage("목표 날짜를 선택해주세요")
                    .setCancelable(false);


            // 다이얼로그 생성
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.setCanceledOnTouchOutside(true);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(255,255,116,115)));
            // 다이얼로그 보여주기
            alertDialog.show();





        }
    }
    public void insert(){
        dbhelper = new DBhelper(getApplication());
        dbhelper.open();
        Log.d("nnn","aaaa"+nnn);
        dbhelper.updateedate(nnn);
    }
}

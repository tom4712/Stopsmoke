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
                            String nnn=""+mYear+mMonth+mDay;
                            Log.d("nnn",""+nnn);
//여기다 씨발 여기다가 디비 하면됌개새끼야
                        }

                        else if(Mmonth==mMonth){
                            if(day<mDay){
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

                }
                else{
                    String nnn=""+mYear+mMonth+mDay;
                    Log.d("nnn",""+nnn);
                    //여기다 씨발 여기다가 디비 하면됌개새끼야
                    //여기다 씨발 여기다가 디비 하면됌개새끼야//여기다 씨발 여기다가 디비 하면됌개새끼야//여기다 씨발 여기다가 디비 하면됌개새끼야//여기다 씨발 여기다가 디비 하면됌개새끼야
                    //여기다 씨발 여기다가 디비 하면됌개새끼야





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
        mYear=0;
        if(mYear==0){
            message.setText("날짜를 선택해주십시오!");
        }
        else {
            message.setText("날짜를 확인해주세요!");
        }
        AlertDialog.Builder alt = new AlertDialog.Builder(context);

        alt.setView(view)
                .setCancelable(false);

        AlertDialog alert = alt.create();
        alert.setCanceledOnTouchOutside(true);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(argb(255,255,116,115)));

        mYear=0;
        alert.show();
    }
    public void onClick(View view) {

        if(mYear!=0){

            Intent intent=new Intent(Fish.this,Four.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_left);
            finish();

        }
        else{

            show();


        }
    }

}

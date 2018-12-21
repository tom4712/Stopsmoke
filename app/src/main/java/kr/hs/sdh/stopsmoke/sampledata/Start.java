package kr.hs.sdh.stopsmoke.sampledata;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import kr.hs.sdh.stopsmoke.MainActivity;
import kr.hs.sdh.stopsmoke.R;

import static android.graphics.Color.*;
import static android.graphics.Color.WHITE;

public class Start extends AppCompatActivity {
    CalendarView calendar;
    final Context context = this;
    View view;
    int mYear;
    int mMonth,mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        calendar = (CalendarView)findViewById(R.id.calenda);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                mYear = year;
                Toast.makeText(Start.this, year+"/"+(month+1)+"/"+dayOfMonth, Toast.LENGTH_SHORT).show();

            }

        });
    }



    public void onClick(View view) {

        if(mYear==0){

            Intent intent=new Intent(Start.this,Fish.class);
            startActivity(intent);

        }
        else{
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                    context);
//
//            // 제목셋팅
//            alertDialogBuilder.setTitle("경고!");
//
//            // AlertDialog 셋팅
//            alertDialogBuilder
//                    .setMessage("시작 날짜를 선택해주세요")
//                    .setCancelable(false);
//
//
//            // 다이얼로그 생성
//            AlertDialog alertDialog = alertDialogBuilder.create();
//            alertDialog.setCanceledOnTouchOutside(false);
//            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(255,255,116,115)));
//            // 다이얼로그 보여주기
//            alertDialog.show();


            view = this.getLayoutInflater().inflate(R.layout.dig, null);
// 제목 넣기
            TextView txtTitle = (TextView) view.findViewById(R.id.title);
            txtTitle.setTextSize(40);
            txtTitle.setTextColor(Color.BLACK);
            txtTitle.setText("경고!");
// 내용 넣기
            TextView message = (TextView) view.findViewById(R.id.message);
            message.setTextSize(25);
            txtTitle.setTextColor(Color.BLACK);
            message.setText("날짜를 확인해주세요!");

            AlertDialog.Builder alt = new AlertDialog.Builder(this);

            alt.setView(view)
                    .setCancelable(false)
                    .setPositiveButton("확인",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

            AlertDialog alert = alt.create();

            alert.getWindow().setBackgroundDrawable(new ColorDrawable(argb(255,255,116,115)));



            alert.show();




        }
    }
}

package kr.hs.sdh.stopsmoke;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import kr.hs.sdh.stopsmoke.sampledata.Start;

public class MainActivity extends AppCompatActivity {
    //버튼
    ImageButton mainplus;

    //레이아웃
    AutoScrollViewPager autoViewPager;
    private ListView mListView;

    //DB
    private DBhelper dbhelper;
    private Cursor all_cursor;
    int num = 40;
    private ArrayList<String> list = new ArrayList(num);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //버튼 아이디 지정
        mainplus = findViewById(R.id.mainplus);

        //DB생성
        DBcreate();
        //DB검색
        Cursul();
        //첫실행 확인
        //checkfirst();
        //이미지 슬라이드
        Imageslide();
        //리스트뷰
        dataSetting();

    }




    public void Imageslide(){

        ArrayList<String> data = new ArrayList<>(); //이미지 url를 저장하는 arraylist
        data.add("https://pds.joins.com/news/component/htmlphoto_mmdata/201612/22/htm_20161222142415183195.jpg");
        data.add("http://www.korea.kr/newsWeb/resources/attaches/2018.07/12/318994929409595fbb35d1d2d60872ee.jpg");
        data.add("https://i.pinimg.com/originals/fa/d8/2e/fad82e9b7a48ac31cde759751bdaa98e.jpg");
        data.add("http://ktravelacademy.com/wp/wp-content/uploads/2018/07/ktravelacademy_dq201710.001.jpeg");

        autoViewPager = (AutoScrollViewPager)findViewById(R.id.autoViewPager);
        Adapter.AutoScrollAdapter scrollAdapter = new Adapter(). new AutoScrollAdapter(this,data);
        autoViewPager.setAdapter(scrollAdapter); //Auto Viewpager에 Adapter 장착
        autoViewPager.setInterval(5000); // 페이지 넘어갈 시간 간격 설정
        autoViewPager.startAutoScroll(); //Auto Scroll 시작
    }//이미지 슬라이드
    private void dataSetting(){
        mListView = (ListView)findViewById(R.id.listview);
        MyAdapter mMyAdapter = new MyAdapter();


        for (int i=0; i<=8; i++) {
            if(i == 0) mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.main_list_0), "금연20분 경과" , "혈압과 맥박이 정상으로 돌아옵니다.");
            if(i == 1) mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.main_list_1), "금연8시간 경과" , "혈액 속의 일산화탄소 양이 감소하고 산소양이 정상으로 증가 합니다.");
            if(i == 2) mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.main_list_2), "금연24시간 경과" , "심장마비 위험이 감소합니다.");
            if(i == 3) mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.main_list_3), "금연48시간 경과" , "신경 말단 부위가 니코틴이 사라진 것에 적응하고 후각과 미각 능력이 증가합니다.");
            if(i == 4) mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.main_list_4), "금연72시간 경과" , "기관지가 이완되고 호흡하는 것이 쉬워지며 폐활량이 증가합니다." );
            if(i == 5) mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.main_list_5), "금연1개월 경과" , "혈액 순환이 좋아지고 걷는 것이 쉬워지며 폐 기능이 30%증가합니다.");
            if(i == 6) mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.main_list_6), "금연1년 경과" , "심장마비 사망 위험이 흡연자의 절반으로 줄어듭니다.");
            if(i == 7) mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.main_list_7), "금연5년 경과" , "심장마비 사망 위험이 비흡연자와 거의 같아 집니다.");
            if(i == 8) mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.main_list_8), "금연10년 경과" , "폐암으로 인한 사망 위험이 흡연자의 절반으로 줄어듭니다.");
        }

        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);
    }//리스트뷰
    public void DBcreate(){
            dbhelper = new DBhelper(getApplication());              Log.d("DB", "헬퍼불러옴");
        dbhelper.open();                                             Log.d("DB", "디비오픈");
        all_cursor = dbhelper.AllRows();                              Log.d("DB", "커서 열음");
        all_cursor.moveToFirst();                                           Log.d("DB", "처음으로감");
            SharedPreferences pref = getSharedPreferences("isFirst", Activity.MODE_PRIVATE);
            boolean first = pref.getBoolean("isFirst", false);    Log.d("DB", "처음인지 확인");
            if (first == false) {
                Log.d("DB", "처음임");
                dbhelper.insertGarbage();
                Log.d("DB", "쓰레기값넣음");

                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("isFirst", true);
                editor.commit();
            }
        }//DB생성
    public void Cursul() {
        list.clear();
        dbhelper = new DBhelper(this);
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
                if (!all_cursor.moveToNext())
                    break;
            } catch (Exception e) {

            }

        }
    }//DB검색
//    public void checkfirst(){
//        if(Integer.parseInt(list.get(1)) != 1){
//            Intent intent=new Intent(MainActivity.this,Start.class);
//            startActivity(intent);
//            finish();
//        }
//    }
    @Override
    protected void onStop() {
        super.onStop();
        dbhelper.close();
    } //앱종료


}


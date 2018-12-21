package kr.hs.sdh.stopsmoke.sampledata;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import kr.hs.sdh.stopsmoke.MainActivity;
import kr.hs.sdh.stopsmoke.R;

public class Four extends AppCompatActivity {
    Button Threepacks,Twopacks,Onepacks,twenty_bar,Ten_bar;
    int town=0;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        Threepacks=findViewById(R.id.Threepacks);
        Twopacks=findViewById(R.id.Twopacks);
        Onepacks=findViewById(R.id.Onepacks);
        twenty_bar=findViewById(R.id.twenty_bar);
        Ten_bar=findViewById(R.id.Ten_bar);
    }

    public void onCLick(View view) {

        switch (view.getId()){
            case R.id.Threepacks:
            view(1);
                break;
            case R.id.Twopacks:
                view(2);
                break;
            case R.id.Onepacks:
                view(3);
                break;
            case R.id.twenty_bar:
                view(4);
                break;
            case R.id.Ten_bar:
                view(5);
                break;


            case R.id.next:
                 Toast.makeText(getApplicationContext(),""+town,Toast.LENGTH_SHORT).show();
                if(town==0){
                   show();
                   break;
                }
                pash(town);

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_left);
                finish();
                break;
        }

    }


public void show(){
    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
            context);

    // 제목셋팅
    alertDialogBuilder.setTitle("경고!");

    // AlertDialog 셋팅
    alertDialogBuilder
            .setMessage("하루 흡연량을 선택해주세요")
            .setCancelable(false);


    // 다이얼로그 생성
    AlertDialog alertDialog = alertDialogBuilder.create();
    alertDialog.setCanceledOnTouchOutside(true);
    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(255,255,116,115)));
    // 다이얼로그 보여주기
    alertDialog.show();
}


    public void pash(int num){


    switch (num){
        case 1:

            //13500

            break;
        case 2:
            //9000
            break;
        case 3:
            //4500
            break;
        case 4:
            //2500
            break;
        case 5:
            //1500
            break;
    }
    }



    public void view(int num){

        town=num;

        switch (num){

            case 1:
                Toast.makeText(getApplicationContext(),"ONE",Toast.LENGTH_SHORT).show();
                Threepacks.setSelected(true);
                Twopacks.setSelected(false);
                Onepacks.setSelected(false);
                twenty_bar.setSelected(false);
                Ten_bar.setSelected(false);
                break;
            case 2:
                Toast.makeText(getApplicationContext(),"TWO",Toast.LENGTH_SHORT).show();
                Threepacks.setSelected(false);
                Twopacks.setSelected(true);
                Onepacks.setSelected(false);
                twenty_bar.setSelected(false);
                Ten_bar.setSelected(false);
                break;
            case 3:
                Toast.makeText(getApplicationContext(),"TRUEE",Toast.LENGTH_SHORT).show();
                Threepacks.setSelected(false);
                Twopacks.setSelected(false);
                Onepacks.setSelected(true);
                twenty_bar.setSelected(false);
                Ten_bar.setSelected(false);
                break;
            case 4:
                Toast.makeText(getApplicationContext(),"FOUR",Toast.LENGTH_SHORT).show();
                Threepacks.setSelected(false);
                Twopacks.setSelected(false);
                Onepacks.setSelected(false);
                twenty_bar.setSelected(true);
                Ten_bar.setSelected(false);
                break;
            case 5:
                Toast.makeText(getApplicationContext(),"TWICE",Toast.LENGTH_SHORT).show();
                Threepacks.setSelected(false);
                Twopacks.setSelected(false);
                Onepacks.setSelected(false);
                twenty_bar.setSelected(false);
                Ten_bar.setSelected(true);
                break;
        }



    }
}

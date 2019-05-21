package cs.inhatc.khac.khac_where_is_leisure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*Dvelop By KimAYoung*/
public class AY_MainPage extends AppCompatActivity implements View.OnClickListener{

    private Button btnTrip, btnMypage, btnAfterTrip;
    private JU_PermissionCall PC;
    private boolean m_bTrackingMode;

    //뒤로가기 버튼 Delay
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ay_mainpage);

         btnTrip=(Button)findViewById(R.id.btnTrip);
         btnMypage=(Button)findViewById(R.id.btnMypage);
         btnAfterTrip=(Button)findViewById(R.id.btnAfterTrip);

        btnTrip.setOnClickListener(this);
        btnMypage.setOnClickListener(this);
        btnAfterTrip.setOnClickListener(this);

        // 취소버튼 관련 변수
        m_bTrackingMode = true;

        // Permission 확인
        //PC = new JU_PermissionCall(this);
    }

    // 이전버튼 이벤트
    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(this, "이전 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View v){
        if(v==btnTrip){
            Intent intent0 = new Intent(AY_MainPage.this, EB_Choiceloc.class );
            startActivity(intent0);
        }
        else if(v==btnMypage){
            Intent intent1 = new Intent(AY_MainPage.this, AY_Mypage.class );
            startActivity(intent1);
        }

        else if(v==btnAfterTrip){
            Intent intent2 = new Intent(AY_MainPage.this, AY_AfterTrip.class );
            startActivity(intent2);
        }
    }
}

package cs.inhatc.khac.khac_where_is_leisure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/*Dvelop By KimAYoung*/
public class AY_Mypage extends AppCompatActivity implements View.OnClickListener {

    private Button btnBack, btnck1, btnck2, btnck3, btnck4, btnck5;
    //static final String[] LIST_MENU = {"챙길것 - 우산, 충전기, 카메라..", "사올것 - 초콜릿, 떡, 화장품", "회비 - 50,000", "티켓 꼭 챙기기!", "미리 연락 하기~"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ay_mypage);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnck1 = (Button) findViewById(R.id.btnck1);
        btnck1.setOnClickListener(this);
        btnck2 = (Button) findViewById(R.id.btnck2);
        btnck2.setOnClickListener(this);
        btnck3 = (Button) findViewById(R.id.btnck3);
        btnck3.setOnClickListener(this);
        btnck4 = (Button) findViewById(R.id.btnck4);
        btnck4.setOnClickListener(this);
        btnck5 = (Button) findViewById(R.id.btnck5);
        btnck5.setOnClickListener(this);

        /*
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, LIST_MENU);

        ListView listview_memo = (ListView) findViewById(R.id.listview_memo);
        listview_memo.setAdapter(adapter);
        */


    }

    public void onClick(View v) {
        if (v == btnBack) {
            Intent intent = new Intent(AY_Mypage.this, AY_MainPage.class);
            startActivity(intent);
        } else if (v == btnck1) {
            startActivity(new Intent(AY_Mypage.this, AY_Dialog.class));
        }else if (v == btnck5) {
            startActivity(new Intent(AY_Mypage.this, AY_AfterTripMake.class));
        }

    }
}

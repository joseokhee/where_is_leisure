package cs.inhatc.khac.khac_where_is_leisure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/*Dvelop By KimAYoung*/
public class AY_AfterTrip extends AppCompatActivity implements View.OnClickListener{

    ListView listview = null ;

    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ay_aftertrip);

        btnBack=(Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        AY_ListViewAdapter adapter;

        // Adapter 생성
        adapter = new AY_ListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.beachload),
                "죽도 해안 산책로", "경상북도 / 울릉군 ulleng\r\n 해안따라 들리는 바다소리가 너무 이뻤어요~!\r\n");

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ecoland),
                "에코랜드", "제주도 / 조천읍 jejudo \r\n한번 더 타고싶다~");

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.hanrasumok),
                "인천 수목원", "인천 / 남동구 inchen \r\n데이트 장소로도 딱이에요");

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.loveland),
                "러브랜드", "제주도 / 제주시 jejudo \r\n!!청소년 출입금지!!");

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.oramemil),
                "메밀꽃밭", "강원도 / 춘천시 gangwon \r\n메밀꽃이 만발할때 갔더니 환상적이었어요");

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.swiss),
                "스위스 마을", "제주도 /조천읍 jejudo \r\n스위스 풍의 아기자기한 분위기가 사진찍기 딱좋아요!");


        EditText editTextFilter = (EditText)findViewById(R.id.editTextFilter) ;
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString() ;
                /*
                if (filterText.length() > 0) {
                    listview.setFilterText(filterText) ;
                }
                else {
                    listview.clearTextFilter() ;
                }
                */
                ((AY_ListViewAdapter)listview.getAdapter()).getFilter().filter(filterText) ;

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        }) ;

    }
    public void onClick(View v) {
        if (v == btnBack) {
            Intent intent = new Intent(AY_AfterTrip.this, AY_MainPage.class);
            startActivity(intent);
        }
    }
}



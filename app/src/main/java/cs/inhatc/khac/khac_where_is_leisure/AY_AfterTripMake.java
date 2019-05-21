package cs.inhatc.khac.khac_where_is_leisure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/*Dvelop By KimAYoung*/
public class AY_AfterTripMake extends AppCompatActivity implements View.OnClickListener{

    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ay_after_trip_make);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
    }
    public void onClick(View v) {
        if (v == btnBack) {
            Intent intent = new Intent(AY_AfterTripMake.this, AY_Mypage.class);
            startActivity(intent);
        }
    }
}

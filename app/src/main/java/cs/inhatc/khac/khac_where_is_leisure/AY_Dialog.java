package cs.inhatc.khac.khac_where_is_leisure;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*Dvelop By KimAYoung*/
public class AY_Dialog extends Activity implements View.OnClickListener{
    private Button btnclose;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ay_dialog);

        btnclose = findViewById(R.id.btnclose);
        btnclose.setOnClickListener(this);
    }
    public void onClick(View v) {
        if (v == btnclose) {
            this.finish();
        }
    }
}

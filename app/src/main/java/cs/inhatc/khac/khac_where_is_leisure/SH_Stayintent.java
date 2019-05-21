package cs.inhatc.khac.khac_where_is_leisure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/* Develop By JoSeokHee */
public class SH_Stayintent extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private TextView first;
    private TextView second;
    private TextView third;
    private TextView forth;
    private EditText search;
    private Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sh_stayintent);

        first = (TextView) findViewById(R.id.textView1);
        second = (TextView) findViewById(R.id.textView2);
        third = (TextView) findViewById(R.id.textView3);
        forth = (TextView) findViewById(R.id.textView4);
        search = (EditText) findViewById(R.id.search);
        cancel=(Button)findViewById(R.id.cancel);

        first.setVisibility(View.INVISIBLE);
        second.setVisibility(View.INVISIBLE);
        third.setVisibility(View.INVISIBLE);
        forth.setVisibility(View.INVISIBLE);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (search.getText().toString().equals("별빛")) {
                    first.setVisibility(View.VISIBLE);
                    second.setVisibility(View.VISIBLE);
                    third.setVisibility(View.VISIBLE);
                    forth.setVisibility(View.INVISIBLE);
                    first.setText("별빛펜션\n주소 : 충청북도 충주시 매현읍");
                    second.setText("별이 빛나는 방\n주소 : 부산광역시 해운대구");
                    third.setText("별빛나라\n주소 : 강원도 가평");
                } else if (search.getText().toString().equals("해변")) {
                    first.setVisibility(View.VISIBLE);
                    second.setVisibility(View.VISIBLE);
                    third.setVisibility(View.VISIBLE);
                    forth.setVisibility(View.VISIBLE);

                    first.setText("비치해변\n주소 : 인천광역시 을왕리");
                    second.setText("해변발리\n주소 : 강원도 양양");
                    third.setText("반짝해변펜션\n주소 : 제주도 애월읍");
                    forth.setText("감자해변\n주소 : 강원도 속초");
                }
            }
        };
        search.addTextChangedListener(textWatcher);
        first.setOnClickListener(this);
        second.setOnClickListener(this);
        third.setOnClickListener(this);
        forth.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }




    @Override
    public void onClick(View view) {
        if (view == first) {
            Intent change=new Intent(SH_Stayintent.this,SH_ChoiceStayActivity.class);
            change.putExtra("lod",first.getText().toString());
            setResult(0,change);
            finish();
        }
        else if(view==second){
            Intent change=new Intent(SH_Stayintent.this,SH_ChoiceStayActivity.class);
            change.putExtra("lod",second.getText().toString());
            setResult(0,change);
            finish();
        }
        else if(view==third){
            Intent change=new Intent(SH_Stayintent.this,SH_ChoiceStayActivity.class);
            change.putExtra("lod",third.getText().toString());
            setResult(0,change);
            finish();
        }
        else if(view==forth){
            Intent change=new Intent(SH_Stayintent.this,SH_ChoiceStayActivity.class);
            change.putExtra("lod",forth.getText().toString());
            setResult(0,change);
            finish();
        }
        else if(view==cancel) {
            setResult(5);
            finish();
        }
    }
}

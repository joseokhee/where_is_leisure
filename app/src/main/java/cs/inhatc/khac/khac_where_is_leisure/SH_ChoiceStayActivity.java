package cs.inhatc.khac.khac_where_is_leisure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/* Develop By JoSeokHee */
public class SH_ChoiceStayActivity extends AppCompatActivity implements View.OnClickListener{
    private int days=3;
    private Spinner spinner;
    private int p_day=0;
    private TextView day;
    private Button next;
    private Button pre;
    private TextView t_days;
    private int count=0;
    private TextView r;
    private int pre_n=0;
    private EditText lod;
    private String t_r="";
    private TextView suk;
    private Intent intent;
    private String content;
    private CheckBox option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sh_choicestay);

        option=(CheckBox)findViewById(R.id.checkBox);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                suk.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
            }
        };

        day=(TextView)findViewById(R.id.day);
        spinner=(Spinner)findViewById(R.id.spinner);
        next=(Button)findViewById(R.id.next);
        pre=(Button)findViewById(R.id.pre);
        t_days=(TextView)findViewById(R.id.days);
        r=(TextView)findViewById(R.id.list);
        lod=(EditText)findViewById(R.id.lodging);
        suk=(TextView)findViewById(R.id.sukbak);

        lod.addTextChangedListener(textWatcher);



        t_days.setText(days+"박"+(days+1)+"일");
        next.setVisibility(View.INVISIBLE);

        final ArrayList<String> list=new ArrayList<>();
        list.add("선택");
        for(int i=0;i<days-1;i++){
            list.add(i+1+"박");
        }
        list.add("전체");
        final ArrayAdapter spinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(spinnerAdapter);
        suk.setVisibility(View.INVISIBLE);
        spinner.setVisibility(View.INVISIBLE);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if (p_day != days) {
                            if(days-p_day<i){
                                Toast.makeText(SH_ChoiceStayActivity.this,"선택 할 수 없습니다.", Toast.LENGTH_LONG).show();
                                return;
                            }
                            p_day += i;
                            day.setText((p_day + 1) + "번째 날");
                            if(i>0) {

                                t_r += i + "박 : " + lod.getText().toString() + "\n";
                                r.setText(t_r);
                                spinner.setSelection(0);
                                lod.setText("");
                                suk.setVisibility(View.INVISIBLE);
                                spinner.setVisibility(View.INVISIBLE);

                            }
                        } else {
                            next.setVisibility(View.VISIBLE);
                            spinner.setVisibility(View.INVISIBLE);
                            day.setVisibility(View.INVISIBLE);
                            lod.setVisibility(View.INVISIBLE);
                            suk.setVisibility(View.INVISIBLE);
                            spinner.setVisibility(View.INVISIBLE);
                        }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
        lod.setOnClickListener(this);
        next.setOnClickListener(this);
        pre.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==lod){
            Intent search=new Intent(SH_ChoiceStayActivity.this,SH_Stayintent.class);
            startActivityForResult(search,0);
        }
        else if(v==next){
            Intent next=new Intent(SH_ChoiceStayActivity.this,SY_TouristActivity.class);
            next.putExtra("throw",t_r);
            next.putExtra("check",option.isChecked());
            startActivity(next);
        }
        else if(v==pre){
            finish();
        }
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch (resultCode) {
            case 0:
                String content = data.getStringExtra("lod");
                lod.setText(content);
                break;

            default:
                break;
        }
    }
}

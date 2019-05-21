package cs.inhatc.khac.khac_where_is_leisure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

/* Develop By Eunbee-Shin */
public class EB_Choiceloc extends AppCompatActivity {

    ArrayAdapter<CharSequence> ad_spin1, ad_spin2;
    String choice_do="";
    String choice_si="";

    ImageView iv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eb_choiceloc);

        final Spinner spin1 = findViewById(R.id.spinner2);
        final Spinner spin2 = findViewById(R.id.spinner);

        Button button1 = findViewById(R.id.button);

        iv = findViewById(R.id.imageView);

        Button button2 = findViewById(R.id.button3);

        Button button3 = findViewById(R.id.button2);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EB_Choiceloc.super.onBackPressed();
            }
        });

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                spin1.setSelection(1);
            }
        });


        ad_spin1 = ArrayAdapter.createFromResource(this,R.array.do_array,android.R.layout.simple_spinner_dropdown_item);

        spin1.setAdapter(ad_spin1);

        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(ad_spin1.getItem(i).equals("충청도")){
                    choice_do = "충청도";
                    ad_spin2 = ArrayAdapter.createFromResource(EB_Choiceloc.this,R.array.ccd_array,android.R.layout.simple_spinner_dropdown_item);
                    ad_spin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(ad_spin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if(ad_spin2.getItem(i).equals("충주시")){
                                iv.setImageResource(R.drawable.cj1);
                            }
                            choice_si = ad_spin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                else if(ad_spin1.getItem(i).equals("제주도")){
                    ad_spin2 = ArrayAdapter.createFromResource(EB_Choiceloc.this,R.array.no_array,android.R.layout.simple_spinner_dropdown_item);
                    ad_spin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(ad_spin2);
                    iv.setImageResource(R.drawable.jj1);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_si = ad_spin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                else if(ad_spin1.getItem(i).equals("인천광역시")){
                    ad_spin2 = ArrayAdapter.createFromResource(EB_Choiceloc.this,R.array.no_array,android.R.layout.simple_spinner_dropdown_item);
                    ad_spin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(ad_spin2);
                    iv.setImageResource(R.drawable.ic2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_si = ad_spin2.getItem(i).toString();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Spinner spin3 = findViewById(R.id.spinner3);
                Spinner spin_do = findViewById(R.id.spinner2);
                Spinner spin_si = findViewById(R.id.spinner);

                String date = spin3.getSelectedItem().toString();
                String doice = spin_do.getSelectedItem().toString();
                String siice = spin_si.getSelectedItem().toString();

                Character date2 = date.charAt(0);

                Integer days = (int)date2;

                if(spin2.getItemAtPosition(0).equals("(선택불가)")){
                    Toast.makeText(EB_Choiceloc.this, "여행지가 어디인가요?", Toast.LENGTH_LONG).show();
                }

                else{
                    Intent intent = new Intent(getApplicationContext(),SH_ChoiceStayActivity.class);
                    intent.putExtra("choice_do",doice);
                    intent.putExtra("choice_si",siice);
                    intent.putExtra("days",days);
                    startActivity(intent);
                }
            }
        });



    }
}

package cs.inhatc.khac.khac_where_is_leisure;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/* Develop By Jeon Se Yeon */
public class SY_TouristActivity extends AppCompatActivity {
    private ListView listview;
    private CustomChoiceListViewAdapter adapter;
    final Context context = this;
    private ArrayList<SY_ListViewItem> listViewItemSYList = new ArrayList<SY_ListViewItem>();

    /*앱이 실행됬을 때*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sy_tourist);

        // Adapter 생성
        adapter = new CustomChoiceListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.list_main);
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listview.setAdapter(adapter);

        //리스트 추가
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.what),
                "사라오름");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.what),
                "새별오름");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.what),
                "성산일출봉");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.what),
                "정방폭포");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.what),
                "러브랜드");

    }

   /* 이미지 버튼을 클릭했을 때 -> 팝업*/
    public void Popup(View v) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        LayoutInflater inflater
                = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.activity_sy_popup, null);

        /*관광지명, 사진, 설명*/
        TextView tourName = (TextView) layout.findViewById(R.id.txtTourName);
        tourName.setText("사라오름");
        ImageView imgTour = (ImageView) layout.findViewById(R.id.imgTour);
        imgTour.setImageResource(R.drawable.sara);
        TextView tourExplain = (TextView) layout.findViewById(R.id.txtExplain);
        tourExplain.setText("노루떼가 뛰어노는 모습이 한 폭의 그림 같은 곳");

        /*먹거리사진, 설명*/
        ImageView imgOther1 = (ImageView) layout.findViewById(R.id.imgOther1);
        imgOther1.setImageResource(R.drawable.seoyeon);
        TextView txtOther1 = (TextView) layout.findViewById(R.id.txtOther1);
        txtOther1.setText("서연의 집: \n" +
                "넓은 창 밖으로 바다가 보이는 카페. 건축학개론 촬영지로 더 유명한 카페. 납뜩이 머핀, 스토리텔링 떡 등 독특한 이름의 메뉴들이 인기 메뉴");

        /*축제사진, 설명*/
        ImageView imgOther2 = (ImageView) layout.findViewById(R.id.imgOther2);
        imgOther2.setImageResource(R.drawable.suguk);
        TextView txtOther2 = (TextView) layout.findViewById(R.id.txtOther2);
        txtOther2.setText("휴애리 여름 수국 축제: \n" +
                "먹거리 - 솜사탕, 흑돼지빵, 소시지 \n" +
                "상철체험프로그램 - 동물먹이주기체험(산토끼, 흑염소, 산양, 염소, 말 등), 흑돼지야놀자(흑돼지쇼), 승마체험, 야생화 자연 학습체험, 전통놀이체험(돌탑쌓기, 투호던지기, 굴렁쇠 체험 등)");

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 프로그램 종료

                            }
                        });
        alertDialogBuilder.setView(layout);
        // 다이얼로그 생성
        AlertDialog alertDialog = alertDialogBuilder.create();
        // 다이얼로그 보여주기
        alertDialog.show();
    }

    /*[다음]버튼을 눌렀을 때*/
    public void btnOk(View v) {
        int count = 0;
        SparseBooleanArray countCheckBox = listview.getCheckedItemPositions();
        int size = adapter.getCount();
        ArrayList<String> ReceiveArr= new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (countCheckBox.valueAt(i) == true) {
                count++;
               int index = countCheckBox.keyAt(i);

                ReceiveArr.add(listViewItemSYList.get(index).getText());
                        Toast toasts = Toast.makeText(getApplicationContext(), "선택한 관광지: "+ listViewItemSYList.get(index).getText(), Toast.LENGTH_SHORT);
                toasts.show();
            }
        }

        if (count == 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "관광지를 1개의 항목 이상 선택해주세요.", Toast.LENGTH_SHORT);
            toast.show();
        }

        else {
            Intent intent = new Intent(this, JU_MapActivity.class);
            intent.putStringArrayListExtra("ArrayList", ReceiveArr);

            startActivity(intent);
        }
    }


    public class CustomChoiceListViewAdapter extends BaseAdapter {

        /*ListViewAdapter의 생성자*/
        public CustomChoiceListViewAdapter() {

        }

       /* Adapter에 사용되는 데이터의 개수를 리턴*/
        @Override
        public int getCount() {
            return listViewItemSYList.size();
        }

        /*position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴*/
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int pos = position;
            final Context context = parent.getContext();

           /* "listview_item" Layout을 inflate하여 convertView 참조 획득*/
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_sy_checkbox_row, parent, false);
            }

            /*화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득*/
            ImageButton iconImageView = (ImageButton) convertView.findViewById(R.id.imgBtnWhat);
            TextView textTextView = (TextView) convertView.findViewById(R.id.txtName);


            // Data Set(listViewItemSYList)에서 position에 위치한 데이터 참조 획득
            SY_ListViewItem SYListViewItem = listViewItemSYList.get(position);

            // 아이템 내 각 위젯에 데이터 반영
            iconImageView.setImageDrawable(SYListViewItem.getIcon());
            textTextView.setText(SYListViewItem.getText());

            return convertView;
        }


        // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
        @Override
        public long getItemId(int position) {
            return position;
        }

        // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
        @Override
        public Object getItem(int position) {
            return listViewItemSYList.get(position);
        }

        // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
        public void addItem(Drawable icon, String text) {
            SY_ListViewItem item = new SY_ListViewItem();
            item.setIcon(icon);
            item.setText(text);

            listViewItemSYList.add(item);
        }

    }
}






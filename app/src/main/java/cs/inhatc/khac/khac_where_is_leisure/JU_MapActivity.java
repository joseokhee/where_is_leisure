package cs.inhatc.khac.khac_where_is_leisure;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/* Develop By Jinuk-Ha */
public class JU_MapActivity extends AppCompatActivity implements View.OnClickListener {

    TMapPoint tMapPoint1 = new TMapPoint(37.4588197, 126.63411719999999); // 인하대병원
    TMapPoint tMapPoint2 = new TMapPoint(37.4500221, 126.65348799999992); // 인하대
    TMapPoint tMapPoint3 = new TMapPoint(37.4480158, 126.65750409999998); // 인하공전
    TMapPoint tMapPoint4 = new TMapPoint(37.441546, 126.70149600000002); // 인천시외버스터미널
    TMapPoint tMapPoint5 = new TMapPoint(37.4565562, 126.68458069999997); // 주안역

    private TMapView tMapView ;
    private TMapData tMapData;
    private ArrayList passList;
    private ImageView gpsi;
    private Button btnPre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ju_map);
        LinearLayout linearLayoutTmap = findViewById(R.id.linearLayoutTmap);

        btnPre = findViewById(R.id.map_pre);
        btnPre.setOnClickListener(this);

        // TMap 지도 생성 관련 변수
        tMapView = new TMapView(this);
        // TMap에서 사용되는 데이터 관련 변수
        tMapData = new TMapData();

        // Permission 확인
        //PC = new JU_PermissionCall(this);

        // 지도 생성
        tMapView.setSKTMapApiKey(getString(R.string.tmap_api_key));
        linearLayoutTmap.addView(tMapView);

        // 경유지 추가를 위한 ArrayList
        passList = new ArrayList<>();
        passList.add(tMapPoint2);
        passList.add(tMapPoint3);
        passList.add(tMapPoint4);

        // GPS 아이콘 생성
        gpsi = findViewById(R.id.gps_icon);

        // GPS 아이콘 클릭 시 이벤트
        gpsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 권한 확인
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1); //위치권한 탐색 허용 관련 내용
                    }
                    return;
                }
                setGps(); // 현재위치 찾기
                Toast.makeText(getApplicationContext(), "현재위치를 찾는 중입니다. GPS를 켜주세요.", Toast.LENGTH_SHORT).show();
            }
        });

        // 시작위치 설정
        tMapView.setCenterPoint(126.63411719999999,37.4588197);

        // 경로 검색 이벤트
        findPathDataWithType();
    }

    // 경로찾기 메서드
    public void findPathDataWithType() {
        tMapData.findPathDataWithType(TMapData.TMapPathType.CAR_PATH, tMapPoint1, tMapPoint5, passList, 0,
                new TMapData.FindPathDataListenerCallback() {
                    @Override
                    public void onFindPathData(TMapPolyLine polyLine) {
                        polyLine.setLineColor(Color.GREEN);
                        polyLine.setLineWidth(10);
                        tMapView.addTMapPath(polyLine);
                    }
                });
        findPathAllType();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.map_next :

                break;
            case R.id.map_pre :
                finish();
                break;
            default:
                break;
        }
    }

    // Type별 경로 거리
    public void findPathAllType() {
        tMapData.findPathDataAllType(TMapData.TMapPathType.CAR_PATH, tMapPoint1, tMapPoint5, new TMapData.FindPathDataAllListenerCallback() {
            @Override
            public void onFindPathDataAll(Document document) {
                Element root = document.getDocumentElement();
                NodeList nodeListPlacemark = root.getElementsByTagName("Document");
                for (int i = 0; i < nodeListPlacemark.getLength(); i++) {
                    NodeList nodeListPlacemarkItem = nodeListPlacemark.item(i).getChildNodes();
                    for (int j = 0; j < nodeListPlacemarkItem.getLength(); j++) {
                        if (nodeListPlacemarkItem.item(j).getNodeName().equals("tmap:totalDistance")) {
                            Log.d("Distance", nodeListPlacemarkItem.item(j).getTextContent().trim()+"M"); // 총 거리 표시(M)
                        }else if (nodeListPlacemarkItem.item(j).getNodeName().equals("tmap:totalTime")) {
                            int TM = Integer.parseInt(nodeListPlacemarkItem.item(j).getTextContent().trim())/60;
                            int TS = Integer.parseInt(nodeListPlacemarkItem.item(j).getTextContent().trim())%60;
                            Log.d("Time", TM+" min"+TS+" sec"); // 총 시간 표시(초)
                        }else if (nodeListPlacemarkItem.item(j).getNodeName().equals("tmap:totalFare")) {
                            Log.d("Money", nodeListPlacemarkItem.item(j).getTextContent().trim()+" Won"); // 총 요금 표시
                        }
                    }
                }
            }
        });
    }

    // GPS 얻어오기 메서드
    public void setGps() {
        final LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE}, 1);
        }
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자(실내에선 NETWORK_PROVIDER 권장)
                1000, // 통지사이의 최소 시간간격 (miliSecond)
                1, // 통지사이의 최소 변경거리 (m)
                mLocationListener);
    }

    // 현재위치 관련 메서드
    private final LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {

            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                /* 해당 위치에 포인트 찍기 */
                tMapView.setLocationPoint(longitude, latitude);

                /* 해당 위치의 중앙으로 화면 이동 */
                tMapView.setCenterPoint(longitude, latitude);

                /* 현재 보는 방향표시 */
                tMapView.setCompassMode(true);

                /* 현위치 아이콘표시 */
                tMapView.setIconVisibility(true);
            }

        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };
}

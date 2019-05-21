package cs.inhatc.khac.khac_where_is_leisure;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;

/* Develop By Jeon Se Yeon */
public class SY_CheckableLinearLayout extends LinearLayout implements Checkable {

    // 만약 CheckBox가 아닌 View를 추가한다면 아래의 변수 사용 가능.
    // private boolean mIsChecked ;

    public SY_CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        // mIsChecked = false ;
    }



    @Override
    public boolean isChecked() {
        CheckBox cb = (CheckBox) findViewById(R.id.chbtour) ;

        return cb.isChecked() ;
        // return mIsChecked ;
    }

    @Override
    public void toggle() {
        CheckBox cb = (CheckBox) findViewById(R.id.chbtour) ;

        setChecked(cb.isChecked() ? false : true) ;
        // setChecked(mIsChecked ? false : true) ;
}




    @Override
    public void setChecked(boolean checked) {
        CheckBox cb = (CheckBox) findViewById(R.id.chbtour) ;
        if (cb.isChecked()==true) {
            cb.setChecked(false);
        }
        else  {
            cb.setChecked(checked) ;


            }
        }

        // CheckBox 가 아닌 View의 상태 변경.
    }


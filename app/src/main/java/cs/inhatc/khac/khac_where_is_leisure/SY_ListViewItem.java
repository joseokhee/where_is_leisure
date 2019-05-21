package cs.inhatc.khac.khac_where_is_leisure;

import android.graphics.drawable.Drawable;

/* Develop By Jeon Se Yeon */
public class SY_ListViewItem {
    private Drawable icon ;
    private String text ;

    public void setIcon(Drawable icon) {
        this.icon = icon ;
    }
    public void setText(String text) {
        this.text = text ;
    }

    public Drawable getIcon() {
        return this.icon ;
    }

    public String getText() {
        return this.text ;
    }
    //파라메타값이 배열로된것을 사용함


}

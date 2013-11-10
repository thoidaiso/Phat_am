package com.example.phat_am;



import com.example.phat_am.TwoTextArrayAdapter.RowType;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ListItem implements Item {
    private final String str1;
    private final Integer  icon;
    private final Context context;

    public ListItem(String text1, Integer image1, Context context) {
        this.str1 = text1;
        this.icon = image1;
        this.context = context;
    }

    @Override
    public int getViewType() {
        return RowType.LIST_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        View view;
        if (convertView == null) {
            view = inflater.inflate(R.layout.drawer_list_item, null);
            // Do some initialization
        } else {
            view = convertView;
        }
        TextView text1 = (TextView) view.findViewById(R.id.navi_menu_text);
        ImageView image1 = (ImageView) view.findViewById(R.id.navi_menu_icon);
        text1.setText(str1);
//        Typeface tf = Typeface.createFromAsset(context.getAssets(),"fonts/VNARIAL_0.TTF");
//        text1.setTypeface(tf);
        image1.setImageResource(icon);
        
        View divider =view.findViewById(R.id.divider);
        if ( text1.equals("Liên hệ"))
        	divider.setBackgroundResource(R.color.nav_background);
//        #797679
        return view;
    }

}

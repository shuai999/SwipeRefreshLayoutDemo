package com.jackchen.swipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @email :  2185134304@qq.com
 * @date :2017/12/23
 * @author : Jack-Chen
 * @Description: SwipeRefreshLayout
 *
 */
public class LVAdapter extends BaseAdapter {

    private Context mContext ;
    private List<String> mDatas ;

    public LVAdapter(Context context , List<String> datas){
        this.mContext = context ;
        this.mDatas = datas ;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv , null) ;

            holder = new ViewHolder() ;

            holder.text = (TextView)convertView.findViewById(R.id.text) ;

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(mDatas.get(position));
        return convertView;
    }

    public class ViewHolder{
        TextView text ;
    }
}

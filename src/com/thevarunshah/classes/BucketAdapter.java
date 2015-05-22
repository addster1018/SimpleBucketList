package com.thevarunshah.classes;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.thevarunshah.simplebucketlist.R;

public class BucketAdapter extends ArrayAdapter<BucketItem> {

	private ArrayList<BucketItem> bucketList;
	private Context context;

	public BucketAdapter(Context context, int textViewResourceId, ArrayList<BucketItem> bucketList) {
		
		super(context, textViewResourceId, bucketList);
		
		this.context = context;
		this.bucketList = bucketList;
	}

	private class ViewHolder {
		
		CheckBox done;
		TextView goal;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;

		if (convertView == null) {
			
			LayoutInflater vi = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.row, null);

			holder = new ViewHolder();
			holder.goal = (TextView) convertView.findViewById(R.id.row_text);
			holder.done = (CheckBox) convertView.findViewById(R.id.row_check);
			convertView.setTag(holder);

			holder.done.setOnClickListener(new View.OnClickListener() {  
				public void onClick(View v) {  
					CheckBox cb = (CheckBox) v;  
					BucketItem item = (BucketItem) cb.getTag(); 
					item.setDone(cb.isChecked());
				}  
			});
		} 
		else {
			holder = (ViewHolder) convertView.getTag();
		}

		BucketItem item = bucketList.get(position);
		holder.goal.setText(item.getGoal());
		holder.done.setChecked(item.isDone());
		holder.done.setTag(item);

		return convertView;
	}
}
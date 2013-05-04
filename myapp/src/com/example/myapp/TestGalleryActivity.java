package com.example.myapp;

import com.android.uti.NetWork;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class TestGalleryActivity extends Activity {
	/** Called when the activity is first created. */
	private RatingBar rb;
	public int chosenId = 0;
	private Button bSubmit;
	public TextView tv_test;
	public int privacy;
	Gallery gallery;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		// str123(Data.SurveyQue);
		privacy = getIntent().getIntExtra("privacy", 0);
		Data.privacy = Integer.toString(privacy + 1);
		findViews();
		setUp();

	}

	private void setUp() {
		bSubmit.setOnClickListener(submitButtonProcess);
		rb = (RatingBar) findViewById(R.id.ratingBar1);
		rb.setNumStars(5);
		rb.setRating(0);
		rb.setStepSize((float) 1.0);
		rb.setOnRatingBarChangeListener(rbLis);
		Data.RatingList = new String[Data.dataList.size()];
		Data.RatingListDou = new Float[Data.dataList.size()];
		for (int i = 0; i < Data.dataList.size(); i++) {
			Data.RatingListDou[i] = (float) 5.0;
		}
		gallery.setAdapter(new galleryAdapter(this));
		gallery.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(TestGalleryActivity.this, "" + id + "onclick",
						Toast.LENGTH_SHORT).show();
				chosenId = position;
				rb.setRating(Data.RatingListDou[chosenId]);
				tv_test.setText("chosen" + chosenId);
			}
		});
		gallery.setSelection(1);
		gallery.setSpacing(20);
		gallery.setUnselectedAlpha(150.0f);
	}

	private void findViews() {
		tv_test = (TextView) findViewById(R.id.text_view_test);
		bSubmit = (Button) findViewById(R.id.submit1);
		gallery = (Gallery) findViewById(R.id.myGallery);
	}

	public void str123(String[] str) {
		for (int i = 0; i < 5; i++) {

			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < 50; j++) {

				sb.append("Survey" + String.valueOf(i));
			}
			str[i] = sb.toString();
		}
	}

	private OnClickListener submitButtonProcess = new OnClickListener() {
		public void onClick(View v) {

			// then tcp connection to server
			NetWork nw = new NetWork();
			nw.disable_policy();

			Data.lines = nw.http10TcpSendAndRec(nw.http_request(Data.host,
					"/survey/8/" + Data.login_username + "/"
							+ Data.login_password + "/" + Data.privacy
							+ "/session/"));
			for (int i = 0; i < Data.dataList.size(); i++) {
				Data.RatingList[i] = Double.toString(Data.RatingListDou[i]
						+ nw.gaussianrvGen(privacy));
				Data.lines1 = nw.http10TcpSendAndRec(nw.http_request(Data.host,
						"/survey/" + Integer.toString(Data.dataList.get(i).getID()) + "/"
								+ Data.RatingList[i] + "/"
								+ Data.login_username + "/"
								+ Data.login_password + "/response/"));
			}
			Data.lines1[1] = nw.http_request(Data.host, "/survey/8/"
					+ Data.login_username + "/" + Data.login_password + "/"
					+ Data.privacy + "/session/");
			// Intent intent = new Intent();
			// intent.setClass(TestGalleryActivity.this, MainActivity.class);
			// startActivity(intent);
			finish();

		}
	};

	public String f2Int2Str(float f) {
		return Integer.toString((int) f);
	}

	private OnRatingBarChangeListener rbLis = new OnRatingBarChangeListener() {

		public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
			NetWork nw = new NetWork();
			String rate;
			// ratingList[chosenId]=f2Int2Str(rating);
			Data.RatingListDou[chosenId] = rating;
			Data.RatingList[chosenId] = Double.toString(rating
					+ nw.gaussianrvGen(privacy));

			tv_test.setText("original rating:" + rating + ",chosenId:"
					+ chosenId + "\n,private rating:"
					+ Data.RatingList[chosenId]);
		}

	};

	public class galleryAdapter extends BaseAdapter {
		private Context mContext;

		public galleryAdapter(Context c) {
			mContext = c;

		}

		public int getCount() {
			// TODO Auto-generated method stub
			return Data.dataList.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		/*
		 * public View getView(int position, View convertView, ViewGroup parent)
		 * { // TODO Auto-generated method stub ViewHolder holder;
		 * 
		 * holder = new ViewHolder(); LayoutInflater mInflater =
		 * LayoutInflater.from(mContext); View view; view =
		 * mInflater.inflate(R.layout.pic_text, null); // convertView =
		 * View.inflate(mContext, R.layout.pic_text, null); holder.text =
		 * (TextView)view.findViewById(R.id.textl); //
		 * holder.text.setMovementMethod(new ScrollingMovementMethod()); //
		 * convertView.setTag(holder);
		 * 
		 * holder.text.setText(str[position]); return holder.text; }
		 * 
		 * class ViewHolder { public TextView text; }
		 */

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			TextView tv = new TextView(mContext);
			// View view = View.inflate(mContext, R.layout.pic_text, null);
			// TextView tv;
			// tv=(TextView) view.findViewById(R.id.text);

			tv.setHeight(600);
			tv.setWidth(300);
			tv.setMaxLines(20);

			// tv.setMovementMethod(new ScrollingMovementMethod());

			tv.setText(Data.dataList.get(position).getQuestion());
			return tv;
		}

	}
}
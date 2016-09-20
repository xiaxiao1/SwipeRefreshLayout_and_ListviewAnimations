package com.xiaxiao.xappdbg_test;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class MainActivity extends AppCompatActivity implements OnClickListener,View.OnTouchListener {

    public String ss = "sss";
    int currentIndex=0;
    int direct=Integer.MAX_VALUE;
    ListView listview;
    View header;
    TextView panel;
    TextView panel2;
    TextView panel3;
    TextView panel4;
    View v1;
    View v2;
    View v3;
    List<String> list;
    SwipeRefreshLayout srfl;
    MyAdapter adapter;
    boolean onRefreshing=false;
    Handler h;
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        h=new Handler(){
            @Override
            public void handleMessage(Message message) {
                adapter.notifyDataSetChanged();
                srfl.setRefreshing(false);
                onRefreshing=false;
            }
        };
        Timer t=new Timer();
        TimerTask tk=new TimerTask(){
            @Override
            public void run() {

            }
        };
        ScheduledExecutorService s = Executors.newScheduledThreadPool(1);
        srfl = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        refresh();

        header = (View) getLayoutInflater().inflate(R.layout.header,null);
        listview = (ListView) findViewById(R.id.listview);
        listview.addHeaderView(header);
        panel = (TextView) findViewById(R.id.main_tv);
        panel2 = (TextView) findViewById(R.id.main_tv2);
        panel2.setText(Html.fromHtml("dddddd\t\r\n\b<font color='#49a34b'>lishi</font><br>cccc"));
        panel3 = (TextView) findViewById(R.id.main_tv3);
        panel4 = (TextView) findViewById(R.id.main_tv4);
        v1 = (View) findViewById(R.id.v1);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        v2 = (View) findViewById(R.id.v2);
        v3 = (View) findViewById(R.id.v3);
        list=new ArrayList<String>();
        for (int i=0;i<200;i++) {
            list.add("BETTER "+i);
        }
        adapter = new MyAdapter(list);
        listview.setAdapter(adapter);
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {

            View vv;
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                vv = listview.getChildAt(0);
                if (vv == null) {
                    return;
                }
                if (firstVisibleItem > 0) {
                    panel.setVisibility(View.VISIBLE);
                    v1.setVisibility(View.VISIBLE);
                }
                else {
                    int h = Math.abs(vv.getTop());
                    if (h < 320) {
                        panel.setVisibility(View.GONE);
                        v1.setVisibility(View.GONE);
                    } else {
                        panel.setVisibility(View.VISIBLE);
                        v1.setVisibility(View.VISIBLE);
                    }
                    Log.i("xx", "dddd:  " + h);
                }

                if (firstVisibleItem>= 6) {
                    panel2.setVisibility(View.VISIBLE);
                    v2.setVisibility(View.VISIBLE);
                } else {
                    panel2.setVisibility(View.INVISIBLE);
                    v2.setVisibility(View.GONE);

                }
                if (firstVisibleItem>= 11) {
                    panel3.setVisibility(View.VISIBLE);
                    v3.setVisibility(View.VISIBLE);
                } else {
                    panel3.setVisibility(View.INVISIBLE);
                    v3.setVisibility(View.INVISIBLE);

                }
                if (firstVisibleItem>= 16) {
                    panel4.setVisibility(View.VISIBLE);

                } else {
                    panel4.setVisibility(View.INVISIBLE);

                }
            }
        });
        panel.setOnClickListener(this);
      //  listview.setOnClickListener(this);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Log.i("xx","info: "+list.get(position-1)+"  "+(position-1));
//                View v=listview.getChildAt(position);
                View v=view;
                currentIndex=position-1;
                slide(position, v, R.anim.translate_up_alpha, new OnAnimationEndListener() {
                    @Override
                    public void onEnd() {
                         list.remove(position-1);
                        adapter.notifyDataSetChanged();
                    }
                });

               /* list.remove(position-1);
                adapter.notifyDataSetChanged();*/
               // adapter.notifyDataSetInvalidated();
            }
        });
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "SS:"+ss, Toast.LENGTH_SHORT).show();
        if (v==panel) {
            Log.i("xx","i am panel");
        }
        if (v==btn1) {
//            srfl.setRefreshing(true);
            /*for (int i=1;i<5;i++) {
                View v1=listview.getChildAt(i);
                Animation a1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate_down);
                v1.startAnimation(a1);
                if (i==4) {
                   a1.setAnimationListener(new Animation.AnimationListener() {
                       @Override
                       public void onAnimationStart(Animation animation) {

                       }

                       @Override
                       public void onAnimationEnd(Animation animation) {
                           String sss = list.get(4);
                           list.remove(4);
                           list.add(0,sss);
                           adapter.notifyDataSetChanged();
                       }

                       @Override
                       public void onAnimationRepeat(Animation animation) {

                       }
                   });
                }
            }*/
            list.add(0,"ddddddd");
            adapter.notifyDataSetChanged();
        }
        if (v==btn2) {
            srfl.setEnabled(false);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v==panel) {
            return false;
        }
        if (v==listview) {
            if (listview.getTop()>10) {
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    class MyAdapter extends BaseAdapter{

        List<String> list;

        public MyAdapter(List<String> list) {
            this.list=list;

        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder h;
            if (convertView == null) {
                convertView = MainActivity.this.getLayoutInflater().inflate(R.layout.item, null);
                h = new Holder();
                h.tv = (TextView) convertView.findViewById(R.id.item_tv);
                convertView.setTag(h);
            } else {
                h=(Holder)convertView.getTag();
            }
            if (position % 6 == 0) {
                h.tv.setBackgroundColor(Color.parseColor("#78aced"));
            } else {
                h.tv.setBackgroundColor(Color.parseColor("#cde78a"));

            }
            h.tv.setText(list.get(position));
            if (position % 2 == 0) {
//               slide(position,convertView);
            } else {
//                slide(position,convertView);
            }
            if (position > direct) {
                slide(position, convertView, R.anim.up);
            } else {
                slide(position, convertView, R.anim.translate_down);
                Log.i("xiao","translate_down");
            }
            direct=position;

            return convertView;
        }
        class Holder{
            public TextView tv;
        }
    }

    public String addString(String s) {
        return s + "123";
    }

    public void refresh(){

//        srfl.setSize(SwipeRefreshLayout.LARGE);;
       // srfl.setProgressBackgroundColor(R.color.swipe5);
        //swipeRefreshLayout.setPadding(20, 20, 20, 20);
        //swipeRefreshLayout.setProgressViewOffset(true, 100, 200);
        //swipeRefreshLayout.setDistanceToTriggerSync(50);



//        设置小球弹回后的位置
//        srfl.setProgressViewEndTarget(true, 180);




        //设置小球开始和结束的位置
//        srfl.setProgressViewOffset(true,400,500);
//

//
// 设置圆球的大小  只有两种
// srfl.setSize(SwipeRefreshLayout.LARGE);
//
// 小圆球的背景色和渐变border色
    // srfl.setProgressBackgroundColorSchemeColor(Color.parseColor("#ffffff"));


        //  //设置旋转箭头的颜色
//        srfl.setColorSchemeResources(R.color.swipe1,
//                R.color.swipe2,
//                R.color.swipe3,
//                R.color.swipe4);

        //设置旋转箭头的颜色
        srfl.setColorSchemeColors(
                R.color.swipe2,
                R.color.swipe3,
                R.color.swipe4);
     //
        //设置手势滑动的距离和小圆球下降的距离比例，数值越大，手指就需要向下滑动越长得距离唤出小球
        //   srfl.setDistanceToTriggerSync(300);
        srfl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("xx","refresh 1");
                        for (int i=0;i<5;i++) {
                            list.add(0,"NEW "+i);
                        }
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.i("xx","refresh 2");
                        h.sendMessage(new Message());
                        onRefreshing=true;
                    }
                }).start();

            }
        });

    }

   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            if (onRefreshing) {
                srfl.setRefreshing(false);
                adapter.notifyDataSetChanged();
                return false;
            }
        }
        return true;

    }*/
   public void slide(int position,View v) {
       if (position>=currentIndex) {
           Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.up);
           v.startAnimation(animation);
       }
   }
    public void slide(int position,View v, int animId) {
        if (position>=currentIndex) {
            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, animId);
            v.startAnimation(animation);
        }
    }
    public void slide(int position, View v, int animId, final OnAnimationEndListener listener) {
        if (position>=currentIndex) {
            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, animId);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                        listener.onEnd();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            v.startAnimation(animation);
        }
    }

    public interface OnAnimationEndListener {
        public void onEnd();
    }
}

package study.rq.com.pullrefusetest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.jingchen.pulltorefresh.PullableRecyclerView;

public class MainActivity extends AppCompatActivity implements PullToRefreshLayout.OnPullListener {
    PullToRefreshLayout puller;
    PullableRecyclerView dateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        puller = (PullToRefreshLayout) findViewById(R.id.prl_refuse);
//        puller.setCustomRefreshView(new DeafultRefreshView(MainActivity.this));
        puller.setOnPullListener(MainActivity.this);
        dateView = (PullableRecyclerView) findViewById(R.id.prl_refuse_data);
        LinearLayoutManager lm = new LinearLayoutManager(MainActivity.this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        dateView.setLayoutManager(lm);
        RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        };
        dateView.setAdapter(adapter);
    }

    int i = 0;

    @Override // puller.setOnPullListener
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        Log.e("MainActivity", "onRefresh  ");
        i++;
// TODO: 2017/6/5 refresh
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //模拟刷新成功
                puller.refreshFinish(i % 2 == 0 ? PullToRefreshLayout.SUCCEED : PullToRefreshLayout.FAIL);
                Log.e("MainActivity", "PullToRefreshLayout.i = " + i);
            }
        }, 2000);
    }

    @Override // puller.setOnPullListener
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        Log.e("MainActivity", "onLoadMore  ");
// TODO: 2017/6/5 loadMoreDataFromInternet
        i++;
// TODO: 2017/6/5 refresh
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //模拟加载成功与失败
                puller.loadmoreFinish(i % 2 == 0 ? PullToRefreshLayout.SUCCEED : PullToRefreshLayout.FAIL);
                Log.e("MainActivity", "PullToRefreshLayout.i = " + i);
            }
        }, 2000);
    }

    @Override
    public void onCancel(PullToRefreshLayout pullToRefreshLayout) {
        Log.e("MainActivity", "onCancel  ");
    }
}

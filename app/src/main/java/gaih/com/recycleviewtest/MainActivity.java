package gaih.com.recycleviewtest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private MyRecylerViewAdapter myRecylerViewAdapter;
    private List<String> datas = new ArrayList<>();
    private Button bt_add;
    private Button bt_delete;
    private LinearLayoutManager linearLayoutManager;
    private Spinner spinner;
    private  static final String[] m = {"竖着","瀑布流","方块"};
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        spinner = (Spinner) findViewById(R.id.spinner);
        bt_add = (Button) findViewById(R.id.bt1);
        bt_delete = (Button) findViewById(R.id.bt2);
        bt_delete.setOnClickListener(this);
        bt_add.setOnClickListener(this);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setVisibility(View.VISIBLE);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }else if (position ==1){
                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));//设置瀑布流布局
                }else if (position ==2){
                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,3));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        for (int i =0;i<3;i++){
            datas.add("hahahhaha");
        }
        myRecylerViewAdapter = new MyRecylerViewAdapter(MainActivity.this,datas);
        recyclerView.setAdapter(myRecylerViewAdapter);
        myRecylerViewAdapter.setOnItemClickListener(new MyRecylerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final View view, int position) {
                view.animate()
                        .translationZ(15f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                view.animate()
                                        .translationZ(1f)
                                        .setDuration(500)
                                        .start();
                            }
                        }).start();
                Toast.makeText(MainActivity.this,"第"+position+"个",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void add(View view){
        datas.add("ssssss");
        int position = datas.size();
        if (position>0){
            myRecylerViewAdapter.notifyDataSetChanged();
        }
    }
    public void delete(View view){
        int position = datas.size();
        if (position>0){
            datas.remove(position-1);
            myRecylerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt1:
                add(v);
                break;
            case R.id.bt2:
                delete(v);
                break;
        }

    }
}

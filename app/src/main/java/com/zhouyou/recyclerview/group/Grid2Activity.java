package com.zhouyou.recyclerview.group;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.zhouyou.recyclerview.group.adapter.GroupedGridSpanListAdapter;
import com.zhouyou.recyclerview.group.bean.GroupBean;
import com.zhouyou.recyclerview.group.util.GroupModel;
import com.zhouyou.recyclerview.BaseActivity;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;
import com.zhouyou.recyclerviewdemo.R;


/**
 * 子项为Grid布局的分组列表。给RecyclerView的LayoutManager
 * 设置为{@link GroupedGridLayoutManager}即可。
 */
public class Grid2Activity extends BaseActivity {

    private RecyclerView rvList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        rvList = (RecyclerView) findViewById(R.id.rv_list);

        GroupedGridSpanListAdapter adapter = new GroupedGridSpanListAdapter(this, GroupModel.getGroups(20, 5));
        adapter.setOnHeaderClickListener(new com.zhouyou.recyclerview.group.GroupedRecyclerViewAdapter.OnHeaderClickListener<GroupBean>() {
            @Override
            public void onHeaderClick(com.zhouyou.recyclerview.group.GroupedRecyclerViewAdapter adapter, HelperRecyclerViewHolder holder, int groupPosition, GroupBean item) {
                Toast.makeText(Grid2Activity.this, "组头：groupPosition = " + groupPosition,
                        Toast.LENGTH_LONG).show();
            }
        });
        adapter.setOnFooterClickListener(new com.zhouyou.recyclerview.group.GroupedRecyclerViewAdapter.OnFooterClickListener<GroupBean>() {
            @Override
            public void onFooterClick(com.zhouyou.recyclerview.group.GroupedRecyclerViewAdapter adapter, HelperRecyclerViewHolder holder,
                                      int groupPosition, GroupBean item) {
                Toast.makeText(Grid2Activity.this, "组尾：groupPosition = " + groupPosition,
                        Toast.LENGTH_LONG).show();
            }
        });
        adapter.setOnChildClickListener(new com.zhouyou.recyclerview.group.GroupedRecyclerViewAdapter.OnChildClickListener<GroupBean>() {
            @Override
            public void onChildClick(com.zhouyou.recyclerview.group.GroupedRecyclerViewAdapter adapter, HelperRecyclerViewHolder holder,
                                     int groupPosition, int childPosition, GroupBean item) {
                Toast.makeText(Grid2Activity.this, "子项：groupPosition = " + groupPosition
                                + ", childPosition = " + childPosition,
                        Toast.LENGTH_LONG).show();
            }
        });

        //必须放在setAdapter之前，切记！切记！切记！
        rvList.setLayoutManager(new GridLayoutManager(this, 3));
        
        rvList.setAdapter(adapter);
    }
}

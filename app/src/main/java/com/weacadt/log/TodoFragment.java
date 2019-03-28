package com.weacadt.log;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class TodoFragment extends Fragment {
    RecyclerView recyclerView;
    private List<Test> testList =new ArrayList<Test>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initTestData();
        initData();
        super.onActivityCreated(savedInstanceState);
    }

    private void initTestData() {
        testList.add(new Test("打扫卫生"));
        testList.add(new Test("背诵单词"));
        testList.add(new Test("看动漫"));
        testList.add(new Test("洗衣服"));
        testList.add(new Test("做作业"));

    }

    private void initData() {
        recyclerView = getActivity().findViewById(R.id.recyle_todo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        TestAdapter adapter = new TestAdapter(testList);
        recyclerView.setAdapter(adapter);
    }
}

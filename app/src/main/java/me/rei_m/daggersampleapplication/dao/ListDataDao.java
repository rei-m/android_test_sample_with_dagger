package me.rei_m.daggersampleapplication.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ListDataDao {

    @Inject
    public ListDataDao() {
    }

    public List<String> getData() {

        List<String> itemList = new ArrayList<>();
        itemList.add("hoge");
        itemList.add("fuga");
        itemList.add("piyo");
        itemList.add("hogehoge");
        itemList.add("fugafuga");
        itemList.add("piyopiyo");

        return itemList;
    }
}

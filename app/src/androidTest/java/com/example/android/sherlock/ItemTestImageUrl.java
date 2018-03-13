package com.example.android.sherlock;

import com.example.android.sherlock.model.Item;
import com.example.android.sherlock.model.Store;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by ${Abbinav} on 3/12/2018.
 */

public class ItemTestImageUrl {

    @Test
    public void TestItemUrl1() {
        Item item = new Item();
        item.setImageUrl("https://i.imgur.com/tkqNF6L.jpg");
        assertEquals("https://i.imgur.com/tkqNF6L.jpg", item.getImageUrl());
    }

    @Test
    public void TestItemUrl2() {
        Item item = new Item();
        item.setImageUrl("https://i.imgur.com/2vf8jop.jpg");
        assertEquals("https://i.imgur.com/2vf8jop.jpg", item.getImageUrl());
    }
}

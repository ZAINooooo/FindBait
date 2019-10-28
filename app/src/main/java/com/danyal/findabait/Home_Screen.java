//package com.danyal.findabait;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.res.Configuration;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
//public class Home_Screen extends AppCompatActivity implements RecyclerViewAdapter.ItemListener{
//
//    RecyclerView recyclerView;
//    ArrayList<DataModel> arrayList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.home_screen);
//
//
//
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        arrayList = new ArrayList<>();
//        arrayList.add(new DataModel("Item 1", R.drawable.battle, "#09A9FF"));
//        arrayList.add(new DataModel("Item 2", R.drawable.beer, "#3E51B1"));
//        arrayList.add(new DataModel("Item 3", R.drawable.ferrari, "#673BB7"));
//        arrayList.add(new DataModel("Item 4", R.drawable.jetpack_joyride, "#4BAA50"));
//        arrayList.add(new DataModel("Item 5", R.drawable.three_d, "#F94336"));
//        arrayList.add(new DataModel("Item 6", R.drawable.terraria, "#0A9B88"));
//
//
//
//        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
//            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,arrayList, this);
//            GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 3);
//            int spanCount = 3; // 3 columns
//            int spacing = 50; // 50px
//            boolean includeEdge = false;
//            recyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
//            recyclerView.setLayoutManager(mGridLayoutManager);
//            recyclerView.setAdapter(adapter);
//        }
//        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
//            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,arrayList, this);
//            GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 2);
//            int spanCount = 2; // 3 columns
//            int spacing = 40; // 50px
//            boolean includeEdge = false;
//            recyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
//            recyclerView.setLayoutManager(mGridLayoutManager);
//            recyclerView.setAdapter(adapter);
//        }
//        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
//            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,arrayList, this);
//            GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 2);
//            int spanCount = 2; // 3 columns
//            int spacing = 50; // 50px
//            boolean includeEdge = false;
//            recyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
//            recyclerView.setLayoutManager(mGridLayoutManager);
//            recyclerView.setAdapter(adapter);
//        }
//        else {
//            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,arrayList, this);
//            GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 3);
//            int spanCount = 3; // 3 columns
//            int spacing = 50; // 50px
//            boolean includeEdge = false;
//            recyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
//            recyclerView.setLayoutManager(mGridLayoutManager);
//            recyclerView.setAdapter(adapter);
//        }
//
//
////        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,arrayList, this);
////        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 3);
////        int spanCount = 3; // 3 columns
////        int spacing = 50; // 50px
////        boolean includeEdge = false;
////        recyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
////        recyclerView.setLayoutManager(mGridLayoutManager);
////        recyclerView.setAdapter(adapter);
//    }
//
//
//
//
//
//
//    @Override
//    public void onItemClick(DataModel item) {
//
//        Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();
//
//    }
//}

package com.pop.pricecutz.activities.other;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;

/**
 * A placeholder fragment containing a simple view.
 */
public class PreferenceActivityFragment extends Fragment implements AdapterView.OnItemClickListener {

//    CategoryGridAdapter mCategoryGridAdapter;
//
//    ArrayList<Category> mCategoryArrayList;
//
//    Button mButton;
//
//    GridView gridView;
//
//    Context mContext;
//
//    ArrayList<String> preferenceArrayList;
//
//    public PreferenceActivityFragment() {
////        preferenceArrayList = new ArrayList<>();
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        mContext = getContext();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        View root = inflater.inflate(R.layout.fragment_preference, container, false);
//
//        gridView = (GridView) root.findViewById(R.id.fragment_preference_gidview);
//
//        mButton = (Button) root.findViewById(R.id.fragment_preference_button);
//        mButton.setEnabled(false);
//
////        mCategoryArrayList = new ArrayList<>();
////
////        for(String c: Data.categories) {
////            mCategoryArrayList.add(new Category(0, "", c));
////        }
////
////        mCategoryGridAdapter = new CategoryGridAdapter(
////                mContext,
////                R.layout.fragment_preference_grid_items,
////                R.id.fragment_preference_grid_item_imageview,
////                R.id.fragment_preference_grid_item_imageview2,
////                R.id.fragment_preference_grid_item_textview,
////                mCategoryArrayList);
////
////        gridView.setAdapter(mCategoryGridAdapter);
////        gridView.setOnItemClickListener(this);
//        return root;
//    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//        mCategoryArrayList.get(i).setSelected(true);
//        Category c = mCategoryArrayList.get(i);
//
//        setNewGridView();
////
////        mCategoryGridAdapter.getItem(i).setSelected(!c.isSelected());
////
////        mCategoryGridAdapter.notifyDataSetChanged();
////        gridView.invalidateViews();
//
////        boolean addedAlready = false;
////        int index = -1;
////        for(int q = 0; q < preferenceArrayList.size(); q++) {
////            String str = preferenceArrayList.get(q);
////            if(str.equalsIgnoreCase(c.getName())) {
////                addedAlready = true;
////                index = q;
////                break;
////            }
////        }
//
//        //Toast.makeText(mContext, "addedAlready - " + addedAlready, Toast.LENGTH_SHORT).show();
//
////        ImageView selectImageView   = (ImageView) view.findViewById(R.id.fragment_preference_grid_item_imageview2);
////        TextView textView           = (TextView) view.findViewById(R.id.fragment_preference_grid_item_textview);
////
////
////
////        if(addedAlready) {      //Remove from list
////            preferenceArrayList.remove(index);
////            //Toast.makeText(mContext, "Added already" + index, Toast.LENGTH_SHORT).show();
////            selectImageView.setVisibility(View.INVISIBLE);
////        }
////        else {                  //Add to list
////            preferenceArrayList.add(c.getName());
////            selectImageView.setVisibility(View.VISIBLE);
////            textView.setText("index = " + i);
////            //Toast.makeText(mContext, "Not added already", Toast.LENGTH_SHORT).show();
////        }
////
////        if(preferenceArrayList.size() == 0){
////            mButton.setText("Finish");
////            mButton.setEnabled(false);
////        }
////        else {
////            mButton.setText(preferenceArrayList.size() + " selected: Finish");
////            mButton.setEnabled(true);
////        }
////
////
//////        radioButton.setVisibility(View.VISIBLE);
//////        radioButton.setChecked(true);
////
//        Toast.makeText(mContext, "Clicked - " + i, Toast.LENGTH_SHORT).show();
////
////        //updatePreferenceArrayList();
    }

    private void updatePreferenceArrayList(RadioButton radioButton) {

    }

    private void setNewGridView() {

//        mCategoryGridAdapter = new CategoryGridAdapter(
//                mContext,
//                R.layout.fragment_preference_grid_items,
//                R.id.fragment_preference_grid_item_imageview,
//                R.id.fragment_preference_grid_item_imageview2,
//                R.id.fragment_preference_grid_item_textview,
//                mCategoryArrayList);
//
//        gridView.setAdapter(mCategoryGridAdapter);

    }
}

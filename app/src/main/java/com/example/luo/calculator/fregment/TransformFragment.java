package com.example.luo.calculator.fregment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.luo.calculator.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TransformFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TransformFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransformFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private double[][] convert = {
            {1, 0.1, 0.01, 0.001, 0.000001,},
            {10, 1, 0.1, 0.01, 0.00001,},
            {100, 10, 1, 0.1, 0.0001,},
            {1000, 100, 10, 1, 0.001,},
            {1000000, 100000, 10000, 1000, 1,},
    };
    private Button btn_unit_conversion_convert;
    private TextView text_unit_conversion_value1;
    private TextView text_unit_conversion_value2;
    private Spinner spinner_unit_conversion_unit1;
    private Spinner spinner_unit_conversion_unit2;

    public TransformFragment() {
        // Required empty public constructor
    }


    @Override
    public void onStart() {
        super.onStart();
        btn_unit_conversion_convert = (Button)getView().findViewById(R.id.btn_unit_conversion_convert);
        text_unit_conversion_value1 = (TextView)getView().findViewById(R.id.text_unit_conversion_value1);
        text_unit_conversion_value2 = (TextView)getView().findViewById(R.id.text_unit_conversion_value2);
        spinner_unit_conversion_unit1 =  (Spinner)getView().findViewById(R.id.spinner_unit_conversion_unit1);
        spinner_unit_conversion_unit2 =  (Spinner)getView().findViewById(R.id.spinner_unit_conversion_unit2);
        btn_unit_conversion_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double value1 = getValue1();
                double value2 = value1 * convert[getUnit1()][getUnit2()];
                text_unit_conversion_value2.setText(value2+"");
            }
        });
    }

    private double getValue1(){
        double value1 = Double.parseDouble(text_unit_conversion_value1.getText()+"");
        return value1;
    }

    private int getUnit1() {

        return unitToInt(spinner_unit_conversion_unit1.getSelectedItem().toString());
    }


    private int getUnit2() {
        return unitToInt(spinner_unit_conversion_unit2.getSelectedItem().toString());
    }




    public int unitToInt(String unit) {
        if (unit.equals("mm")) return 0;
        if (unit.equals("cm")) return 1;
        if (unit.equals("dm")) return 2;
        if (unit.equals("m")) return 3;
        if (unit.equals("km")) return 4;
        return -1;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransformFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransformFragment newInstance(String param1, String param2) {
        TransformFragment fragment = new TransformFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transform, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

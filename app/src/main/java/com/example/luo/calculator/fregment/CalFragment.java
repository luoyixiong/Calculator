package com.example.luo.calculator.fregment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.luo.calculator.R;
import com.example.luo.calculator.cal.ProcessExpression;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private EditText editText;

    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button button_7;
    private Button button_8;
    private Button button_9;
    private Button button_0;
    private Button button_add;
    private Button button_sub;
    private Button button_mul;
    private Button button_mins;
    private Button button_point;
    private Button button_equal;
    private Button button_clear;
    private Button button_delete;

    boolean canInputAPoint = true;

    public CalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment CalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalFragment newInstance() {
        CalFragment fragment = new CalFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        editText = (EditText) getView().findViewById(R.id.Edit_Text);
        button_0 = (Button) getView().findViewById(R.id.button_0);
        button_1 = (Button) getView().findViewById(R.id.button_1);
        button_2 = (Button) getView().findViewById(R.id.button_2);
        button_3 = (Button) getView().findViewById(R.id.button_3);
        button_4 = (Button) getView().findViewById(R.id.button_4);
        button_5 = (Button) getView().findViewById(R.id.button_5);
        button_6 = (Button) getView().findViewById(R.id.button_6);
        button_7 = (Button) getView().findViewById(R.id.button_7);
        button_8 = (Button) getView().findViewById(R.id.button_8);
        button_9 = (Button) getView().findViewById(R.id.button_9);
        button_point = (Button) getView().findViewById(R.id.button_point);
        button_clear = (Button) getView().findViewById(R.id.button_clear);
        button_delete = (Button) getView().findViewById(R.id.button_delete);
        button_equal = (Button) getView().findViewById(R.id.button_equals);
        button_add = (Button) getView().findViewById(R.id.button_add);
        button_sub = (Button) getView().findViewById(R.id.button_sub);
        button_mul = (Button) getView().findViewById(R.id.button_mul);
        button_mins = (Button) getView().findViewById(R.id.button_mins);



        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_point.setOnClickListener(this);
        button_clear.setOnClickListener(this);
        button_delete.setOnClickListener(this);
        button_add.setOnClickListener(this);
        button_sub.setOnClickListener(this);
        button_mul.setOnClickListener(this);
        button_mins.setOnClickListener(this);
        button_equal.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cal, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        String str = editText.getText().toString();

        //Log.i(TAG,str);

        switch (v.getId()) {
            case R.id.button_0:
            case R.id.button_1:
            case R.id.button_2:
            case R.id.button_3:
            case R.id.button_4:
            case R.id.button_5:
            case R.id.button_6:
            case R.id.button_7:
            case R.id.button_8:
            case R.id.button_9:
                editText.setText(str + ((Button) v).getText());
                break;
            case R.id.button_point:
            case R.id.button_add:
            case R.id.button_mul:
            case R.id.button_sub:
            case R.id.button_mins:

                //输入框中有无内容
                if (!editText.getText().toString().isEmpty()) {//有内容

                    if (str.length() == 1) {//当输入框中只有一字符时
                        lengthEqualOne(str,v);
                    }else {                 //字符数大于一
                        lengthBigThanOne(str,v);
                    }
                    //无内容，符号只能输入减号
                } else {
                    if (((Button) v).getText().equals("-")) {
                        editText.setText(str + "-");
                    }
                }

                break;

            case R.id.button_clear:
                editText.setText("");
                break;
            case R.id.button_delete:
                if (str != null && !str.isEmpty()) {
                    editText.setText(str.substring(0, str.length() - 1));
                }
                break;
            case R.id.button_equals:

                if (!editText.getText().toString().isEmpty()) {
                    if (isOpChar(str.charAt(str.length() - 1))) {
                        str.substring(0, str.length() - 1);
                    }
                    str += ((Button) v).getText();

                    //控制浮点数输出的精度
                    ProcessExpression proExp = new ProcessExpression(str);
                    DecimalFormat decimalFormat = new DecimalFormat("######.000000");
                    editText.setText(decimalFormat.format(proExp.getResult()) + "");
                }
                break;

        }

    }

    private void lengthEqualOne(String str,View v){
        if (str.charAt(0) == '-') {//为减号
            if (!((Button) v).getText().equals("-")) {//输入任何其他符号都清空输入框，减号则无任何改变
                editText.setText("");
            }
        } else {
            if(((Button) v).getText().equals(".")) {
                if (canInputAPoint) {
                    canInputAPoint = false;
                    editText.setText(str + ((Button) v).getText());
                }
            }else {
                editText.setText(str + ((Button) v).getText());
            }
        }
    }

    private void lengthBigThanOne(String str,View v){
        if (isNumChar(str.charAt(str.length() - 1))) {
            if(((Button) v).getText().equals(".")) {
                if (canInputAPoint) {
                    canInputAPoint = false;
                    editText.setText(str + ((Button) v).getText());
                }
            }else {
                canInputAPoint = true;
                editText.setText(str + ((Button) v).getText());
            }
        } else {

            if (((Button) v).getText().equals("-") && isMulOrMins(str.charAt(str.length() - 1))) {
                editText.setText(str + "-");
            } else {
                if (isMulOrMins(str.charAt(str.length() - 2))) {
                    editText.setText(str.substring(0, str.length() - 2) + ((Button) v).getText());
                } else {
                    if (!((Button) v).getText().equals(".")) {

                        editText.setText(str.substring(0, str.length() - 1) + ((Button) v).getText());
                        canInputAPoint = true;
                    } else {

                        if (canInputAPoint) {
                            editText.setText(str.substring(0, str.length() - 1) + ((Button) v).getText());
                            canInputAPoint = false;
                        }
                    }
                }
            }
        }
    }
    private boolean isOpChar(char opchar) {
        if (opchar == '/' || opchar == '*' || opchar == '+' || opchar == '-') {
            return true;
        } else {
            return false;
        }
    }

    private boolean isMulOrMins(char opchar) {
        if (opchar == '/' || opchar == '*') {
            return true;
        } else {
            return false;
        }
    }

    private boolean isNumChar(char num) {
        if ('0' <= num && num <= '9') {
            return true;
        } else {
            return false;
        }
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
